package com.wallet.strategy.service.impl;

import java.math.BigDecimal;
import java.util.*;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wallet.chain.domain.ChainConfigUrl;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.DateUtils;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.StrageyPositionInfo;
import com.wallet.strategy.domain.bo.RevenueCurveBO;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.RevenueCurveMapper;
import com.wallet.strategy.domain.RevenueCurve;
import com.wallet.strategy.service.IRevenueCurveService;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 收益曲线Service业务层处理
 * 
 * @author wallet
 * @date 2023-03-11
 */
@Service
public class RevenueCurveServiceImpl implements IRevenueCurveService 
{
    @Autowired
    private RevenueCurveMapper revenueCurveMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询收益曲线
     * 
     * @param id 收益曲线ID
     * @return 收益曲线
     */
    @Override
    public RevenueCurve selectRevenueCurveById(Long id)
    {
        return revenueCurveMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询收益曲线列表
     * 
     * @param revenueCurve 收益曲线
     * @return 收益曲线
     */
    @Override
    public List<RevenueCurve> selectRevenueCurveList(RevenueCurve revenueCurve)
    {
        return revenueCurveMapper.select(revenueCurve);
    }

    /**
     * 获取redis数据
     * @return
     */
    @Override
    public List<RevenueCurve> selectRevenueCurveListOrderByTime(Long accountId) {
        Example example = new Example(RevenueCurve.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("apiAccountId",accountId);
        example.setOrderByClause("STR_TO_DATE(date_time,'%Y-%m-%d') asc");
        return revenueCurveMapper.selectByExample(example);
    }

    @Override
    public List<RevenueCurve> findSelect(RevenueCurve revenueCurve)
    {
        Example example = new Example(RevenueCurve.class);
        Example.Criteria criteria = example.createCriteria();
        if(revenueCurve!=null){
            if(revenueCurve.getApiAccountId()!=null){
                criteria.andEqualTo("apiAccountId",revenueCurve.getApiAccountId());
            }
            if(StringUtils.isNotBlank(revenueCurve.getDataTime())){
                criteria.andEqualTo("dateTime",revenueCurve.getDataTime());
            }
        }
        example.setOrderByClause("date_time");
        return revenueCurveMapper.selectByExample(example);
    }
    /**
     * 新增收益曲线
     * 
     * @param revenueCurve 收益曲线
     * @return 结果
     */
    @Override
    public int insertRevenueCurve(RevenueCurve revenueCurve)
    {
		 return revenueCurveMapper.insertSelective(revenueCurve);
    }

    /**
     * 修改收益曲线
     * 
     * @param revenueCurve 收益曲线
     * @return 结果
     */
    @Override
    public int updateRevenueCurve(RevenueCurve revenueCurve)
    {

        return revenueCurveMapper.updateByPrimaryKeySelective(revenueCurve);
    }

    /**
     * 批量删除收益曲线
     * 
     * @param ids 需要删除的收益曲线ID
     * @return 结果
     */
    @Override
    public int deleteRevenueCurveByIds(Long[] ids)
    {
        return revenueCurveMapper.deleteRevenueCurveByIds(ids);
    }

    /**
     * 删除收益曲线信息
     * 
     * @param id 收益曲线ID
     * @return 结果
     */
    @Override
    public int deleteRevenueCurveById(Long id)
    {
		return revenueCurveMapper.deleteByPrimaryKey(id);
        
    }
}
