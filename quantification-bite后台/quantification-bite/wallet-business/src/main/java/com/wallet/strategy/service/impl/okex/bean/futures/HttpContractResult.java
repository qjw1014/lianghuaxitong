package com.wallet.strategy.service.impl.okex.bean.futures;

/**
 * Http Result
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 17/03/2018 11:36
 */
public class HttpContractResult {

    private int error_code;
    private String error_message;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
