package com.wallet.strategy.service.impl.okex;
import com.wallet.common.exception.BaseException;
import com.wallet.err.OkexErrMessage;
import com.wallet.strategy.domain.dto.PlatformKeyInfoDto;
import com.wallet.strategy.service.BourseService;
import com.wallet.strategy.service.impl.okex.config.APIConfiguration;
import com.wallet.strategy.service.impl.okex.exception.APIException;
import com.wallet.strategy.service.impl.okex.exception.APITimeoutException;
import com.wallet.strategy.service.impl.okex.service.v5.account.AccountAPIService;
import com.wallet.strategy.service.impl.okex.service.v5.account.impl.AccountAPIServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("okex")
public class OkexV3Client implements BourseService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(OkexV3Client.class);

    private String mPlatform = "okex";

    @Value("${platform.okex_api_url}")
    private  String okexApiUrl;


    @Override
    public boolean testConnect(PlatformKeyInfoDto dto) throws BaseException {
        try {
                APIConfiguration config = new APIConfiguration(dto.getAppId(),dto.getSecret(),dto.getPassword(),okexApiUrl);
                //v5接口
                AccountAPIService accountAPIService = new AccountAPIServiceImpl(config);
                com.alibaba.fastjson.JSONObject result = accountAPIService.getBalance("USDT");
                log.info(mPlatform+"接入api:"+dto.getAppId()+",返回信息%s",result);
                if(!result.getString("code").equals("0")){
                    throw new BaseException("连接交易所失败，请稍后重试");
                }else{
                    return true;
                }
            } catch (APITimeoutException e) {
                e.printStackTrace();
                log.error(mPlatform+"接入api:"+dto.getAppId()+",返回错误信息：平台接口连接不通",e);
                throw new BaseException("连接交易所失败，请稍后重试");
            } catch (APIException e) {
                log.error(mPlatform+"接入api,api:"+dto.getAppId()+",返回错误信息："+OkexErrMessage.OkexMessage.get(String.valueOf(e.getCode())),e);
                e.printStackTrace();
                throw new BaseException(OkexErrMessage.error(String.valueOf(e.getCode())));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("测试连接失败，e=%s,", e);
                throw new BaseException("连接交易所失败，请稍后重试");
            }
    }


    public static void main(String[] args) throws BaseException{
        OkexV3Client okexV3Client = new OkexV3Client();
        PlatformKeyInfoDto dto = new PlatformKeyInfoDto();
        dto.setAppId("3eb8be9c-e077-48ec-9aff-5616ccf5933d");
        dto.setPassword("Yu123456@");
        dto.setSecret("0D1D3DADAF121A455A4E58E661035046");
        System.out.println(okexV3Client.testConnect(dto));
    }
}
