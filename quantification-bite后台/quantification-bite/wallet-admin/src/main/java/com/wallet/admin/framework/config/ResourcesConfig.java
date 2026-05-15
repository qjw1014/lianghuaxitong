package com.wallet.admin.framework.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wallet.admin.framework.interceptor.RepeatSubmitInterceptor;
import com.wallet.common.config.Config;
import com.wallet.common.constant.Constants;

/**
 * 通用配置
 * 
 * @author wallet
 */
@Configuration
public class ResourcesConfig extends WebMvcConfigurationSupport
{
    @Autowired
    private RepeatSubmitInterceptor repeatSubmitInterceptor;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + Config.getProfile() + "/");
        
        /** swagger配置 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
    }
    
    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter()
    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置访问源地址
        config.addAllowedOrigin("*");
        // 设置访问源请求头
        config.addAllowedHeader("*");
        // 设置访问源请求方法
        config.addAllowedMethod("*");
        // 对接口配置跨域设置
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullNumberAsZero,
            // List字段如果为null,输出为[],而非null
            SerializerFeature.WriteNullListAsEmpty,
            // 是否输出值为null的字段,默认为false
            SerializerFeature.WriteMapNullValue,
            // 字符串null返回空字符串
            SerializerFeature.WriteNullStringAsEmpty,
            // 空布尔值返回false
            SerializerFeature.WriteNullBooleanAsFalse,
            // 结果是否格式化,默认为false
            SerializerFeature.PrettyFormat);
        // 格式化日期
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        List<MediaType> mediaTypes = new ArrayList();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);// 设定json格式且编码为UTF-8
        converter.setSupportedMediaTypes(mediaTypes);
        converter.setFastJsonConfig(fastJsonConfig);
        converters.add(converter);
    }
}