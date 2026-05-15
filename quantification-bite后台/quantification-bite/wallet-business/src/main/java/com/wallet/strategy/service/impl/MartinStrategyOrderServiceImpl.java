package com.wallet.strategy.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.wallet.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.MartinStrategyOrderMapper;
import com.wallet.strategy.domain.MartinStrategyOrder;
import com.wallet.strategy.service.IMartinStrategyOrderService;

/**
 * 策略订单信息Service业务层处理
 * 
 * @author wallet
 * @date 2022-10-28
 */
@Service
public class MartinStrategyOrderServiceImpl implements IMartinStrategyOrderService 
{
    @Autowired
    private MartinStrategyOrderMapper martinStrategyOrderMapper;

    /**
     * 查询策略订单信息
     * 
     * @param serialId 策略订单信息ID
     * @return 策略订单信息
     */
    @Override
    public MartinStrategyOrder selectMartinStrategyOrderById(Long serialId)
    {
        return martinStrategyOrderMapper.selectByPrimaryKey(serialId);
    }

    /**
     * 查询策略订单信息列表
     * 
     * @param martinStrategyOrder 策略订单信息
     * @return 策略订单信息
     */
    @Override
    public List<MartinStrategyOrder> selectMartinStrategyOrderList(MartinStrategyOrder martinStrategyOrder)
    {
        return martinStrategyOrderMapper.select(martinStrategyOrder);
    }
	 @Override
    public List<MartinStrategyOrder> findSelect(MartinStrategyOrder martinStrategyOrder)
    {
        return martinStrategyOrderMapper.select(martinStrategyOrder);
    }
    /**
     * 新增策略订单信息
     * 
     * @param martinStrategyOrder 策略订单信息
     * @return 结果
     */
    @Override
    public int insertMartinStrategyOrder(MartinStrategyOrder martinStrategyOrder)
    {
		 return martinStrategyOrderMapper.insertSelective(martinStrategyOrder);
    }

    /**
     * 修改策略订单信息
     * 
     * @param martinStrategyOrder 策略订单信息
     * @return 结果
     */
    @Override
    public int updateMartinStrategyOrder(MartinStrategyOrder martinStrategyOrder)
    {

        return martinStrategyOrderMapper.updateByPrimaryKeySelective(martinStrategyOrder);
    }

    /**
     * 批量删除策略订单信息
     * 
     * @param serialIds 需要删除的策略订单信息ID
     * @return 结果
     */
    @Override
    public int deleteMartinStrategyOrderByIds(Long[] serialIds)
    {
        return martinStrategyOrderMapper.deleteMartinStrategyOrderByIds(serialIds);
    }

    /**
     * 删除策略订单信息信息
     * 
     * @param serialId 策略订单信息ID
     * @return 结果
     */
    @Override
    public int deleteMartinStrategyOrderById(Long serialId)
    {
		return martinStrategyOrderMapper.deleteByPrimaryKey(serialId);
        
    }

    /**
     * 统计子账号总收益
     * @param isSettled Y 统计已分润收益，N统计未分润收益 不填统计所有
     * @return
     */
    @Override
    public String findOrderTotalIncome(Long accountId,String isSettled) {
        MartinStrategyOrder order = new MartinStrategyOrder();
        if(accountId!=null){
            order.setAccountId(accountId);
        }
        if(StringUtils.isNotBlank(isSettled)){
            order.setIsSettled(isSettled);
        }
        BigDecimal bigDecimal = martinStrategyOrderMapper.findOrderTotalIncome(order);
        return bigDecimal.stripTrailingZeros().toPlainString();
    }
}
