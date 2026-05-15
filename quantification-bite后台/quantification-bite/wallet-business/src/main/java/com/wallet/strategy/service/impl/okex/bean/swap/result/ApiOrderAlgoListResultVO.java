package com.wallet.strategy.service.impl.okex.bean.swap.result;

import java.util.List;

public class ApiOrderAlgoListResultVO<T> {

    private List<PerOrderAlgoListResult> orderStrategyVOS;

    public List<PerOrderAlgoListResult> getOrderStrategyVOS() {
        return orderStrategyVOS;
    }

    public void setOrderStrategyVOS(List<PerOrderAlgoListResult> orderStrategyVOS) {
        this.orderStrategyVOS = orderStrategyVOS;
    }
}
