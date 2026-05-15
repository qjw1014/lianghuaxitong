package com.wallet.strategy.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.DateUtils;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.StrageyPositionInfo;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.RevenueDetailMapper;
import com.wallet.strategy.domain.RevenueDetail;
import com.wallet.strategy.service.IRevenueDetailService;
import tk.mybatis.mapper.entity.Example;

/**
 * 收益明细	Service业务层处理
 * 
 * @author wallet
 * @date 2023-03-11
 */
@Service
public class RevenueDetailServiceImpl implements IRevenueDetailService 
{
    @Autowired
    private RevenueDetailMapper revenueDetailMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询收益明细	
     * 
     * @param id 收益明细	ID
     * @return 收益明细	
     */
    @Override
    public RevenueDetail selectRevenueDetailById(Long id)
    {
        return revenueDetailMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询收益明细	列表
     * 
     * @param revenueDetail 收益明细	
     * @return 收益明细	
     */
    @Override
    public List<RevenueDetail> selectRevenueDetailList(RevenueDetail revenueDetail)
    {
        Example example = new Example(RevenueDetail.class);
        Example.Criteria criteria = example.createCriteria();
        if(revenueDetail.getApiAccountId()!=null){
            criteria.andEqualTo("apiAccountId",revenueDetail.getApiAccountId());
        }
        if(StringUtils.isNotBlank(revenueDetail.getProfitCoin())){
            criteria.andEqualTo("profitCoin",revenueDetail.getProfitCoin());
        }
        example.setOrderByClause("STR_TO_DATE(date_time,'%Y-%m-%d') desc");
        return revenueDetailMapper.selectByExample(example);
    }

    @Override
    public List<RevenueDetail> selectRevenueDetailList(Long apiAccountId) {
        List<RevenueDetail> result = new ArrayList<>();
        Object object = redisUtil.hget(StrategyRedisConfig.b_revenue_detail,String.valueOf(apiAccountId));
        if(ObjectUtil.isNotNull(object)) {
            JSONArray array = JSONObject.parseArray(object.toString());
            for(Object dataJson:array){
                JSONObject json = (JSONObject) dataJson;
                //获取api
                Long accountId =json.containsKey("account_id")?json.getLong("account_id"): null;
                //交易时间
                String date =json.containsKey("date")?json.getString("date"): null;
                //数量
                BigDecimal profit =json.containsKey("profit")?json.getBigDecimal("profit"): BigDecimal.ZERO;
                //币种
                String profitCoin =json.containsKey("profit_coin")?json.getString("profit_coin"): null;
                RevenueDetail detail = new RevenueDetail();
                detail.setApiAccountId(accountId);
                detail.setDataTime(date);
                detail.setProfit(profit);
                detail.setProfitCoin(profitCoin);
                detail.setCreateTime(new Date());
                result.add(detail);
                RevenueDetail revenueDetail = revenueDetailMapper.selectRevenueDetailByAccountIdAndDateTime(apiAccountId,date);
                if(revenueDetail!=null){
                    revenueDetail.setProfit(profit);
                    revenueDetail.setCreateTime(new Date());
                    revenueDetail.setProfitCoin(profitCoin);
                    revenueDetailMapper.updateByPrimaryKeySelective(revenueDetail);
                }else{
                    revenueDetailMapper.insertSelective(detail);
                }
            }
        }
        return result;
    }

    @Override
    public List<RevenueDetail> findSelect(RevenueDetail revenueDetail)
    {
        return revenueDetailMapper.select(revenueDetail);
    }
    /**
     * 新增收益明细	
     * 
     * @param revenueDetail 收益明细	
     * @return 结果
     */
    @Override
    public int insertRevenueDetail(RevenueDetail revenueDetail)
    {
		 return revenueDetailMapper.insertSelective(revenueDetail);
    }

    /**
     * 修改收益明细	
     * 
     * @param revenueDetail 收益明细	
     * @return 结果
     */
    @Override
    public int updateRevenueDetail(RevenueDetail revenueDetail)
    {

        return revenueDetailMapper.updateByPrimaryKeySelective(revenueDetail);
    }

    /**
     * 批量删除收益明细	
     * 
     * @param ids 需要删除的收益明细	ID
     * @return 结果
     */
    @Override
    public int deleteRevenueDetailByIds(Long[] ids)
    {
        return revenueDetailMapper.deleteRevenueDetailByIds(ids);
    }

    /**
     * 删除收益明细	信息
     * 
     * @param id 收益明细	ID
     * @return 结果
     */
    @Override
    public int deleteRevenueDetailById(Long id)
    {
		return revenueDetailMapper.deleteByPrimaryKey(id);
        
    }
}
