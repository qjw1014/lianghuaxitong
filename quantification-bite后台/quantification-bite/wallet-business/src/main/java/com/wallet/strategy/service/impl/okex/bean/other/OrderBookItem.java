package com.wallet.strategy.service.impl.okex.bean.other;

public interface OrderBookItem<T> {
    String getPrice();

    T getSize();
}
