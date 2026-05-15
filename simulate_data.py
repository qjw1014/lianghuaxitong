#!/usr/bin/env python3
"""
模拟交易数据生成器
在模拟模式下，定期给净值加随机波动，让前端看到实时变化。
状态保存在 Redis 中，不受 Java 引擎覆盖影响。
每30秒由 cron 触发执行。
"""
import json
import random
from datetime import datetime
from time import time

import pymysql
import redis as redis_lib

DB_CONFIG = {
    "host": "localhost",
    "port": 3306,
    "user": "root",
    "password": "",
    "database": "bite",
    "charset": "utf8mb4",
}

REDIS_CONFIG = {
    "host": "localhost",
    "port": 6379,
    "db": 0,
    "decode_responses": True,
}

ACCOUNT_ID = 8
INIT_USDT = 10000.0
MAX_FLUCTUATION = 5.0
MIN_NET_VALUE = 9500.0
MAX_NET_VALUE = 10500.0
SIM_STATE_KEY = f"simulate:account:{ACCOUNT_ID}:state"


def main():
    r = redis_lib.Redis(**REDIS_CONFIG)

    # 1. 读取实际账户统计值（优先），确保与真实净值同步
    arb_stats_str = r.hgetall(f"arb_stats:{ACCOUNT_ID}")
    if arb_stats_str and "currentUsdt" in arb_stats_str:
        current_net = float(arb_stats_str["currentUsdt"])
        init_usdt_val = float(arb_stats_str.get("initUsdt", INIT_USDT))
    else:
        # 回退到模拟状态
        state_str = r.get(SIM_STATE_KEY)
        if state_str:
            state = json.loads(state_str)
            current_net = state["net_value"]
            init_usdt_val = state.get("init_usdt", INIT_USDT)
        else:
            # 首次运行，从数据库读取初始净值
            conn = pymysql.connect(**DB_CONFIG)
            try:
                with conn.cursor() as cursor:
                    cursor.execute(
                        "SELECT net_value FROM b_revenue_curve WHERE api_account_id = %s ORDER BY id DESC LIMIT 1",
                        (ACCOUNT_ID,),
                    )
                    row = cursor.fetchone()
                    current_net = float(row[0]) if row else INIT_USDT
                    init_usdt_val = INIT_USDT
            finally:
                conn.close()

    # 2. 随机波动
    if random.random() < 0.7:
        delta = round(random.uniform(-MAX_FLUCTUATION, MAX_FLUCTUATION), 2)
    else:
        delta = round(random.uniform(-MAX_FLUCTUATION * 2, MAX_FLUCTUATION * 2), 2)

    new_net = round(current_net + delta, 8)
    new_net = max(MIN_NET_VALUE, min(MAX_NET_VALUE, new_net))

    # 3. 保存模拟状态到 Redis
    r.set(SIM_STATE_KEY, json.dumps({"net_value": new_net, "updated_at": datetime.now().isoformat()}))

    # 4. 计算统计字段并直接写入 account_detail（让前端立即看到变化）
    total_profit = round(new_net - init_usdt_val, 8)
    charge = 0.0
    days7_profit = round(total_profit, 8)
    days30_profit = round(total_profit, 8)
    days7_float = round(total_profit / init_usdt_val / 7 * 365, 10) if init_usdt_val else 0.0
    days30_float = round(total_profit / init_usdt_val / 30 * 365, 10) if init_usdt_val else 0.0
    total_float = round(total_profit / init_usdt_val, 10) if init_usdt_val else 0.0

    stats = {
        "init_usdt": init_usdt_val,
        "charge": charge,
        "current_usdt": new_net,
        "7days_profit": days7_profit,
        "7days_profit_float": days7_float,
        "30days_profit": days30_profit,
        "30days_profit_float": days30_float,
        "total_profit": total_profit,
        "total_profit_float": total_float,
    }
    r.hset("account_detail", str(ACCOUNT_ID), json.dumps(stats, ensure_ascii=False))

    profit = round(new_net - current_net, 8)

    # 6. 累加每日套利收益到独立 Redis key（与马丁策略分开）
    today = datetime.now().strftime("%Y-%m-%d")
    r.hincrbyfloat(f"arb_daily_pnl:{ACCOUNT_ID}", today, profit)

    print(f"[OK] 净值: {current_net} → {new_net} (Δ{profit:+.2f}) 已写入 Redis")

    # 5. 异步写入 MySQL（不影响前端响应速度）
    try:
        conn = pymysql.connect(**DB_CONFIG)
        try:
            with conn.cursor() as cursor:
                now = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                today = datetime.now().strftime("%Y-%m-%d")

                # 收益曲线
                cursor.execute(
                    "INSERT INTO b_revenue_curve (api_account_id, date_time, net_value, create_time) VALUES (%s, %s, %s, %s) "
                    "ON DUPLICATE KEY UPDATE net_value = VALUES(net_value), create_time = VALUES(create_time)",
                    (ACCOUNT_ID, today, new_net, now),
                )

                # 收益明细（date_time 精确到分秒）
                if profit != 0:
                    cursor.execute(
                        "INSERT INTO b_revenue_detail (api_account_id, date_time, profit, profit_coin, create_time) VALUES (%s, %s, %s, %s, %s)",
                        (ACCOUNT_ID, now, profit, "USDT", now),
                    )

                # 更新 statistics_account
                cursor.execute(
                    "UPDATE b_statistics_account SET current_usdt = %s, total_profit = %s, total_profit_float = %s, "
                    "days7_profit = %s, days7_profit_float = %s, days30_profit = %s, days30_profit_float = %s, charge = %s, update_time = %s "
                    "WHERE api_account_id = %s",
                    (new_net, total_profit, total_float, days7_profit, days7_float, days30_profit, days30_float, charge, now, ACCOUNT_ID),
                )
                conn.commit()
        finally:
            conn.close()
    except Exception as e:
        print(f"[WARN] MySQL写入失败（不影响前端）: {e}")


if __name__ == "__main__":
    main()
