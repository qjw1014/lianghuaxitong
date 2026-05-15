package com.wallet.api.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wallet.common.enums.LanguageEnum;
import com.wallet.common.filter.RepeatedlyRequestWrapper;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 拦截器
 *
 * @ClassName: MyWebAppConfigurer
 * @author
 * @date 2018年6月3日
 *
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport implements Filter {
	HttpServletRequest req = null;

	@Autowired
	private RedisUtil redisUtil;
	/** 静态资源处理 **/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	/** 默认静态资源处理器 **/
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// super.configureDefaultServletHandling(configurer);
		// configurer.enable("stati");
		configurer.enable();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")// 设置允许跨域的路径
				.allowedOrigins("*")// 设置允许跨域请求的域名
				.allowCredentials(true)// 是否允许证书 不再默认开启
				.allowedMethods("GET", "POST", "PUT", "DELETE")// 设置允许的方法
				.maxAge(3600);// 跨域允许时间
	}


	@SuppressWarnings("deprecation")
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
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
		//fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		List<MediaType> mediaTypes = new ArrayList();
		mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);// 设定json格式且编码为UTF-8
		converter.setSupportedMediaTypes(mediaTypes);
		converter.setFastJsonConfig(fastJsonConfig);
		converters.add(converter);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ServletRequest requestWrapper = null;
		req= (HttpServletRequest) request;
		String language = req.getHeader("Accept-Language");
		redisUtil.set("language",findLanguage(language));
		if (request instanceof HttpServletRequest
				&& StringUtils.equalsAnyIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE))
		{
			requestWrapper = new RepeatedlyRequestWrapper((HttpServletRequest) request, response);
		}
		if (null == requestWrapper)
		{
			chain.doFilter(request, response);
		}
		else
		{
			chain.doFilter(requestWrapper, response);
		}
	}

	@Override
	public void destroy() {

	}

	/**
	 * 解析语言
	 * @param language
	 * @return
	 */
	public static String findLanguage(String language){
		if(StringUtils.isBlank(language)){
			language = LanguageEnum.zh_Hans.getInfo();
		}
		boolean success = LanguageEnum.enumMap.containsKey(language);
		if(success){
			return LanguageEnum.getValue(language);
		}
		return LanguageEnum.zh_Hans.getInfo();
	}
}
