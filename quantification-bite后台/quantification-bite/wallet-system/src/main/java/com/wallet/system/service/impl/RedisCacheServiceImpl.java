package com.wallet.system.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.DateUtils;
import com.wallet.system.service.IRedisCacheService;

/***
 * 缓存service
 * 
 * @author
 *
 */

@Service
public class RedisCacheServiceImpl implements IRedisCacheService{
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
	@Resource
	private RedisUtil redis;
	
	
	/***
	 * 控制并发的redis操作
	 * @param biz
	 * @param time 秒
	 * @return
	 */
	@Override
	public boolean setBizOperation(String biz,long time) {
		//如果redis的存储的key存在，返回操作频繁
		String bizRediskey=(String)redis.get(biz);
		if(StringUtils.isNotBlank(bizRediskey)) {
			return false;
		}
		Long ts=time;
		if(ts==null) {
			ts=0L;
		}
		//如果不存在，那就写入数据
		return redis.set(biz, biz,ts);
	}

	@Override
	public boolean setBizOperation(String biz, String value, long time) {
		//如果redis的存储的key存在，返回操作频繁
		String bizRediskey=(String)redis.get(biz);
		if(StringUtils.isNotBlank(bizRediskey)) {
			return false;
		}
		Long ts=time;
		if(ts==null) {
			ts=0L;
		}
		//如果不存在，那就写入数据
		return redis.set(biz, value,ts);
	}

	/***
	 * 控制并发操作完成，从redis里面清空
	 * @param biz
	 */
	@Override
	public void delBizOperation(String biz) {
		redis.del(biz);
	}
	
	@Override
	public void setSmsCode(String code, String mobile, String type) {

		try {
			redis.set("USER:SMS:CODE:" + type + ":" + mobile + ":count", "0", 10 * 60);
			redis.set("USER:SMS:CODE:" + type + ":" + mobile, code, 10 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	public String getSmsCode(String mobile, String type) {
		String code = StringUtils.EMPTY;
		try {
			String count = (String) redis.get("USER:SMS:CODE:" + type + ":" + mobile + ":count");
			if (StringUtils.isNotBlank(count)) {
				int nextCount = Integer.parseInt(count) + 1;
				if (nextCount >= 5) {
					redis.del("USER:SMS:CODE:" + type + ":" + mobile + ":count");
					redis.del("USER:SMS:CODE:" + type + ":" + mobile);
				}
				redis.set("USER:SMS:CODE:" + type + ":" + mobile + ":count", String.valueOf(nextCount),
						10 * 60);
			} else {
				redis.set("USER:SMS:CODE:" + type + ":" + mobile + ":count", "0", 10 * 60);
			}

			code = (String) redis.get("USER:SMS:CODE:" + type + ":" + mobile );

		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	/**
	 * 删除验证码
	 * 
	 * @param mobile
	 */
	@Override
	public void delSmsCode(String mobile, String type) {
		try {
			redis.del("USER:SMS:CODE:" + type + ":" + mobile + ":count");
			redis.del("USER:SMS:CODE:" + type + ":" + mobile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图形验证码
	 * 
	 * @param code
	 * @param mobile
	 */
	@Override
	public void setSmsImgCode(String code, String mobile) {
        try {
            redis.set("USER:IMG:CODE:" + mobile + ":count", "0", 10 * 60);
            redis.set("USER:IMG:CODE:" + mobile, code, 10 * 60);
            // System.out.println(redis.getExpire("USER:IMG:CODE:" + mobile));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	@Override
	public String getSmsImgCode(String mobile) {
        String code = StringUtils.EMPTY;
        try {
            String count = (String) redis.get("USER:IMG:CODE:" + mobile + ":count");
            if (StringUtils.isNotBlank(count)) {
                int nextCount = Integer.parseInt(count) + 1;
                if (nextCount >= 5) {
                    redis.del("USER:IMG:CODE:" +  mobile + ":count");
                    redis.del("USER:IMG:CODE:" + mobile);
                }
                redis.set("USER:IMG:CODE:" + mobile + ":count", String.valueOf(nextCount),
                        10 * 60);
            } else {
                redis.set("USER:IMG:CODE:" + mobile + ":count", "0", 10 * 60);
            }

            code = (String) redis.get("USER:IMG:CODE:" + mobile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
	}

	@Override
	public void delSmsImgCode(String mobile) {

        try {
            redis.del("USER:IMG:CODE:" + mobile + ":count");
            redis.del("USER:IMG:CODE:" + mobile);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	@Override
	public boolean flowControl(String key, Long max){
		Long total = 1L;
		try {
        	//明天凌晨时间戳
        	Long t1 = DateUtils.toDate(DateUtils.getFormatDateTommorrow(" 00:00:00"),"yyyy-MM-dd HH:mm:ss").getTime();
    		//当前时间戳
        	Long t2 = new Date().getTime();
        	//当前时间距离凌晨秒数
        	Integer t3 = Integer.valueOf((t1-t2)/1000 +"");
        	Integer val = (Integer) redis.get(key);
            if (val==null) {
                //jedisInstance是Jedis连接实例，可以使单链接也可以使用链接池获取，实现方式请参考之前的blog内容
                //如果redis目前没有这个key，创建并赋予0，有效时间为60s
            	redis.set(key, 1, t3);
            } else {
            	total = val.longValue();
            	//判断是否已超过最大值，超过则返回false
                if (total.compareTo(max) > 0) {
                    return false;
                }
                //获取加1后的值
                total = redis.incr(key,1);
                redis.expire(key, t3);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("流量控制组件:执行计数操作失败,无法执行计数");
        }
        return true;
    }

	@Override
	public boolean set(String key, Object value, long time) {
		
		return redis.set(key, value, time);
	}

	@Override
	public Object get(String key) {
		return redis.get(key);
	}

	@Override
	public boolean setValue(String key, Object value) {
		return redis.set(key,value);
	}
}
