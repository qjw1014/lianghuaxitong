#!/usr/bin/env python3
"""
统计账户数据更新脚本
从 MySQL 读取最新净值/收益数据，计算统计字段，写入 Redis。
建议每分钟由 cron 触发执行。
"""
import json
import sys
from datetime import datetime, timedelta

import pymysql
import redis as redis_lib

# ====== 配置 ======
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

REDIS_KEY = "account_detail"


def get_conn():
    return pymysql.connect(**DB_CONFIG)


def update_account(account_id):
    conn = get_conn()
    try:
        with conn.cursor() as cursor:
            # 1. 基准本金
            cursor.execute(
                "SELECT init_usdt, charge FROM b_statistics_account WHERE api_account_id = %s",
                (account_id,),
            )
            row = cursor.fetchone()
            if row is None:
                print(f"account_id={account_id}: b_statistics_account 无数据，跳过")
                return
            init_usdt = float(row[0])
            charge_db = float(row[1]) if row[1] else 0.0

            # 2. 最新净值
            cursor.execute(
                "SELECT net_value, date_time FROM b_revenue_curve WHERE api_account_id = %s ORDER BY id DESC LIMIT 1",
                (account_id,),
            )
            curve = cursor.fetchone()
            if curve is None:
                print(f"account_id={account_id}: b_revenue_curve 无数据，跳过")
                return
            current_usdt = float(curve[0])
            latest_date_str = curve[1] if curve[1] else ""

            # 3. 近30天充提
            since_30d = (datetime.now() - timedelta(days=30)).strftime("%Y-%m-%d %H:%M:%S")
            cursor.execute(
                "SELECT COALESCE(SUM(amount), 0) FROM b_charge_history WHERE api_account_id = %s AND create_time >= %s",
                (account_id, since_30d),
            )
            charge_30d = float(cursor.fetchone()[0])
            charge = charge_30d if charge_30d != 0 else charge_db

            # 4. N 天前的净值（用于区间收益）
            def net_value_days_ago(days):
                target = (datetime.now() - timedelta(days=days)).strftime("%Y-%m-%d")
                cursor.execute(
                    "SELECT net_value FROM b_revenue_curve WHERE api_account_id = %s AND date_time <= %s ORDER BY id DESC LIMIT 1",
                    (account_id, target),
                )
                r = cursor.fetchone()
                return float(r[0]) if r else None

            # 5. 计算各项收益
            total_profit = round(current_usdt - init_usdt - charge, 8)

            nv7 = net_value_days_ago(7)
            days7_profit = round(current_usdt - nv7, 8) if nv7 is not None else total_profit

            nv30 = net_value_days_ago(30)
            days30_profit = round(current_usdt - nv30, 8) if nv30 is not None else total_profit

            # 6. 年化收益率（7日/30日年化）和累计收益率（简单收益率）
            def annualized(profit, days):
                if init_usdt == 0 or days <= 0:
                    return 0.0
                return round(profit / init_usdt / days * 365, 10)

            days7_float = annualized(days7_profit, 7)

            # 30天年化：有30天历史数据用30天，否则至少按30天折算（避免数据不足时年化率极端）
            if nv30 is not None:
                days30_actual = 30
            else:
                try:
                    latest_date = datetime.strptime(latest_date_str, "%Y-%m-%d") if latest_date_str else datetime.now()
                    days30_actual = max(30, (datetime.now() - latest_date).days)
                except (ValueError, TypeError):
                    days30_actual = 30
            days30_float = annualized(days30_profit, days30_actual)

            # 累计收益率 = 总收益 / 本金（不是年化）
            total_float = round(total_profit / init_usdt, 10) if init_usdt != 0 else 0.0

    finally:
        conn.close()

    # 7. 写入 Redis
    stats = {
        "init_usdt": round(init_usdt, 8),
        "charge": round(charge, 8),
        "current_usdt": current_usdt,
        "7days_profit": days7_profit,
        "7days_profit_float": days7_float,
        "30days_profit": days30_profit,
        "30days_profit_float": days30_float,
        "total_profit": total_profit,
        "total_profit_float": total_float,
    }
    r = redis_lib.Redis(**REDIS_CONFIG)
    r.hset(REDIS_KEY, str(account_id), json.dumps(stats, ensure_ascii=False))
    print(f"account_id={account_id}: 已写入 Redis -> {json.dumps(stats, ensure_ascii=False)}")


def main():
    conn = get_conn()
    try:
        with conn.cursor() as cursor:
            cursor.execute("SELECT DISTINCT api_account_id FROM b_statistics_account")
            ids = [row[0] for row in cursor.fetchall()]
    finally:
        conn.close()

    if not ids:
        print("没有找到账户，退出")
        sys.exit(0)

    for aid in ids:
        try:
            update_account(aid)
        except Exception as e:
            print(f"account_id={aid}: 出错 {e}")


if __name__ == "__main__":
    main()
