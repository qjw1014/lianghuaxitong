package com.wallet.system.service;

public interface IRedisCacheService {
	public boolean setBizOperation(String biz,long time);

	public boolean setBizOperation(String biz,String value,long time);


	/***
	 * 控制并发操作完成，从redis里面清空
	 * @param biz
	 */
	public void delBizOperation(String biz);
	

	public void setSmsCode(String code, String mobile, String type);

	public String getSmsCode(String mobile, String type);

	/**
	 * 删除验证码
	 * 
	 * @param mobile
	 */
	public void delSmsCode(String mobile, String type);

	/**
	 * 图形验证码
	 * 
	 * @param code
	 * @param mobile
	 */
	public void setSmsImgCode(String code, String mobile) ;

	public String getSmsImgCode(String mobile);

	public void delSmsImgCode(String mobile);
	public boolean flowControl(String key, Long max);
	public boolean set(String key, Object value, long time);
	public Object get(String key);


	/**
	 * 缓存
	 */
	public boolean setValue(String key, Object value);
}
