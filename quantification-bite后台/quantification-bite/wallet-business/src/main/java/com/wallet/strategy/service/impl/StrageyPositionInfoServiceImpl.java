package com.wallet.strategy.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wallet.common.redis.RedisUtil;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.StrageyPositionInfoMapper;
import com.wallet.strategy.domain.StrageyPositionInfo;
import com.wallet.strategy.service.IStrageyPositionInfoService;
import tk.mybatis.mapper.entity.Example;

/**
 * 策略仓位Service业务层处理
 * 
 * @author wallet
 * @date 2023-03-11
 */
@Service
public class StrageyPositionInfoServiceImpl implements IStrageyPositionInfoService 
{
    @Autowired
    private StrageyPositionInfoMapper strageyPositionInfoMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询策略仓位
     * 
     * @param id 策略仓位ID
     * @return 策略仓位
     */
    @Override
    public StrageyPositionInfo selectStrageyPositionInfoById(Long id)
    {
        return strageyPositionInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据子账号查询仓位列表信息
     *
     * @param accountId 策略仓位ID
     * @return 策略仓位
     */
    @Override
    public List<StrageyPositionInfo> selectStrageyPositionInfoByAccount(Long accountId) {
        List<StrageyPositionInfo> result = new ArrayList<>();
        Object object = redisUtil.hget(StrategyRedisConfig.b_position_info,String.valueOf(accountId));
        if(ObjectUtil.isNotNull(object)) {
            JSONArray array = JSONObject.parseArray(object.toString());
            for(Object dataJson:array){
                JSONObject json = (JSONObject) dataJson;
                //获取交易所
                String exchange =json.containsKey("Exchange")?json.getString("Exchange"): null;
                //获取交易对
                String symbol =json.containsKey("Symbol")?json.getString("Symbol"): null;
                //买卖方向
                String positionSide =json.containsKey("PositionSide")?json.getString("PositionSide"): null;
                //数量
                BigDecimal quantity =json.containsKey("Quantity")?json.getBigDecimal("Quantity"): BigDecimal.ZERO;
                //开始价格
                BigDecimal openPrice =json.containsKey("OpenPrice")?json.getBigDecimal("OpenPrice"): BigDecimal.ZERO;
                //当前价格
                BigDecimal currenPrice =json.containsKey("CurrenPrice")?json.getBigDecimal("CurrenPrice"): BigDecimal.ZERO;
                //清算价格
                BigDecimal liquidationPrice =json.containsKey("LiquidationPrice")?json.getBigDecimal("LiquidationPrice"): BigDecimal.ZERO;
                //ald指标
                int adl =json.containsKey("Adl")?json.getInteger("Adl"): 0;
                //等级
                int leverage =json.containsKey("Leverage")?json.getInteger("Leverage"): 0;
                //期货价值增损
                BigDecimal positionValue =json.containsKey("PositionValue")?json.getBigDecimal("PositionValue"): BigDecimal.ZERO;
                StrageyPositionInfo info = new StrageyPositionInfo();
                info.setExchange(exchange);
                info.setSymbol(symbol);
                info.setPositionSide(positionSide);
                info.setQuantity(quantity.stripTrailingZeros());
                info.setOpenPrice(openPrice.stripTrailingZeros());
                info.setCurrenPrice(currenPrice.stripTrailingZeros());
                info.setLiquidationPrice(liquidationPrice.stripTrailingZeros());
                info.setAdl(Long.valueOf(adl));
                info.setLeverage(Long.valueOf(leverage));
                info.setPositionValue(positionValue.stripTrailingZeros());
                info.setApiAccountId(accountId);
                info.setCretaeTime(new Date());
                //不用添加
//                strageyPositionInfoMapper.insertSelective(info);
                result.add(info);
            }
        }
        // Redis无数据时从数据库查询
        if (result.isEmpty()) {
            StrageyPositionInfo query = new StrageyPositionInfo();
            query.setApiAccountId(accountId);
            result = strageyPositionInfoMapper.select(query);
        }
        return result;
    }

    /**
     * 查询策略仓位列表
     * 
     * @param strageyPositionInfo 策略仓位
     * @return 策略仓位
     */
    @Override
    public List<StrageyPositionInfo> selectStrageyPositionInfoList(StrageyPositionInfo strageyPositionInfo)
    {
        Example example = new Example(StrageyPositionInfo.class);
        return strageyPositionInfoMapper.select(strageyPositionInfo);
    }
	 @Override
    public List<StrageyPositionInfo> findSelect(StrageyPositionInfo strageyPositionInfo)
    {
        return strageyPositionInfoMapper.select(strageyPositionInfo);
    }
    /**
     * 新增策略仓位
     * 
     * @param strageyPositionInfo 策略仓位
     * @return 结果
     */
    @Override
    public int insertStrageyPositionInfo(StrageyPositionInfo strageyPositionInfo)
    {
		 return strageyPositionInfoMapper.insertSelective(strageyPositionInfo);
    }

    /**
     * 修改策略仓位
     * 
     * @param strageyPositionInfo 策略仓位
     * @return 结果
     */
    @Override
    public int updateStrageyPositionInfo(StrageyPositionInfo strageyPositionInfo)
    {

        return strageyPositionInfoMapper.updateByPrimaryKeySelective(strageyPositionInfo);
    }

    /**
     * 批量删除策略仓位
     * 
     * @param ids 需要删除的策略仓位ID
     * @return 结果
     */
    @Override
    public int deleteStrageyPositionInfoByIds(Long[] ids)
    {
        return strageyPositionInfoMapper.deleteStrageyPositionInfoByIds(ids);
    }

    /**
     * 删除策略仓位信息
     * 
     * @param id 策略仓位ID
     * @return 结果
     */
    @Override
    public int deleteStrageyPositionInfoById(Long id)
    {
		return strageyPositionInfoMapper.deleteByPrimaryKey(id);
        
    }
}
