package com.wallet.strategy.service.impl.okex.client;

import com.alibaba.fastjson.JSON;
import com.wallet.strategy.service.impl.okex.bean.ett.result.CursorPager;
import com.wallet.strategy.service.impl.okex.bean.futures.HttpContractResult;
import com.wallet.strategy.service.impl.okex.bean.futures.HttpResult;
import com.wallet.strategy.service.impl.okex.config.APIConfiguration;
import com.wallet.strategy.service.impl.okex.constant.APIConstants;
import com.wallet.strategy.service.impl.okex.enums.HttpHeadersEnum;
import com.wallet.strategy.service.impl.okex.exception.APIException;
import com.wallet.strategy.service.impl.okex.exception.APITimeoutException;
import com.wallet.strategy.service.impl.okex.utils.DateUtils;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Optional;

/**
 * OKEX API Client
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/3/8 13:43
 */
public class APIClient {

    private static final Logger LOG = LoggerFactory.getLogger(APIClient.class);

    private final APIConfiguration config;
    private final APICredentials credentials;
    private final OkHttpClient client;
    private final Retrofit retrofit;
    private final ApiHttp apiHttp;

    /**
     * Initialize the apis client
     */
    public APIClient(final APIConfiguration config) {
        if (config == null || StringUtils.isEmpty(config.getEndpoint())) {
            throw new RuntimeException("The APIClient params can't be empty.");
        }
        this.config = config;
        this.credentials = new APICredentials(config);
        this.client = new APIHttpClient(config, this.credentials).client();
        this.retrofit = new APIRetrofit(config, this.client).retrofit();
        this.apiHttp = new ApiHttp(config, this.client);
    }

    /**
     * Initialize the retrofit operation service
     */
    public <T> T createService(final Class<T> service) {
        return this.retrofit.create(service);
    }

    public ApiHttp getApiHttp() {
        return this.apiHttp;
    }

    /**
     * Synchronous send request
     */
    public <T> T executeSync(final Call<T> call) {
        try {
            final Response<T> response = call.execute();
            if (this.config.isPrint()) {
                this.printResponse(response);
            }
            final int status = response.code();
            final String message = new StringBuilder().append(response.code()).append(" / ").append(response.message()).toString();
            if (response.isSuccessful()) {
                return response.body();
            } else if (APIConstants.resultStatusArray.contains(status)) {
                String text = response.errorBody().string();
                final HttpResult result = JSON.parseObject(text, HttpResult.class);
                if(result.getCode()==0 && result.getMessage()==null){
                    final HttpContractResult contractResult = JSON.parseObject(text, HttpContractResult.class);
                    throw new APIException(contractResult.getError_code(), contractResult.getError_message());
                }else {
                    throw new APIException(result.getCode(), result.getMessage());
                }
            } else {
                LOG.debug("okex错误错误错误"+message);
                throw new APIException(message);
            }
        } catch (final SocketTimeoutException e) {
            throw new APITimeoutException("连接不通", e);
        } catch (final ConnectException e) {
            throw new APITimeoutException("连接不通", e);
        } catch (final IOException e) {
            throw new APIException(e.getMessage(), e);
        }
    }

    /**
     * Synchronous send request
     */
    public <T> CursorPager<T> executeSyncCursorPager(final Call<List<T>> call) {
        try {
            final Response<List<T>> response = call.execute();
            if (this.config.isPrint()) {
                this.printResponse(response);
            }
            final int status = response.code();
            final String message = response.code() + " / " + response.message();
            if (response.isSuccessful()) {
                final Headers headers = response.headers();
                final CursorPager<T> cursorPager = new CursorPager<>();
                cursorPager.setData(response.body());
                cursorPager.setBefore(headers.get("OK-BEFORE"));
                cursorPager.setAfter(headers.get("OK-AFTER"));
                cursorPager.setLimit(Optional.ofNullable(headers.get("OK-LIMIT")).map(Integer::valueOf).orElse(100));
                return cursorPager;
            }
            if (APIConstants.resultStatusArray.contains(status)) {
                final HttpResult result = JSON.parseObject(new String(response.errorBody().bytes()), HttpResult.class);
                throw new APIException(result.getCode(), result.getMessage());
            }
            throw new APIException(message);
        } catch (final IOException e) {
            throw new APIException("APIClient executeSync exception.", e);
        }
    }

    private void printResponse(final Response response) {
        final StringBuilder responseInfo = new StringBuilder();
        responseInfo.append("\n\tResponse").append("(").append(DateUtils.timeToString(null, 4)).append("):");
        if (response != null) {
            final String limit = response.headers().get(HttpHeadersEnum.OK_LIMIT.header());
            if (StringUtils.isNotEmpty(limit)) {
                responseInfo.append("\n\t\t").append("Headers: ");
//                responseInfo.append("\n\t\t\t").append(HttpHeadersEnum.OK_BEFORE.header()).append(": ").append(response.headers().get(HttpHeadersEnum.OK_BEFORE.header()));
//                responseInfo.append("\n\t\t\t").append(HttpHeadersEnum.OK_AFTER.header()).append(": ").append(response.headers().get(HttpHeadersEnum.OK_AFTER.header()));
                responseInfo.append("\n\t\t\t").append(HttpHeadersEnum.OK_FROM.header()).append(": ").append(response.headers().get(HttpHeadersEnum.OK_FROM.header()));
                responseInfo.append("\n\t\t\t").append(HttpHeadersEnum.OK_TO.header()).append(": ").append(response.headers().get(HttpHeadersEnum.OK_TO.header()));
                responseInfo.append("\n\t\t\t").append(HttpHeadersEnum.OK_LIMIT.header()).append(": ").append(limit);
            }
            responseInfo.append("\n\t\t").append("Status: ").append(response.code());
            responseInfo.append("\n\t\t").append("Message: ").append(response.message());
            responseInfo.append("\n\t\t").append("Body: ").append(JSON.toJSONString(response.body()));
        } else {
            responseInfo.append("\n\t\t").append("\n\tRequest Error: response is null");
        }
        APIClient.LOG.info(responseInfo.toString());
    }
}
