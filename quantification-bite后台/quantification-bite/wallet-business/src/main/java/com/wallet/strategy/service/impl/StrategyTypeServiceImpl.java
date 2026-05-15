package com.wallet.strategy.service.impl;

import java.util.List;
import com.wallet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.StrategyTypeMapper;
import com.wallet.strategy.domain.StrategyType;
import com.wallet.strategy.service.IStrategyTypeService;

/**
 * 策略类型Service业务层处理
 * 
 * @author wallet
 * @date 2022-09-01
 */
@Service
public class StrategyTypeServiceImpl implements IStrategyTypeService 
{
    @Autowired
    private StrategyTypeMapper strategyTypeMapper;

    /**
     * 查询策略类型
     * 
     * @param id 策略类型ID
     * @return 策略类型
     */
    @Override
    public StrategyType selectStrategyTypeById(Long id)
    {
        return strategyTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public StrategyType selectStrategyTypeByType(Integer type) {
        return strategyTypeMapper.selectInfoByType(type);
    }

    /**
     * 查询策略类型列表
     * 
     * @param strategyType 策略类型
     * @return 策略类型
     */
    @Override
    public List<StrategyType> selectStrategyTypeList(StrategyType strategyType)
    {
        return strategyTypeMapper.select(strategyType);
    }
	 @Override
    public List<StrategyType> findSelect(StrategyType strategyType)
    {
        return strategyTypeMapper.select(strategyType);
    }
    /**
     * 新增策略类型
     * 
     * @param strategyType 策略类型
     * @return 结果
     */
    @Override
    public int insertStrategyType(StrategyType strategyType)
    {
		 return strategyTypeMapper.insertSelective(strategyType);
    }

    /**
     * 修改策略类型
     * 
     * @param strategyType 策略类型
     * @return 结果
     */
    @Override
    public int updateStrategyType(StrategyType strategyType)
    {

        return strategyTypeMapper.updateByPrimaryKeySelective(strategyType);
    }

    /**
     * 批量删除策略类型
     * 
     * @param ids 需要删除的策略类型ID
     * @return 结果
     */
    @Override
    public int deleteStrategyTypeByIds(Long[] ids)
    {
        return strategyTypeMapper.deleteStrategyTypeByIds(ids);
    }

    /**
     * 删除策略类型信息
     * 
     * @param id 策略类型ID
     * @return 结果
     */
    @Override
    public int deleteStrategyTypeById(Long id)
    {
		return strategyTypeMapper.deleteByPrimaryKey(id);
        
    }
}
