package com.wallet.err;

import java.util.HashMap;
import java.util.Map;

public class BinanceErrMessage {
	 public static Map<String, String> BinanceMessage = new HashMap<String, String>();
	 static {
		 BinanceMessage.put("-1000", "未知错误");
		 BinanceMessage.put("-1001", "连接断开");
		 BinanceMessage.put("-1002 ","未授权");
		 BinanceMessage.put("-1003", "请求过多");
		 BinanceMessage.put("-1006", "非常规响应");
		 BinanceMessage.put("-1007", "超时");
		 BinanceMessage.put("-1014", "不支持的订单参数(组合)");
		 BinanceMessage.put("-1015", "订单太多");
		 BinanceMessage.put("-1016", "服务器下线");
		 BinanceMessage.put("-1020", "不支持的操作");
		 BinanceMessage.put("-1021", "时延过大");
		 BinanceMessage.put("-1022", "签名不正确");
		 BinanceMessage.put("-1100", "非法字符");
		 BinanceMessage.put("-1101", "参数太多");
		 BinanceMessage.put("-1102", "缺少必须参数");
		 BinanceMessage.put("-1103", "无法识别的参数");
		 BinanceMessage.put("-1104", "冗余参数");
		 BinanceMessage.put("-1105", "空参数(仅有参数名)");
		 BinanceMessage.put("-1106", "非必需参数");
		 BinanceMessage.put("-1111", "精度过高");
		 BinanceMessage.put("-1112", "空白的orderbook");
		 BinanceMessage.put("-1114", "错误地发送了不需要的TIF参数");
		 BinanceMessage.put("-1115", "无效的TIF参数");
		 BinanceMessage.put("-1116", "无效的订单类型");
		 BinanceMessage.put("-1117", "无效的订单方向");
		 BinanceMessage.put("-1118", "空白的newClientOrderId");
		 BinanceMessage.put("-1119", "空白的originalClientOrderId");
		 BinanceMessage.put("-1120", "无效的间隔(interval)");
		 BinanceMessage.put("-1121", "空白的originalClientOrderId");
		 BinanceMessage.put("-1125", "无效的listenKey");
		 BinanceMessage.put("-1127", "查询间隔过长");
		 BinanceMessage.put("-1128", "无效的可选参数组合");
		 BinanceMessage.put("-1130", "无效参数(值)");
		 BinanceMessage.put("-2010", "订单被拒绝");
		 BinanceMessage.put("-2011", "订单取消被拒绝,不存在改该订单");
		 BinanceMessage.put("-2013", "不存在的订单");
		 BinanceMessage.put("-2014", "API Key格式无效");
		 BinanceMessage.put("-2015", "API Key权限，例如该Key不存在");
		 BinanceMessage.put("-2016", "订单取消被拒绝");
		 BinanceMessage.put("-1010", "找不到订单，或者账户金额不足");
		 BinanceMessage.put("10001", "平台接口连接不通");
         BinanceMessage.put("10002", "参数错误或者无效APIKey");
	 }

    public static String error(String code){
        if(BinanceMessage.containsKey(code)){
            return BinanceMessage.get(code);
        }else{
            return BinanceMessage.get("10002");
        }
    }
}
