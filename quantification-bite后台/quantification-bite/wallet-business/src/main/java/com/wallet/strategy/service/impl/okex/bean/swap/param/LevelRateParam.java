package com.wallet.strategy.service.impl.okex.bean.swap.param;


public class LevelRateParam {

    /**
     * 1.LONG
     * 2.SHORT
     * 3.全仓杠杆
     */
    private String side;
    private String leverage;

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getLeverage() {
        return leverage;
    }

    public void setLeverage(String leverage) {
        this.leverage = leverage;
    }
}
