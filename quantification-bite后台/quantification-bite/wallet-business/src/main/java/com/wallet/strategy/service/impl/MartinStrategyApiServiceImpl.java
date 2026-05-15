package com.wallet.strategy.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wallet.common.enums.Enum;
import com.wallet.common.enums.StrategyStatusEnum;
import com.wallet.common.exception.BaseException;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.JsonUtils;
import com.wallet.common.utils.MessageUtils;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.UserPlatform;
import com.wallet.strategy.domain.dto.MartinStrategyApiRedisDto;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.IUserPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.MartinStrategyApiMapper;
import com.wallet.strategy.domain.MartinStrategyApi;
import com.wallet.strategy.service.IMartinStrategyApiService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * api对应策略信息Service业务层处理
 * 
 * @author wallet
 * @date 2022-09-05
 */
@Service
public class MartinStrategyApiServiceImpl implements IMartinStrategyApiService 
{
    @Autowired
    private MartinStrategyApiMapper martinStrategyApiMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IUserPlatformService userPlatformService;

    /**
     * 查询api对应策略信息
     * 
     * @param id api对应策略信息ID
     * @return api对应策略信息
     */
    @Override
    public MartinStrategyApi selectMartinStrategyApiById(Long id)
    {
        return martinStrategyApiMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询api对应策略信息列表
     * 
     * @param martinStrategyApi api对应策略信息
     * @return api对应策略信息
     */
    @Override
    public List<MartinStrategyApi> selectMartinStrategyApiList(MartinStrategyApi martinStrategyApi)
    {
        return martinStrategyApiMapper.select(martinStrategyApi);
    }
	 @Override
    public List<MartinStrategyApi> findSelect(MartinStrategyApi martinStrategyApi)
    {
        return martinStrategyApiMapper.select(martinStrategyApi);
    }
    /**
     * 新增api对应策略信息
     * 
     * @param martinStrategyApi api对应策略信息
     * @return 结果
     */
    @Override
    public int insertMartinStrategyApi(MartinStrategyApi martinStrategyApi)
    {
		 return martinStrategyApiMapper.insertSelective(martinStrategyApi);
    }

    /**
     * 修改api对应策略信息
     * 
     * @param martinStrategyApi api对应策略信息
     * @return 结果
     */
    @Override
    public int updateMartinStrategyApi(MartinStrategyApi martinStrategyApi)
    {

        return martinStrategyApiMapper.updateByPrimaryKeySelective(martinStrategyApi);
    }

    /**
     * 开启
     * @param martinStrategyApi api对应策略信息
     * @return
     */
    @Override
    @Transactional
    public int startMartinStrategy(MartinStrategyApi martinStrategyApi) {
        MartinStrategyApi dto= martinStrategyApiMapper.selectByPrimaryKey(martinStrategyApi.getId());
        if(dto==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        if(martinStrategyApi.getBaseRate()==null ||
            martinStrategyApi.getBaseRate().compareTo(BigDecimal.ZERO)<1){//小于等于0
            throw new BaseException(MessageUtils.message("baseRate.not.null"));
        }
        dto.setId(martinStrategyApi.getId());
        dto.setBaseRate(martinStrategyApi.getBaseRate());
        dto.setStrategyStatus(StrategyStatusEnum.VALUE_1.getValue());
        int result = martinStrategyApiMapper.updateByPrimaryKeySelective(dto);
        if(result>0){
            MartinStrategyApiRedisDto redisDto = new MartinStrategyApiRedisDto();
            redisDto.setId(dto.getId());
            redisDto.setStrategy_id(dto.getStrategyId());
            redisDto.setStrategy_status(dto.getStrategyStatus());
            redisDto.setStrategy_type(dto.getStrategyType());
            redisDto.setAccount_id(dto.getAccountId());
            //查询对应apiId信息
            UserPlatform userPlatform = userPlatformService.selectUserPlatformById(dto.getAccountId());
            if(userPlatform==null){
                throw new BaseException(MessageUtils.message("api.key.is.null"));
            }
            redisDto.setApi_key(userPlatform.getAppkey());
            redisDto.setSecret_key(userPlatform.getAppsecret());
            redisDto.setExchange(userPlatform.getPlatformName());
            if(StringUtils.isNotBlank(userPlatform.getTradePassword())){
                redisDto.setPassphrase(userPlatform.getTradePassword());
            }
            String baseRate =dto.getBaseRate().stripTrailingZeros().toPlainString();
            redisDto.setBase_rate(new BigDecimal(baseRate));
            redisDto.setInit_usdt(userPlatform.getInitUsdt().stripTrailingZeros());
            redisUtil.hset(StrategyRedisConfig.MARTIN_strategy,String.valueOf(martinStrategyApi.getId()), JsonUtils.objectToJson(redisDto));
        }
        return result;
    }

    /**
     * 停止策略
     * @param martinStrategyApi api对应策略信息
     * @return
     */
    @Override
    public int stopMartinStrategy(MartinStrategyApi martinStrategyApi) {
        MartinStrategyApi dto= martinStrategyApiMapper.selectByPrimaryKey(martinStrategyApi.getId());
        if(dto==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        dto.setCrateTime(new Date());
        dto.setStrategyStatus(StrategyStatusEnum.VALUE_2.getValue());
        int result = martinStrategyApiMapper.updateByPrimaryKeySelective(dto);
        if(result>0){
            MartinStrategyApiRedisDto redisDto = new MartinStrategyApiRedisDto();
            redisDto.setId(dto.getId());
            redisDto.setStrategy_id(dto.getStrategyId());
            redisDto.setStrategy_status(dto.getStrategyStatus());
            redisDto.setStrategy_type(dto.getStrategyType());
            redisDto.setAccount_id(dto.getAccountId());
            //查询对应apiId信息
            UserPlatform userPlatform = userPlatformService.selectUserPlatformById(dto.getAccountId());
            if(userPlatform==null){
                throw new BaseException(MessageUtils.message("api.key.is.null"));
            }
            redisDto.setApi_key(userPlatform.getAppkey());
            redisDto.setSecret_key(userPlatform.getAppsecret());
            redisDto.setExchange(userPlatform.getPlatformName());
            if(StringUtils.isNotBlank(userPlatform.getTradePassword())){
                redisDto.setPassphrase(userPlatform.getTradePassword());
            }
            String baseRate =dto.getBaseRate().stripTrailingZeros().toPlainString();
            redisDto.setBase_rate(new BigDecimal(baseRate));
            redisDto.setInit_usdt(userPlatform.getInitUsdt().stripTrailingZeros());
            redisUtil.lLeftPush(StrategyRedisConfig.update_strategy,JsonUtils.objectToJson(redisDto));
        }
        return result;
    }

    @Override
    public int updateBaseRate(MartinStrategyApi martinStrategyApi) {
        MartinStrategyApi dto= martinStrategyApiMapper.selectByPrimaryKey(martinStrategyApi.getId());
        if(dto==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        if(martinStrategyApi.getBaseRate()==null ||
                martinStrategyApi.getBaseRate().compareTo(BigDecimal.ZERO)<1){//小于等于0
            throw new BaseException(MessageUtils.message("baseRate.not.null"));
        }
        dto.setCrateTime(new Date());
        dto.setBaseRate(martinStrategyApi.getBaseRate());
        int result = martinStrategyApiMapper.updateByPrimaryKeySelective(dto);
        if(result>0){
            MartinStrategyApiRedisDto redisDto = new MartinStrategyApiRedisDto();
            redisDto.setId(dto.getId());
            redisDto.setStrategy_id(dto.getStrategyId());
            redisDto.setStrategy_status(dto.getStrategyStatus());
            redisDto.setStrategy_type(dto.getStrategyType());
            redisDto.setAccount_id(dto.getAccountId());
            //查询对应apiId信息
            UserPlatform userPlatform = userPlatformService.selectUserPlatformById(dto.getAccountId());
            if(userPlatform==null){
                throw new BaseException(MessageUtils.message("api.key.is.null"));
            }
            redisDto.setApi_key(userPlatform.getAppkey());
            redisDto.setSecret_key(userPlatform.getAppsecret());
            redisDto.setExchange(userPlatform.getPlatformName());
            if(StringUtils.isNotBlank(userPlatform.getTradePassword())){
                redisDto.setPassphrase(userPlatform.getTradePassword());
            }
            String baseRate =dto.getBaseRate().stripTrailingZeros().toPlainString();
            redisDto.setBase_rate(new BigDecimal(baseRate));
            redisDto.setInit_usdt(userPlatform.getInitUsdt().stripTrailingZeros());
            redisUtil.lLeftPush(StrategyRedisConfig.update_strategy,JsonUtils.objectToJson(redisDto));
        }
        return result;
    }

    /**
     * 批量删除api对应策略信息
     * 
     * @param ids 需要删除的api对应策略信息ID
     * @return 结果
     */
    @Override
    public int deleteMartinStrategyApiByIds(Long[] ids)
    {
        return martinStrategyApiMapper.deleteMartinStrategyApiByIds(ids);
    }

    /**
     * 删除api对应策略信息信息
     * 
     * @param id api对应策略信息ID
     * @return 结果
     */
    @Override
    public int deleteMartinStrategyApiById(Long id)
    {
		return martinStrategyApiMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改策略类型
     * @return
     */
    @Override
    public int updateStrategyType(Long strategyId,Long strategyType) {
        Example example = new Example(MartinStrategyApi.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("strategyId",strategyId);
        MartinStrategyApi api = new MartinStrategyApi();
        api.setStrategyId(strategyId);
        api.setStrategyType(strategyType);
        return martinStrategyApiMapper.updateByExampleSelective(api,example);
    }

    /**
     * 删除
     * @param strategyId
     * @return
     */
    @Override
    public int deleteStrategyApi(Long strategyId) {
        Example example = new Example(MartinStrategyApi.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("strategyId",strategyId);
        MartinStrategyApi api = new MartinStrategyApi();
        api.setStrategyId(strategyId);
        api.setIsDelete(Enum.rule_validate.Y.getCode());
        return martinStrategyApiMapper.updateByExampleSelective(api,example);
    }
}
