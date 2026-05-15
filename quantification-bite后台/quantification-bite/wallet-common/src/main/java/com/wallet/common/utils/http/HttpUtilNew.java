package com.wallet.common.utils.http;

import com.wallet.common.utils.JsonUtils;
import com.wallet.common.utils.MapUtils;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * http工具类
 *
 * @author chenle
 */
public abstract class HttpUtilNew {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private static OkHttpClient okHttpClient;
    
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final int timeout = 60000;

    static {
        okHttpClient = new OkHttpClient().newBuilder().readTimeout(timeout, TimeUnit.MILLISECONDS).writeTimeout(timeout, TimeUnit.MILLISECONDS).connectTimeout(timeout, TimeUnit.MILLISECONDS).build();
    }
    
    public static void main(String[] args) {
        try {
//            String str = post("http://127.0.0.1:9100/member/order/pageList111.do");
            String str = get("https://trx.tokenview.com/api/trx/address/normal/TBk72yKTJWppCsa7XrsU7Qhfo4yNvbm8rE/1/20");
            // System.out.println("-----------输出值-----------str值=" + str + "," + "当前类=HttpUtils.main()");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 执行给定的链接
     *
     * @param url 链接
     * @return 链接返回的结果
     * @throws Exception
     */
    public static String get(String url) throws Exception {
        return get(url,null,null);
    }
    
    /**
     * 执行给定的链接
     *
     * @param url 链接
     * @param params 参数
     * @return 链接返回的结果
     * @throws Exception
     */
    public static String get(String url,Map<String,Object> params) {
        return get(url,params,null);
    }
    
    /**
     * get方式请求
     * @param url
     * @param params 参数
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String,Object> params,Map<String,String> headers){
        Response response = null;
        ResponseBody body = null;
        try {
            Request.Builder builder = getBuilder(headers);
            log.info("开始调用链接url="+url);
            //添加参数信息
            if (MapUtils.isNotEmpty(params)) {
                StringBuilder param = new StringBuilder();
                param.append(url.indexOf("?")>0?"&":"?");
                param.append(MapUtils.toString(params));
                url+=param.toString();
            }
            Request request = builder.url(url).build();
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                body = response.body();
                return body != null ? body.string() : null;
            };

        }catch (Exception e){
            e.printStackTrace();
            log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + JsonUtils.objectToJson(params), e);
        }
        finally {
            close(body);
            close(response);
        }
        return null;
    }
    
    /**
     * POST提交Json数据
     * @param url 连接
     * @param json Json数据
     * @param headers
     * @return
     * @throws Exception
     */
    public static String postJson(String url,String json,Map<String,String> headers) throws Exception{
        Response response = null;
        ResponseBody body = null;
        try {
            RequestBody requestBody = RequestBody.create(JSON, json);
            Request.Builder builder = getBuilder(headers);
            log.info("%s,开始调用链接url=%s","post", url);
            Request request = builder.url(url).post(requestBody).build();
            response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            body = response.body();
            return body != null ? body.string() : null;
        }
        finally {
            close(body);
            close(response);
        }
    }
    
    /**
     * post表单形式提交
     *
     * @param url 链接
     * @return 链接返回的结果
     * @throws Exception
     */
    public static String post(String url) throws Exception {
        return post(url,null,null);
    }
    
    /**
     * post表单形式提交
     *
     * @param url 链接
     * @param params 参数
     * @return 链接返回的结果
     * @throws Exception
     */
    public static String post(String url,Map<String,Object> params) throws Exception {
        return post(url,params,null);
    }
    
    /**
     * post表单形式提交
     * @param url
     * @param params 参数
     * @param headers 头部
     * @return
     * @throws Exception
     */
    public static String post(String url, Map<String,Object> params,Map<String,String> headers) throws Exception{
        Response response = null;
        ResponseBody body = null;
        try {
            FormBody formBody = new FormBody.Builder().build();
            if(MapUtils.isNotEmpty(params)){
                FormBody.Builder builder =  new FormBody.Builder();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    builder.add(entry.getKey(),String.valueOf(entry.getValue()));
                }
                formBody = builder.build();
            }
            Request.Builder builder = getBuilder(headers);
            log.info("%s,开始调用链接url=%s","post", url);
            Request request = builder.url(url).post(formBody).build();
            response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            body = response.body();
            return body != null ? body.string() : null;
        }
        finally {
            close(body);
            close(response);
        }
    }
    
    /**
     * 获取Builder
     * @param headers
     * @return
     */
    public static Request.Builder getBuilder(Map<String,String> headers){
        Request.Builder builder = new Request.Builder();
        //添加头部信息
        if(MapUtils.isNotEmpty(headers)){
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(),entry.getValue());
            }
        }
        return builder;
    }

    public static String pythonPost(String url, Map<String, String> params,String interfaceName) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            url = "http://"+url+"/"+interfaceName;
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            if (params != null) {
                out.write(paramMapToString(params));
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            ////// System.out.println(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String paramMapToString(Map<String, String> params){
        StringBuilder param = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if(param.length()>0){
                param.append("&");
            }
            param.append(entry.getKey());
            param.append("=");
            param.append(entry.getValue());
        }
        return param.toString();
    }


    /**
     * 关闭资源
     *
     * @param closeable 可关闭的资源对象
     */
    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            }
            catch (Exception e) {
                log.error("关闭资源时发生异常", e);
            }
        }
    }
}
