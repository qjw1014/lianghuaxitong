package com.wallet.strategy.service;

import java.math.BigDecimal;
import java.util.List;
import com.wallet.strategy.domain.MartinStrategyOrder;

/**
 * 策略订单信息Service接口
 * 
 * @author wallet
 * @date 2022-10-28
 */
public interface IMartinStrategyOrderService 
{
    /**
     * 查询策略订单信息
     * 
     * @param serialId 策略订单信息ID
     * @return 策略订单信息
     */
    public MartinStrategyOrder selectMartinStrategyOrderById(Long serialId);

    /**
     * 查询策略订单信息列表
     * 
     * @param martinStrategyOrder 策略订单信息
     * @return 策略订单信息集合
     */
    public List<MartinStrategyOrder> selectMartinStrategyOrderList(MartinStrategyOrder martinStrategyOrder);
    
    /**
     * 通用查询策略订单信息列表
     * 
     * @param martinStrategyOrder 策略订单信息
     * @return 策略订单信息集合
     */
    public List<MartinStrategyOrder> findSelect(MartinStrategyOrder martinStrategyOrder);

    /**
     * 新增策略订单信息
     * 
     * @param martinStrategyOrder 策略订单信息
     * @return 结果
     */
    public int insertMartinStrategyOrder(MartinStrategyOrder martinStrategyOrder);

    /**
     * 修改策略订单信息
     * 
     * @param martinStrategyOrder 策略订单信息
     * @return 结果
     */
    public int updateMartinStrategyOrder(MartinStrategyOrder martinStrategyOrder);

    /**
     * 批量删除策略订单信息
     * 
     * @param serialIds 需要删除的策略订单信息ID
     * @return 结果
     */
    public int deleteMartinStrategyOrderByIds(Long[] serialIds);

    /**
     * 删除策略订单信息信息
     * 
     * @param serialId 策略订单信息ID
     * @return 结果
     */
    public int deleteMartinStrategyOrderById(Long serialId);

    /**
     * 统计子账号总收益
     * @return
     */
    public String findOrderTotalIncome(Long accountId,String isSettled);
}
