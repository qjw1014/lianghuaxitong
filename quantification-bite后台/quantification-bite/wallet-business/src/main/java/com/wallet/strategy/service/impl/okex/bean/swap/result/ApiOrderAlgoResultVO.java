package com.wallet.strategy.service.impl.okex.bean.swap.result;

public class ApiOrderAlgoResultVO {

    private PerOrderResult data;
    private Integer code;
    private String detailMsg;
    private String msg;

    public PerOrderResult getData() {
        return data;
    }

    public void setData(PerOrderResult data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class PerOrderResult {

        //调用接口返回结果 success
        String result;
        //订单ID
        String algo_id;
        //合约ID
        String instrument_id;
        //错误码，下单成功时为0，下单失败时会显示相应错误码
        String error_code;
        //错误信息，下单成功时为空，下单失败时会显示错误信息
        String error_message;
        //1：止盈止损 2：跟踪委托 3：冰山委托 4：时间加权
        String order_type;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getAlgo_id() {
            return algo_id;
        }

        public void setAlgo_id(String algo_id) {
            this.algo_id = algo_id;
        }

        public String getInstrument_id() {
            return instrument_id;
        }

        public void setInstrument_id(String instrument_id) {
            this.instrument_id = instrument_id;
        }

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public String getError_message() {
            return error_message;
        }

        public void setError_message(String error_message) {
            this.error_message = error_message;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }
    }
}
