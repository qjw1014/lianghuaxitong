package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.StrategyField;
import com.wallet.strategy.domain.vo.StrategyFieldAddVo;

/**
 * 策略字段关系Service接口
 * 
 * @author wallet
 * @date 2023-03-14
 */
public interface IStrategyFieldService 
{
    /**
     * 查询策略字段关系
     * 
     * @param id 策略字段关系ID
     * @return 策略字段关系
     */
    public StrategyField selectStrategyFieldById(Long id);

    /**
     * 查询策略字段关系列表
     * 
     * @param strategyField 策略字段关系
     * @return 策略字段关系集合
     */
    public List<StrategyField> selectStrategyFieldList(StrategyField strategyField);
    
    /**
     * 通用查询策略字段关系列表
     * 
     * @param strategyField 策略字段关系
     * @return 策略字段关系集合
     */
    public List<StrategyField> findSelect(StrategyField strategyField);

    /**
     * 新增策略字段关系
     * 
     * @param strategyField 策略字段关系
     * @return 结果
     */
    public int insertStrategyField(StrategyField strategyField);

    /**
     * 批量新增策略字段关系
     */
    public int insertStrategyFieldBatch(StrategyFieldAddVo vo);

    /**
     * 修改策略字段关系
     * 
     * @param strategyField 策略字段关系
     * @return 结果
     */
    public int updateStrategyField(StrategyField strategyField);

    /**
     * 批量删除策略字段关系
     * 
     * @param ids 需要删除的策略字段关系ID
     * @return 结果
     */
    public int deleteStrategyFieldByIds(Long[] ids);

    /**
     * 删除策略字段关系信息
     * 
     * @param id 策略字段关系ID
     * @return 结果
     */
    public int deleteStrategyFieldById(Long id);
}
