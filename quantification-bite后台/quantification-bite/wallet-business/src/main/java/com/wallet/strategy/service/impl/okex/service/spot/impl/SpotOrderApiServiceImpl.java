package com.wallet.strategy.service.impl.okex.service.spot.impl;

import com.wallet.strategy.service.impl.okex.bean.spot.param.OrderParamDto;
import com.wallet.strategy.service.impl.okex.bean.spot.param.PlaceOrderParam;
import com.wallet.strategy.service.impl.okex.bean.spot.result.BatchOrdersResult;
import com.wallet.strategy.service.impl.okex.bean.spot.result.Fills;
import com.wallet.strategy.service.impl.okex.bean.spot.result.OrderInfo;
import com.wallet.strategy.service.impl.okex.bean.spot.result.OrderResult;
import com.wallet.strategy.service.impl.okex.client.APIClient;
import com.wallet.strategy.service.impl.okex.config.APIConfiguration;
import com.wallet.strategy.service.impl.okex.service.spot.SpotOrderAPIServive;

import java.util.List;
import java.util.Map;

/**
 * 币币订单相关接口
 **/
public class SpotOrderApiServiceImpl implements SpotOrderAPIServive {
    private final APIClient client;
    private final SpotOrderAPI spotOrderAPI;

    public SpotOrderApiServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.spotOrderAPI = this.client.createService(SpotOrderAPI.class);
    }

	@Override
	public OrderResult addOrder(PlaceOrderParam order) {
		return this.client.executeSync(this.spotOrderAPI.addOrder(order));
	}

    @Override
    public Map<String, List<OrderResult>> addOrders(final List<PlaceOrderParam> order) {
        return this.client.executeSync(this.spotOrderAPI.addOrders(order));
    }

    @Override
    public OrderResult cancleOrderByOrderId(final PlaceOrderParam order, final String orderId) {
        return this.client.executeSync(this.spotOrderAPI.cancleOrderByOrderId(orderId, order));
    }
    @Override
    public OrderResult cancleOrderByOrderId_post(final PlaceOrderParam order, final String orderId) {
        return this.client.executeSync(this.spotOrderAPI.cancleOrderByOrderId_1(orderId, order));
    }

    @Override
    public Map<String, BatchOrdersResult> cancleOrders(final List<OrderParamDto> cancleOrders) {
        return this.client.executeSync(this.spotOrderAPI.batchCancleOrders(cancleOrders));
    }

    @Override
    public Map<String, BatchOrdersResult> cancleOrders_post(final List<OrderParamDto> cancleOrders) {
        return this.client.executeSync(this.spotOrderAPI.batchCancleOrders_1(cancleOrders));
    }

    @Override
    public Object getOrderByOrderId(final String product, final String orderId) {
        return this.client.executeSync(this.spotOrderAPI.getOrderByOrderId(orderId, product));
    }

    @Override
    public List<OrderInfo> getOrders(final String product, final String status, final String from, final String to, final String limit) {
        return this.client.executeSync(this.spotOrderAPI.getOrders(product, status, from, to, limit));
    }

    @Override
    public List<OrderInfo> getPendingOrders(final String from, final String to, final String limit, final String instrument_id) {
        return this.client.executeSync(this.spotOrderAPI.getPendingOrders(from, to, limit, instrument_id));
    }

    @Override
    public List<Fills> getFills(final String orderId, final String product, final String from, final String to, final String limit) {
        return this.client.executeSync(this.spotOrderAPI.getFills(orderId, product, from, to, limit));
    }

    

}
