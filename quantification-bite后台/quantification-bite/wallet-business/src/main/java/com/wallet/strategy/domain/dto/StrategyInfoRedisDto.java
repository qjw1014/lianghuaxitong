package com.wallet.strategy.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wallet.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.web3j.abi.datatypes.Int;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 存放到redis对象
 * 
 * @author wallet
 * @date 2023-03-14
 */
public class StrategyInfoRedisDto
{

    private String api_key;

    /**
     * 秘钥
     */
    private String secret_key;

    /**
     * 密码
     */
    private String passphrase;

    /** 策略编号 */
    private Long strategy_id;


    /** 状态0等待开启，1开启，2停止，3已完成 */
    private Integer strategy_status;

    /**
     * 策略类型id
     */
    private Long strategy_type;


    /** 交易所 */
    private String exchange;


    /** 子账号编号 */
    private Long account_id;


    /** 交易对 */
    private String symbol;


    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /** 执行币种 */
    private String coin;


    /** 买方（现货spot，杠杆margin，coinswap币本位合约，  usdtswap U本位 合约） */
    private String buyer;


    /** 卖方 */
    private String seller;


    /** 每张对应的面值 */
    private BigDecimal value;


    /** 每笔下单量 */
    private Long every_amount;


    /** 总下单量（张） */
    private Long total_amount;


    /** 下单次数 */
    private Long trade_count;


    /** 是否一边maker一边taker（1是，0否） */
    private String is_maker;


    /** 杠杆倍数 */
    private Long leverage;


    /** 投入的本金 */
    private BigDecimal init_usdt;


    /** 每一档的开仓金额（两个币各开仓: every_volume * leverage / price） */
    private BigDecimal every_volume;


    /** 币种1 */
    private String symbol_1;


    /** 币种2 */
    private String symbol_2;


    /** 币1的下单数量精度 */
    private Long amount_decimal_1;


    /** 币2的下单数量精度 */

    @Excel(name = "币2的下单数量精度")
    private Long amount_decimal_2;


    /** 币1的下单价格精度 */
    private Long price_decimal_1;


    /** 币2的下单价格精度 */
    private Long price_decimal_2;


    /** 是否一键全平 no否 yes是 */
    private String is_close_all;


    /** K线粒度，如15m */
    private String kline_period;


    /** 初始均线， coin1/coin2的价格比例在初始均线之上则coin1做空，coin2做多，在下则反之 */
    private BigDecimal init_line;


    /** 网格间距 */
    private BigDecimal grid_gap;


    /** 价格上限 */
    private BigDecimal low_limit_price;


    /** 价格下限 */
    private BigDecimal high_limit_price;

    /** 其他参数（拓展用） */
    private String detail_info;

    /**  开平仓，open开仓，close平仓 */
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getEvery_amount() {
        return every_amount;
    }

    public void setEvery_amount(Long every_amount) {
        this.every_amount = every_amount;
    }

    public Long getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Long total_amount) {
        this.total_amount = total_amount;
    }

    public Long getTrade_count() {
        return trade_count;
    }

    public void setTrade_count(Long trade_count) {
        this.trade_count = trade_count;
    }

    public String getIs_maker() {
        return is_maker;
    }

    public void setIs_maker(String is_maker) {
        this.is_maker = is_maker;
    }

    public Long getLeverage() {
        return leverage;
    }

    public void setLeverage(Long leverage) {
        this.leverage = leverage;
    }

    public BigDecimal getInit_usdt() {
        return init_usdt;
    }

    public void setInit_usdt(BigDecimal init_usdt) {
        this.init_usdt = init_usdt;
    }

    public BigDecimal getEvery_volume() {
        return every_volume;
    }

    public void setEvery_volume(BigDecimal every_volume) {
        this.every_volume = every_volume;
    }

    public String getSymbol_1() {
        return symbol_1;
    }

    public void setSymbol_1(String symbol_1) {
        this.symbol_1 = symbol_1;
    }

    public String getSymbol_2() {
        return symbol_2;
    }

    public void setSymbol_2(String symbol_2) {
        this.symbol_2 = symbol_2;
    }

    public Long getAmount_decimal_1() {
        return amount_decimal_1;
    }

    public void setAmount_decimal_1(Long amount_decimal_1) {
        this.amount_decimal_1 = amount_decimal_1;
    }

    public Long getAmount_decimal_2() {
        return amount_decimal_2;
    }

    public void setAmount_decimal_2(Long amount_decimal_2) {
        this.amount_decimal_2 = amount_decimal_2;
    }

    public Long getPrice_decimal_1() {
        return price_decimal_1;
    }

    public void setPrice_decimal_1(Long price_decimal_1) {
        this.price_decimal_1 = price_decimal_1;
    }

    public Long getPrice_decimal_2() {
        return price_decimal_2;
    }

    public void setPrice_decimal_2(Long price_decimal_2) {
        this.price_decimal_2 = price_decimal_2;
    }

    public String getIs_close_all() {
        return is_close_all;
    }

    public void setIs_close_all(String is_close_all) {
        this.is_close_all = is_close_all;
    }

    public String getKline_period() {
        return kline_period;
    }

    public void setKline_period(String kline_period) {
        this.kline_period = kline_period;
    }

    public BigDecimal getInit_line() {
        return init_line;
    }

    public void setInit_line(BigDecimal init_line) {
        this.init_line = init_line;
    }

    public BigDecimal getGrid_gap() {
        return grid_gap;
    }

    public void setGrid_gap(BigDecimal grid_gap) {
        this.grid_gap = grid_gap;
    }

    public BigDecimal getLow_limit_price() {
        return low_limit_price;
    }

    public void setLow_limit_price(BigDecimal low_limit_price) {
        this.low_limit_price = low_limit_price;
    }

    public BigDecimal getHigh_limit_price() {
        return high_limit_price;
    }

    public void setHigh_limit_price(BigDecimal high_limit_price) {
        this.high_limit_price = high_limit_price;
    }

    public String getDetail_info() {
        return detail_info;
    }

    public void setDetail_info(String detail_info) {
        this.detail_info = detail_info;
    }
}
