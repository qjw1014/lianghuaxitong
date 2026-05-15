package com.wallet.strategy.service.impl.binance;


import com.wallet.common.exception.BaseException;
import com.wallet.strategy.domain.dto.PlatformKeyInfoDto;
import com.wallet.strategy.service.impl.okex.OkexV3Client;
import com.wallet.strategy.service.BourseService;
import com.wallet.strategy.service.impl.binance.domain.account.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.NestedServletException;

@Service("binance")
public class BinanceClient implements BourseService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(OkexV3Client.class);

    @Override
    public  boolean testConnect(PlatformKeyInfoDto dto) {
        try {
            BinanceApiClientFactory binanceApiClientFactory = BinanceApiClientFactory.newInstance(dto.getAppId(), dto.getSecret());
            BinanceApiRestClient client = binanceApiClientFactory.newRestClient();
            Account acount = client.getAccount();
            if (acount != null) {
                return true;
            } else {
                return false;
            }
        }catch (ExceptionInInitializerError ex){
            log.error("测试连接失败，e=%s,",ex);
            throw new BaseException("连接交易所失败，请稍后重试");
        }catch (Exception e){
            log.error("测试连接失败，e=%s,",e);
            throw new BaseException("连接交易所失败，请稍后重试");
        }
    }



    public static void main(String[] args) {
        try {
            BinanceClient client  = new BinanceClient();
            PlatformKeyInfoDto dto = new PlatformKeyInfoDto();
            dto.setAppId("tnn1X0bxByO5ZmWt5WoHe9U5OLOm99akRZI9c4rDs7XeiVYdl9JmROWpVJMudmN8");
            dto.setSecret("ysdrdJhdRnvOnCANPFpf4fks1oJRDNJuCHwzKcJ4AjlCjQg2oGJEV46IDmrivhyO");
            System.out.println(client.testConnect(dto));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
