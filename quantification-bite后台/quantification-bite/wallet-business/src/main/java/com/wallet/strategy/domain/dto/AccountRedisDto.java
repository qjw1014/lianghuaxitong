package com.wallet.strategy.domain.dto;


import java.math.BigDecimal;

/**
 * 存放redis
 */
public class AccountRedisDto {
    private String api_key;

    private String secret_key;

    private String exchange;

    private String password;

    private Long account_id;

    private BigDecimal init_usdt;

    public BigDecimal getInit_usdt() {
        return init_usdt;
    }

    public void setInit_usdt(BigDecimal init_usdt) {
        this.init_usdt = init_usdt;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }
}
