package com.wallet.strategy.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 *
 * @author wallet
 * @date 2022-09-05
 */
@ApiModel("策略控制对象")
public class MartinStrategyApiRedisDto
{


    /** 流水编号 */
    @ApiModelProperty(value = "流水编号")
    private Long id;

    /** 策略编号 */
    @ApiModelProperty(value = "策略编号")
    private Long strategy_id;


    /** 1.开启 2.停止 */

    @ApiModelProperty(value = "1.开启 2.停止")
    private Integer strategy_status;


    /** 策略类型 */

    @ApiModelProperty(value = "策略类型")
    private Long strategy_type;


    @ApiModelProperty(value = "交易所")
    private String exchange;

    @ApiModelProperty(value = "apikey")
    private String api_key;

    @ApiModelProperty(value = "secret_key")
    private String secret_key;


    @ApiModelProperty(value = "密码")
    private String passphrase;


    /** API对应的id */
    @ApiModelProperty(value = "API对应的id")
    private Long account_id;


    /** 初始usdt */
    @ApiModelProperty(value = "初始usdt")
    private BigDecimal init_usdt;


    /** 首单金额比例 */
    @ApiModelProperty(value = "首单金额比例")
    private BigDecimal base_rate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStrategy_id() {
        return strategy_id;
    }

    public void setStrategy_id(Long strategy_id) {
        this.strategy_id = strategy_id;
    }

    public Integer getStrategy_status() {
        return strategy_status;
    }

    public void setStrategy_status(Integer strategy_status) {
        this.strategy_status = strategy_status;
    }

    public Long getStrategy_type() {
        return strategy_type;
    }

    public void setStrategy_type(Long strategy_type) {
        this.strategy_type = strategy_type;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
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

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public BigDecimal getInit_usdt() {
        return init_usdt;
    }

    public void setInit_usdt(BigDecimal init_usdt) {
        this.init_usdt = init_usdt;
    }

    public BigDecimal getBase_rate() {
        return base_rate;
    }

    public void setBase_rate(BigDecimal base_rate) {
        this.base_rate = base_rate;
    }
}
