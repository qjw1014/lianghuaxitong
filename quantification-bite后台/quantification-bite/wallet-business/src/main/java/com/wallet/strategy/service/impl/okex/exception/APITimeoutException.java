package com.wallet.strategy.service.impl.okex.exception;

/**
 * API Exception
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/3/8 19:59
 */
public class APITimeoutException extends RuntimeException {

    private int code;

    public APITimeoutException(String message) {
        super(message);
    }

    public APITimeoutException(int code, String message) {
        super(message);
        this.code = code;
    }


    public APITimeoutException(Throwable cause) {
        super(cause);
    }

    public APITimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        if (this.code != 0) {
            return this.code + " : " + super.getMessage();
        }
        return super.getMessage();
    }
}
