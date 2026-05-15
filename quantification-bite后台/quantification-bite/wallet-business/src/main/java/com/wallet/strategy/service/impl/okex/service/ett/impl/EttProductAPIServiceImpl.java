package com.wallet.strategy.service.impl.okex.service.ett.impl;

import com.wallet.strategy.service.impl.okex.bean.ett.result.EttConstituentsResult;
import com.wallet.strategy.service.impl.okex.bean.ett.result.EttSettlementDefinePrice;
import com.wallet.strategy.service.impl.okex.client.APIClient;
import com.wallet.strategy.service.impl.okex.config.APIConfiguration;
import com.wallet.strategy.service.impl.okex.service.ett.EttProductAPIService;

import java.util.List;

/**
 * @author chuping.cui
 * @date 2018/7/5
 */
public class EttProductAPIServiceImpl implements EttProductAPIService {

    private final APIClient client;
    private final EttProductAPI api;

    public EttProductAPIServiceImpl(APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(EttProductAPI.class);
    }

    @Override
    public EttConstituentsResult getConstituents(String ett) {
        return this.client.executeSync(this.api.getConstituents(ett));
    }

    @Override
    public List<EttSettlementDefinePrice> getDefinePrice(String ett) {
        return this.client.executeSync(this.api.getDefinePrice(ett));
    }
}
