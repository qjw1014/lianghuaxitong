package com.wallet.common.core.controller;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import com.wallet.common.core.domain.ErrMessage;
import com.wallet.common.enums.LanguageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wallet.common.constant.HttpStatus;
import com.wallet.common.core.domain.AjaxResult;
import com.wallet.common.core.page.PageDomain;
import com.wallet.common.core.page.TableDataInfo;
import com.wallet.common.core.page.TableSupport;
import com.wallet.common.utils.DateUtils;
import com.wallet.common.utils.StringUtils;
import com.wallet.common.utils.sql.SqlUtil;
import org.springframework.web.server.ServerWebExchange;

/**
 * web层通用数据处理
 * 
 * @author wallet
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    private final String acceptLanguage="Accept-Language";

    public String findLanguage(String language){
		if(StringUtils.isBlank(language)){
			language = LanguageEnum.zh_Hans.getInfo();
		}
		boolean success = LanguageEnum.enumMap.containsKey(language);
		if(success){
			return LanguageEnum.getValue(language);
		}
		return LanguageEnum.zh_Hans.getInfo();
	}

	/**
	 * 默认英文
	 * @param language
	 * @return
	 */
	public String findLanguageEn(String language){
		if(StringUtils.isBlank(language)){
			language = LanguageEnum.en_US.getInfo();
		}
		boolean success = LanguageEnum.enumMap.containsKey(language);
		if(success){
			return LanguageEnum.getValue(language);
		}
		return LanguageEnum.en_US.getInfo();
	}

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
	// Date 类型转换
	binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
	    @Override
	    public void setAsText(String text) {
		setValue(DateUtils.parseDate(text));
	    }
	});
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer pageNum = pageDomain.getPageNum();
		Integer pageSize = pageDomain.getPageSize();
		if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
			String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
			PageHelper.startPage(pageNum, pageSize, orderBy);
		}
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list) {
	TableDataInfo rspData = new TableDataInfo();
	rspData.setRows(list);
	rspData.setTotal(new PageInfo(list).getTotal());
	return rspData;
    }
    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list, long total) {
	TableDataInfo rspData = new TableDataInfo();
	rspData.setRows(list);
	rspData.setTotal(total);
	rspData.setCode(ErrMessage.CODE_SUCCESS);
	return rspData;
    }

    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
	return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
	return StringUtils.format("redirect:{}", url);
    }

    /**
     * 输出json文件到本地磁盘
     * 
     * @param jsonData
     * @param filePath
     * @return
     */
    public static boolean createJsonFile(Object jsonData, String filePath) {
	String content = JSON.toJSONString(jsonData, SerializerFeature.PrettyFormat,
		SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
	boolean flag = true;
	// 生成json格式文件
	try {
	    // 保证创建一个新文件
	    File file = new File(filePath);
	    if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
		file.getParentFile().mkdirs();
	    }
	    if (file.exists()) { // 如果已存在,删除旧文件
		file.delete();
	    }
	    file.createNewFile();
	    // 将格式化后的字符串写入文件
	    Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
	    write.write(content);
	    write.flush();
	    write.close();
	} catch (Exception e) {
	    flag = false;
	    e.printStackTrace();
	}
	return flag;
    }
}
