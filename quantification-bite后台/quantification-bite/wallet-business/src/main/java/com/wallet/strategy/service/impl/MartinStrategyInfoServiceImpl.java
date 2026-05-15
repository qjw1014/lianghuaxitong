package com.wallet.strategy.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.wallet.common.constant.Constants;
import com.wallet.common.enums.Enum;
import com.wallet.common.enums.StrategyStatusEnum;
import com.wallet.common.exception.BaseException;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.DateUtils;
import com.wallet.common.utils.JsonUtils;
import com.wallet.common.utils.MessageUtils;
import com.wallet.common.utils.StringUtils;
import com.wallet.common.utils.id.IDGenerater;
import com.wallet.strategy.domain.*;
import com.wallet.strategy.domain.vo.MartinStrategyInfoVO;
import com.wallet.strategy.domain.vo.MartinStrategyRedisInfoVO;
import com.wallet.strategy.domain.vo.MartinStrategySettingsPythonVO;
import com.wallet.strategy.mapper.CoinInfoMapper;
import com.wallet.strategy.mapper.MartinStrategySettingsMapper;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.IMartinStrategyApiService;
import com.wallet.strategy.service.IUserPlatformService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.MartinStrategyInfoMapper;
import com.wallet.strategy.service.IMartinStrategyInfoService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 策略信息Service业务层处理
 * 
 * @author wallet
 * @date 2022-08-31
 */
@Service
public class MartinStrategyInfoServiceImpl implements IMartinStrategyInfoService 
{
    @Autowired
    private MartinStrategyInfoMapper martinStrategyInfoMapper;
    @Autowired
    private MartinStrategySettingsMapper martinStrategySettingsMapper;
    @Autowired
    private CoinInfoMapper coinInfoMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IMartinStrategyApiService martinStrategyApiService;
    @Autowired
    private IUserPlatformService userPlatformService;


    /**
     * 查询策略信息
     * 
     * @param strategyId 策略信息ID
     * @return 策略信息
     */
    @Override
    public MartinStrategyInfoVO selectMartinStrategyInfoById(Long strategyId)
    {
        MartinStrategyInfoVO vo = new MartinStrategyInfoVO();
        MartinStrategyInfo martinStrategyInfo = martinStrategyInfoMapper.selectByPrimaryKey(strategyId);
        if(martinStrategyInfo!=null){
            BeanUtils.copyProperties(martinStrategyInfo,vo);
            MartinStrategySettings settings = new MartinStrategySettings();
            settings.setStrategyId(martinStrategyInfo.getStrategyId());
            List<MartinStrategySettings> settingsList = martinStrategySettingsMapper.select(settings);
            vo.setRowList(settingsList);
            //查询
//            Object object = redisUtil.hget(StrategyRedisConfig.settings,String.valueOf(martinStrategyInfo.getStrategyId()));
//            if(object!=null && ObjectUtil.isNotNull(object)){
//                MartinStrategyRedisInfoVO redisInfoVO = JsonUtils.jsonToObject(object.toString(),MartinStrategyRedisInfoVO.class);
//            }
        }
        return vo;
    }

    /**
     * 查询策略信息列表
     * 
     * @param martinStrategyInfo 策略信息
     * @return 策略信息
     */
    @Override
    public List<MartinStrategyInfo> selectMartinStrategyInfoList(MartinStrategyInfo martinStrategyInfo)
    {
        return martinStrategyInfoMapper.select(martinStrategyInfo);
    }

    /**
     * 排序查询列表
     * @param martinStrategyInfo 策略信息
     * @return
     */
    @Override
    public List<MartinStrategyInfo> selectMartinStrategyInfoListOrderBy(MartinStrategyInfo martinStrategyInfo) {
        Example example = new Example(MartinStrategyInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(martinStrategyInfo.getCoin())){
            criteria.andEqualTo("coin",martinStrategyInfo.getCoin());
        }
        if(StringUtils.isNotBlank(martinStrategyInfo.getCommon())){
            criteria.andEqualTo("common",martinStrategyInfo.getCommon());
        }
        if(StringUtils.isNotBlank(martinStrategyInfo.getLongCount())){
            criteria.andEqualTo("longCount",martinStrategyInfo.getLongCount());
        }
        if(StringUtils.isNotBlank(martinStrategyInfo.getLongOpen())){
            criteria.andEqualTo("longOpen",martinStrategyInfo.getLongOpen());
        }
        if(StringUtils.isNotBlank(martinStrategyInfo.getPlatform())){
            criteria.andEqualTo("platform",martinStrategyInfo.getPlatform());
        }
        if(StringUtils.isNotBlank(martinStrategyInfo.getShortCount())){
            criteria.andEqualTo("shortCount",martinStrategyInfo.getShortCount());
        }
        if(StringUtils.isNotBlank(martinStrategyInfo.getShortOpen())){
            criteria.andEqualTo("shortOpen",martinStrategyInfo.getShortOpen());
        }
        if(StringUtils.isNotBlank(martinStrategyInfo.getSymbol())){
            criteria.andEqualTo("symbol",martinStrategyInfo.getSymbol());
        }
        if(martinStrategyInfo.getStrategyId()!=null){
            criteria.andEqualTo("strategyId",martinStrategyInfo.getStrategyId());
        }
        criteria.andNotEqualTo("strategyStatus",StrategyStatusEnum.VALUE_3.getValue());
        criteria.andEqualTo("isDelete",Enum.rule_validate.N.getCode());
        return martinStrategyInfoMapper.selectByExample(example);
    }

    @Override
    public List<MartinStrategyInfo> findSelect(MartinStrategyInfo martinStrategyInfo)
    {
        return martinStrategyInfoMapper.select(martinStrategyInfo);
    }
    /**
     * 新增策略信息
     * 
     * @param martinStrategyInfo 策略信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertMartinStrategyInfo(MartinStrategyInfoVO martinStrategyInfo)
    {
         martinStrategyInfo.setCrateTime(new Date());
         martinStrategyInfo.setUpdateTime(new Date());
         MartinStrategyInfo addDto = new MartinStrategyInfo();
         BeanUtils.copyProperties(martinStrategyInfo,addDto);
         addDto.setStrategyId(IDGenerater.nextId());
        //判断币种等于临时币种
        if(martinStrategyInfo.getCoin().equalsIgnoreCase(Constants.COIN_FIXED)){
            if(StringUtils.isNotBlank(martinStrategyInfo.getCoinFixed())){//不等于null
                //修改临时币种
                CoinInfo coinInfo = new CoinInfo();
                coinInfo.setIsFixed(Enum.rule_validate.Y.getCode());
                coinInfo.setName(martinStrategyInfo.getCoinFixed());
                coinInfoMapper.updateCoinFixed(coinInfo);
                addDto.setCoin(martinStrategyInfo.getCoinFixed());
            }else{
                throw new BaseException("请填写临时币种");
            }
        }
         martinStrategyInfo.setCoin(addDto.getCoin());
         int result = martinStrategyInfoMapper.insertSelective(addDto);
         if(result>0){
             martinStrategyInfo.setStrategyId(addDto.getStrategyId());
             List<MartinStrategySettingsPythonVO> listPython = new ArrayList<>();
             List<MartinStrategySettings> list = martinStrategyInfo.getRowList();
             if(list!=null && list.size()>0){
                for(MartinStrategySettings settings:list){
                    settings.setStrategyId(addDto.getStrategyId());
                    settings.setCreateTime(new Date());
                    martinStrategySettingsMapper.insertSelective(settings);
                    MartinStrategySettingsPythonVO dto = new MartinStrategySettingsPythonVO();
                    dto.setAdd_amount_multiple(settings.getAddAmountMultiple());
                    dto.setAdd_price_rate(settings.getAddPriceRate());
                    dto.setLong_stop_rate(settings.getLongStopRate());
                    dto.setLoss_rate(settings.getLossRate());
                    dto.setShort_stop_rate(settings.getShortStopRate());
                    dto.setStrategyId(addDto.getStrategyId());
                    listPython.add(dto);
                }
             }
             //查询全部api信息
             UserPlatform query = new UserPlatform();
             query.setIsDelete(Enum.rule_validate.N.getCode());
             List<UserPlatform> userList = userPlatformService.selectUserPlatformList(query);
             if(userList!=null && userList.size()>0){
                 for(UserPlatform userPlatform:userList){
                     //对应api也添加策略信息
                     MartinStrategyApi strategyApi = new MartinStrategyApi();
                     strategyApi.setAccountId(userPlatform.getId());
                     strategyApi.setIsDelete(Enum.rule_validate.N.getCode());
                     strategyApi.setCrateTime(new Date());
                     strategyApi.setStrategyId(addDto.getStrategyId());
                     strategyApi.setStrategyStatus(martinStrategyInfo.getStrategyStatus());
                     strategyApi.setStrategyType(martinStrategyInfo.getStrategyType());
                     martinStrategyApiService.insertMartinStrategyApi(strategyApi);
                 }
             }
             setStrategyRedis(martinStrategyInfo,listPython);
         }
         return result;
    }

    /**
     * 修改策略信息
     * 
     * @param martinStrategyInfo 策略信息
     * @return 结果
     */
    @Override
    public int updateMartinStrategyInfo(MartinStrategyInfoVO martinStrategyInfo)
    {
        MartinStrategyInfo info = martinStrategyInfoMapper.selectByPrimaryKey(martinStrategyInfo.getStrategyId());
        if(info==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        martinStrategyInfo.setUpdateTime(new Date());
        int result = martinStrategyInfoMapper.updateByPrimaryKeySelective(martinStrategyInfo);
        if(result>0){
            //判断类型是否有修改
            if(!info.getStrategyType().equals(martinStrategyInfo.getStrategyType())){
                //修改对应的api策略数据
                martinStrategyApiService.updateStrategyType(info.getStrategyId(),martinStrategyInfo.getStrategyType());
            }
            //先删除补仓配置数据
            martinStrategySettingsMapper.deleteMartinStrategySettingsByStrategyId(martinStrategyInfo.getStrategyId());
            List<MartinStrategySettingsPythonVO> listPython = new ArrayList<>();
            List<MartinStrategySettings> list = martinStrategyInfo.getRowList();
            if(list!=null && list.size()>0){
                for(MartinStrategySettings settings:list){
                    settings.setStrategyId(martinStrategyInfo.getStrategyId());
                    martinStrategySettingsMapper.insertSelective(settings);
                    MartinStrategySettingsPythonVO dto = new MartinStrategySettingsPythonVO();
                    dto.setAdd_amount_multiple(settings.getAddAmountMultiple());
                    dto.setAdd_price_rate(settings.getAddPriceRate());
                    dto.setLong_stop_rate(settings.getLongStopRate());
                    dto.setLoss_rate(settings.getLossRate());
                    dto.setShort_stop_rate(settings.getShortStopRate());
                    dto.setStrategyId(martinStrategyInfo.getStrategyId());
                    listPython.add(dto);
                }
            }
            setStrategyRedis(martinStrategyInfo,listPython);
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
    public int deleteMartinStrategyInfoByIds(Long[] strategyIds)
    {
        return martinStrategyInfoMapper.deleteMartinStrategyInfoByIds(strategyIds);
    }

    /**
     * 删除策略信息信息
     * 
     * @param strategyId 策略信息ID
     * @return 结果
     */
    @Override
    public int deleteMartinStrategyInfoById(Long strategyId)
    {
		return martinStrategyInfoMapper.deleteByPrimaryKey(strategyId);
        
    }

    /**
     * 业务删除策略信息
     *
     * @param strategyId 策略信息ID
     * @return 结果
     */
    @Override
    public int updateDelete(Long strategyId)
    {
        MartinStrategyInfo info = martinStrategyInfoMapper.selectByPrimaryKey(strategyId);
        if(info==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        info = new MartinStrategyInfo();
        info.setStrategyId(strategyId);
        info.setIsDelete(Enum.rule_validate.Y.getCode());
        info.setStrategyStatus(StrategyStatusEnum.VALUE_3.getValue());
        int result = martinStrategyInfoMapper.updateByPrimaryKeySelective(info);
        if(result>0){
            Object object = redisUtil.hget(StrategyRedisConfig.settings,String.valueOf(strategyId));
            if(ObjectUtil.isNotNull(object)){
                JSONObject json = JSONObject.parseObject(object.toString());
                json.put("strategyStatus",info.getStrategyStatus());
                String str =JsonUtils.objectToJson(json);
                redisUtil.hset(StrategyRedisConfig.settings,String.valueOf(info.getStrategyId()),str);
            }
            //删除api信息
            martinStrategyApiService.deleteStrategyApi(strategyId);
        }
        return result;
    }

    /**
     * 存放值到redis
     * @param martinStrategyInfo
     */
    public void setStrategyRedis(MartinStrategyInfoVO martinStrategyInfo,List<MartinStrategySettingsPythonVO> listPyhton){
        MartinStrategyRedisInfoVO martin = new MartinStrategyRedisInfoVO();
        martin.setStrategyId(martinStrategyInfo.getStrategyId());
        martin.setStrategyStatus(martinStrategyInfo.getStrategyStatus());
        martin.setPlatform(martinStrategyInfo.getPlatform());
        martin.setUpdateTime(martinStrategyInfo.getUpdateTime().getTime()/1000);
        martin.setStrategyType(martinStrategyInfo.getStrategyType());
        martin.setCoin(martinStrategyInfo.getCoin());
        martin.setLeverage(martinStrategyInfo.getLeverage());
        martin.setLongOpen(martinStrategyInfo.getLongOpen());
        martin.setLongCount(martinStrategyInfo.getLongCount());
        martin.setShortOpen(martinStrategyInfo.getShortOpen());
        martin.setShortCount(martinStrategyInfo.getShortCount());
        martin.setCommon(martinStrategyInfo.getCommon());
        martin.setSellLongRate(martinStrategyInfo.getSellLongRate());
        martin.setBuyShortRate(martinStrategyInfo.getBuyShortRate());
        martin.setBuyLongRate(martinStrategyInfo.getBuyLongRate());
        martin.setSellShortRate(martinStrategyInfo.getSellShortRate());
        martin.setAddSet(listPyhton);
        String json = JsonUtils.objectToJson(martin);
        redisUtil.hset(StrategyRedisConfig.settings,String.valueOf(martinStrategyInfo.getStrategyId()),json);
    }


    public static void main(String[] args) {
        MartinStrategyRedisInfoVO martin = new MartinStrategyRedisInfoVO();
        martin.setUpdateTime(1000000000L);
        martin.setStrategyType(1L);
        martin.setCoin("btc");
        martin.setLeverage(1);
        List<MartinStrategySettingsPythonVO> list = new ArrayList<>();
        MartinStrategySettingsPythonVO settings = new MartinStrategySettingsPythonVO();
        settings.setShort_stop_rate(BigDecimal.ZERO);
        list.add(settings);
        martin.setAddSet(list);
        String json = JsonUtils.objectToJson(martin);
        System.out.println(json);
    }
}
