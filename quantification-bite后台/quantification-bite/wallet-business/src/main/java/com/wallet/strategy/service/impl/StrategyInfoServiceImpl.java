package com.wallet.strategy.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.wallet.common.enums.Enum;
import com.wallet.common.enums.StrategyStatusEnum;
import com.wallet.common.exception.BaseException;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.DateUtils;
import com.wallet.common.utils.JsonUtils;
import com.wallet.common.utils.MessageUtils;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.MartinStrategyApi;
import com.wallet.strategy.domain.UserPlatform;
import com.wallet.strategy.domain.dto.StrategyInfoRedisDto;
import com.wallet.strategy.domain.vo.MartinStrategyInfoVO;
import com.wallet.strategy.domain.vo.MartinStrategyRedisInfoVO;
import com.wallet.strategy.domain.vo.MartinStrategySettingsPythonVO;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.IUserPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.StrategyInfoMapper;
import com.wallet.strategy.domain.StrategyInfo;
import com.wallet.strategy.service.IStrategyInfoService;
import tk.mybatis.mapper.entity.Example;

/**
 * 策略信息Service业务层处理
 * 
 * @author wallet
 * @date 2023-03-14
 */
@Service
public class StrategyInfoServiceImpl implements IStrategyInfoService 
{
    @Autowired
    private StrategyInfoMapper strategyInfoMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IUserPlatformService userPlatformService;


    /**
     * 查询策略信息
     * 
     * @param strategyId 策略信息ID
     * @return 策略信息
     */
    @Override
    public StrategyInfo selectStrategyInfoById(Long strategyId)
    {
        return strategyInfoMapper.selectByPrimaryKey(strategyId);
    }

    /**
     * 查询策略信息列表
     * 
     * @param strategyInfo 策略信息
     * @return 策略信息
     */
    @Override
    public List<StrategyInfo> selectStrategyInfoList(StrategyInfo strategyInfo)
    {
        Example example = new Example(StrategyInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if(strategyInfo.getStrategyId()!=null){
            criteria.andEqualTo("strategyId",strategyInfo.getStrategyId());
        }
        if(strategyInfo.getStrategyStatus()!=null){
            criteria.andEqualTo("strategyStatus",strategyInfo.getStrategyStatus());
        }
        if(StringUtils.isNotBlank(strategyInfo.getExchange())){
            criteria.andEqualTo("exchange",strategyInfo.getExchange());
        }
        if(strategyInfo.getApiAccountId()!=null){
            criteria.andEqualTo("apiAccountId",strategyInfo.getApiAccountId());
        }
        criteria.andEqualTo("isDelete",Enum.rule_validate.N.getCode());
        example.setOrderByClause("create_time desc");
        return strategyInfoMapper.selectByExample(example);
    }

	 @Override
    public List<StrategyInfo> findSelect(StrategyInfo strategyInfo)
    {
        return strategyInfoMapper.select(strategyInfo);
    }
    /**
     * 新增策略信息
     * 
     * @param strategyInfo 策略信息
     * @return 结果
     */
    @Override
    public int insertStrategyInfo(StrategyInfo strategyInfo)
    {
        //查询对应apiId信息
        UserPlatform userPlatform = userPlatformService.selectUserPlatformById(strategyInfo.getApiAccountId());
        if(userPlatform==null){
            throw new BaseException(MessageUtils.message("api.key.is.null"));
        }
         strategyInfo.setCreateTime(new Date());
		 int result = strategyInfoMapper.insertSelective(strategyInfo);
		 if(result>0){
		     this.setStrategyRedis(strategyInfo,userPlatform,"add");
         }
         return result;
    }

    /**
     * 存放值到redis
     */
    public void setStrategyRedis(StrategyInfo strategyInfo,UserPlatform userPlatform,String type){
        try {
            //添加数据到redis
            StrategyInfoRedisDto info = new StrategyInfoRedisDto();
            info.setStrategy_id(strategyInfo.getStrategyId());
            info.setStrategy_type(strategyInfo.getStrategyType());
            info.setStrategy_status(strategyInfo.getStrategyStatus());
            info.setExchange(strategyInfo.getExchange());
            info.setAccount_id(strategyInfo.getApiAccountId());
            info.setSymbol(strategyInfo.getSymbol());
            info.setCreateTime(strategyInfo.getCreateTime());
            info.setCoin(strategyInfo.getCoin());
            info.setBuyer(strategyInfo.getBuyer());
            info.setSeller(strategyInfo.getSeller());
            info.setValue(strategyInfo.getValue());
            info.setEvery_amount(strategyInfo.getEveryAmount());
            info.setTotal_amount(strategyInfo.getTotalAmount());
            info.setTrade_count(strategyInfo.getTradeCount());
            info.setTrade_count(strategyInfo.getTradeCount());
            info.setIs_maker(strategyInfo.getIsMaker());
            info.setLeverage(strategyInfo.getLeverage());
            info.setInit_usdt(strategyInfo.getInitUsdt());
            info.setEvery_volume(strategyInfo.getEveryVolume());
            info.setSymbol_1(strategyInfo.getSymbol1());
            info.setSymbol_2(strategyInfo.getSymbol2());
            info.setAmount_decimal_1(strategyInfo.getAmountDecimal1());
            info.setAmount_decimal_2(strategyInfo.getAmountDecimal2());
            info.setPrice_decimal_1(strategyInfo.getPriceDecimal1());
            info.setPrice_decimal_2(strategyInfo.getPriceDecimal2());
            info.setIs_close_all(strategyInfo.getIsCloseAll());
            info.setKline_period(strategyInfo.getKlinePeriod());
            info.setInit_line(strategyInfo.getInitLine());
            info.setGrid_gap(strategyInfo.getGridGap());
            info.setLow_limit_price(strategyInfo.getLowLimitPrice());
            info.setHigh_limit_price(strategyInfo.getHighLimitPrice());
            info.setDetail_info(strategyInfo.getDetailInfo());
            info.setAction(strategyInfo.getAction());
            if(userPlatform!=null){
                info.setApi_key(userPlatform.getAppkey());
                info.setSecret_key(userPlatform.getAppsecret());
                info.setPassphrase(userPlatform.getTradePassword());
            }
            String json = JsonUtils.objectToJson(info);
            if(type.equals("update")){
                //修改为新增到list
                redisUtil.lLeftPush(StrategyRedisConfig.update_strategy,json);
            }else{
                redisUtil.hset(StrategyRedisConfig.b_strategy_data,String.valueOf(strategyInfo.getStrategyId()),json);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改策略信息
     * 
     * @param strategyInfo 策略信息
     * @return 结果
     */
    @Override
    public int updateStrategyInfo(StrategyInfo strategyInfo)
    {
        StrategyInfo info = strategyInfoMapper.selectByPrimaryKey(strategyInfo.getStrategyId());
        if(info==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        //查询对应apiId信息
        UserPlatform userPlatform = userPlatformService.selectUserPlatformById(strategyInfo.getApiAccountId());
        if(userPlatform==null){
            throw new BaseException(MessageUtils.message("api.key.is.null"));
        }
        strategyInfo.setUpdateTime(new Date());
        int result = strategyInfoMapper.updateByPrimaryKeySelective(strategyInfo);
        if(result>0){
            this.setStrategyRedis(strategyInfo,userPlatform,"update");
        }
        return result;
    }

    /**
     * 修改策略状态
     * @param strategyInfo 策略信息
     * @return
     */
    @Override
    public int updateStrategyStatus(StrategyInfo strategyInfo) {
        StrategyInfo info = strategyInfoMapper.selectByPrimaryKey(strategyInfo.getStrategyId());
        if(info==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        info = new StrategyInfo();
        info.setStrategyId(strategyInfo.getStrategyId());
        if(strategyInfo.getStrategyStatus()==StrategyStatusEnum.VALUE_4.getValue()){//删除状态
            info.setIsDelete(Enum.rule_validate.Y.getCode());
        }
        info.setStrategyStatus(strategyInfo.getStrategyStatus());
        int result = strategyInfoMapper.updateByPrimaryKeySelective(info);
        if(result>0){
            Object object = redisUtil.hget(StrategyRedisConfig.b_strategy_data,String.valueOf(info.getStrategyId()));
            if(ObjectUtil.isNotNull(object)){
                JSONObject json = JSONObject.parseObject(object.toString());
                json.put("strategy_status",info.getStrategyStatus());
                String str =JsonUtils.objectToJson(json);
                //修改直接存到新的redis
                redisUtil.lLeftPush(StrategyRedisConfig.update_strategy,str);
//                redisUtil.hset(StrategyRedisConfig.b_strategy_data,String.valueOf(info.getStrategyId()),str);
            }
        }
        return result;
    }

    /**
     * 批量删除策略信息
     * 
     * @param strategyIds 需要删除的策略信息ID
     * @return 结果
     */
    @Override
    public int deleteStrategyInfoByIds(Long[] strategyIds)
    {
        return strategyInfoMapper.deleteStrategyInfoByIds(strategyIds);
    }

    /**
     * 删除策略信息信息
     * 
     * @param strategyId 策略信息ID
     * @return 结果
     */
    @Override
    public int deleteStrategyInfoById(Long strategyId)
    {
		return strategyInfoMapper.deleteByPrimaryKey(strategyId);
    }

    @Override
    public int updateDelete(Long strategyId) {
        StrategyInfo info = strategyInfoMapper.selectByPrimaryKey(strategyId);
        if(info==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        info = new StrategyInfo();
        info.setStrategyId(strategyId);
        info.setIsDelete(Enum.rule_validate.Y.getCode());
        info.setStrategyStatus(StrategyStatusEnum.VALUE_4.getValue());
        int result = strategyInfoMapper.updateByPrimaryKeySelective(info);
        if(result>0){
            Object object = redisUtil.hget(StrategyRedisConfig.settings,String.valueOf(strategyId));
            if(ObjectUtil.isNotNull(object)){
                JSONObject json = JSONObject.parseObject(object.toString());
                json.put("strategy_status",info.getStrategyStatus());
                String str =JsonUtils.objectToJson(json);
                //修改直接存到新的redis
                redisUtil.lLeftPush(StrategyRedisConfig.update_strategy,str);
//                redisUtil.hset(StrategyRedisConfig.settings,String.valueOf(info.getStrategyId()),str);
            }
        }
        return result;
    }
}
