package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.StrategyInfo;

/**
 * 策略信息Service接口
 * 
 * @author wallet
 * @date 2023-03-14
 */
public interface IStrategyInfoService 
{
    /**
     * 查询策略信息
     * 
     * @param strategyId 策略信息ID
     * @return 策略信息
     */
    public StrategyInfo selectStrategyInfoById(Long strategyId);

    /**
     * 查询策略信息列表
     * 
     * @param strategyInfo 策略信息
     * @return 策略信息集合
     */
    public List<StrategyInfo> selectStrategyInfoList(StrategyInfo strategyInfo);
    
    /**
     * 通用查询策略信息列表
     * 
     * @param strategyInfo 策略信息
     * @return 策略信息集合
     */
    public List<StrategyInfo> findSelect(StrategyInfo strategyInfo);

    /**
     * 新增策略信息
     * 
     * @param strategyInfo 策略信息
     * @return 结果
     */
    public int insertStrategyInfo(StrategyInfo strategyInfo);

    /**
     * 修改策略信息
     * 
     * @param strategyInfo 策略信息
     * @return 结果
     */
    public int updateStrategyInfo(StrategyInfo strategyInfo);


    /**
     * 修改策略状态
     *
     * @param strategyInfo 策略信息
     * @return 结果
     */
    public int updateStrategyStatus(StrategyInfo strategyInfo);

    /**
     * 批量删除策略信息
     * 
     * @param strategyIds 需要删除的策略信息ID
     * @return 结果
     */
    public int deleteStrategyInfoByIds(Long[] strategyIds);

    /**
     * 删除策略信息信息
     * 
     * @param strategyId 策略信息ID
     * @return 结果
     */
    public int deleteStrategyInfoById(Long strategyId);


    /**
     * 业务删除
     * @param strategyId
     * @return
     */
    public int updateDelete(Long strategyId);
}
