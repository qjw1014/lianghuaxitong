package com.wallet.strategy.service.impl.okex.bean.account.v5.param;

public class SetTheDisplayTypeOfGreeks {
    private String greeksType;

    public String getGreeksType() {
        return greeksType;
    }

    public void setGreeksType(String greeksType) {
        this.greeksType = greeksType;
    }

    @Override
    public String toString() {
        return "SetTheDisplayTypeOfGreeks{" +
                "greeksType='" + greeksType + '\'' +
                '}';
    }
}
