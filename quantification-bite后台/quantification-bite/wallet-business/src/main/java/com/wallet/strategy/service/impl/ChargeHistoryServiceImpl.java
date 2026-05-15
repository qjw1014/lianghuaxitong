package com.wallet.strategy.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.DateUtils;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.ChargeHistoryMapper;
import com.wallet.strategy.domain.ChargeHistory;
import com.wallet.strategy.service.IChargeHistoryService;
import tk.mybatis.mapper.entity.Example;

/**
 * 充值记录	Service业务层处理
 * 
 * @author wallet
 * @date 2023-03-11
 */
@Service
public class ChargeHistoryServiceImpl implements IChargeHistoryService 
{
    @Autowired
    private ChargeHistoryMapper chargeHistoryMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询充值记录	
     * 
     * @param id 充值记录	ID
     * @return 充值记录	
     */
    @Override
    public ChargeHistory selectChargeHistoryById(Long id)
    {
        return chargeHistoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询充值记录	列表
     * 
     * @param chargeHistory 充值记录	
     * @return 充值记录	
     */
    @Override
    public List<ChargeHistory> selectChargeHistoryList(ChargeHistory chargeHistory)
    {
        Example example = new Example(ChargeHistory.class);
        Example.Criteria criteria = example.createCriteria();
        if(chargeHistory!=null){
            if(chargeHistory.getApiAccountId()!=null){
                criteria.andEqualTo("apiAccountId",chargeHistory.getApiAccountId());
            }
            if(StringUtils.isNotBlank(chargeHistory.getActionType())){
                criteria.andEqualTo("actionType",chargeHistory.getActionType());
            }
        }
        example.setOrderByClause("create_time desc");
        return chargeHistoryMapper.selectByExample(example);
    }

    @Override
    public List<ChargeHistory> selectChargeHistoryListByAccountId(Long accountId) {
        List<ChargeHistory> result = new ArrayList<>();
        Object object = redisUtil.hget(StrategyRedisConfig.b_charge_history,String.valueOf(accountId));
        if(ObjectUtil.isNotNull(object)) {
            JSONArray array = JSONObject.parseArray(object.toString());
            for(Object dataJson:array){
                JSONObject json = (JSONObject) dataJson;
                //获取子账号
                Long apiAccountId =json.containsKey("account_id")?json.getLong("account_id"): null;
                //获取交易对
                String date =json.containsKey("date")?json.getString("date"): null;
                //操作类型 in：充值，out：提币
                String actionType =json.containsKey("action_type")?json.getString("action_type"): null;
                //金额
                BigDecimal amount =json.containsKey("amount")?json.getBigDecimal("amount"): BigDecimal.ZERO;
                //币种
                String coin =json.containsKey("coin")?json.getString("coin"):null;
                ChargeHistory info = new ChargeHistory();
                info.setApiAccountId(apiAccountId);
                info.setAmount(amount);
                info.setActionType(actionType);
                info.setCoin(coin);
                info.setCreateTime(DateUtils.dateTime(date,DateUtils.YYYY_MM_DD_HH_MM_SS));
                chargeHistoryMapper.insertSelective(info);
                result.add(info);
            }
        }
        return result;
    }

    @Override
    public List<ChargeHistory> findSelect(ChargeHistory chargeHistory)
    {
        return chargeHistoryMapper.select(chargeHistory);
    }
    /**
     * 新增充值记录	
     * 
     * @param chargeHistory 充值记录	
     * @return 结果
     */
    @Override
    public int insertChargeHistory(ChargeHistory chargeHistory)
    {
		 return chargeHistoryMapper.insertSelective(chargeHistory);
    }

    /**
     * 修改充值记录	
     * 
     * @param chargeHistory 充值记录	
     * @return 结果
     */
    @Override
    public int updateChargeHistory(ChargeHistory chargeHistory)
    {

        return chargeHistoryMapper.updateByPrimaryKeySelective(chargeHistory);
    }

    /**
     * 批量删除充值记录	
     * 
     * @param ids 需要删除的充值记录	ID
     * @return 结果
     */
    @Override
    public int deleteChargeHistoryByIds(Long[] ids)
    {
        return chargeHistoryMapper.deleteChargeHistoryByIds(ids);
    }

    /**
     * 删除充值记录	信息
     * 
     * @param id 充值记录	ID
     * @return 结果
     */
    @Override
    public int deleteChargeHistoryById(Long id)
    {
		return chargeHistoryMapper.deleteByPrimaryKey(id);
        
    }
}
