package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.StrategyType;

/**
 * 策略类型Service接口
 * 
 * @author wallet
 * @date 2022-09-01
 */
public interface IStrategyTypeService 
{
    /**
     * 查询策略类型
     * 
     * @param id 策略类型ID
     * @return 策略类型
     */
    public StrategyType selectStrategyTypeById(Long id);


    /**
     * 根据类型查询策略类型
     *
     * @param type 策略类型ID
     * @return 策略类型
     */
    public StrategyType selectStrategyTypeByType(Integer type);

    /**
     * 查询策略类型列表
     * 
     * @param strategyType 策略类型
     * @return 策略类型集合
     */
    public List<StrategyType> selectStrategyTypeList(StrategyType strategyType);
    
    /**
     * 通用查询策略类型列表
     * 
     * @param strategyType 策略类型
     * @return 策略类型集合
     */
    public List<StrategyType> findSelect(StrategyType strategyType);

    /**
     * 新增策略类型
     * 
     * @param strategyType 策略类型
     * @return 结果
     */
    public int insertStrategyType(StrategyType strategyType);

    /**
     * 修改策略类型
     * 
     * @param strategyType 策略类型
     * @return 结果
     */
    public int updateStrategyType(StrategyType strategyType);

    /**
     * 批量删除策略类型
     * 
     * @param ids 需要删除的策略类型ID
     * @return 结果
     */
    public int deleteStrategyTypeByIds(Long[] ids);

    /**
     * 删除策略类型信息
     * 
     * @param id 策略类型ID
     * @return 结果
     */
    public int deleteStrategyTypeById(Long id);
}
