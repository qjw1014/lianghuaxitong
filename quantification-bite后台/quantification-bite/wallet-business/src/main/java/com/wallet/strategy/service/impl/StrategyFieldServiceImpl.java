package com.wallet.strategy.service.impl;

import java.util.List;

import com.wallet.common.exception.BaseException;
import com.wallet.common.utils.MessageUtils;
import com.wallet.strategy.domain.vo.StrategyFieldAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.StrategyFieldMapper;
import com.wallet.strategy.domain.StrategyField;
import com.wallet.strategy.service.IStrategyFieldService;

/**
 * 策略字段关系Service业务层处理
 * 
 * @author wallet
 * @date 2023-03-14
 */
@Service
public class StrategyFieldServiceImpl implements IStrategyFieldService 
{
    @Autowired
    private StrategyFieldMapper strategyFieldMapper;

    /**
     * 查询策略字段关系
     * 
     * @param id 策略字段关系ID
     * @return 策略字段关系
     */
    @Override
    public StrategyField selectStrategyFieldById(Long id)
    {
        return strategyFieldMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询策略字段关系列表
     * 
     * @param strategyField 策略字段关系
     * @return 策略字段关系
     */
    @Override
    public List<StrategyField> selectStrategyFieldList(StrategyField strategyField)
    {
        return strategyFieldMapper.select(strategyField);
    }
	 @Override
    public List<StrategyField> findSelect(StrategyField strategyField)
    {
        return strategyFieldMapper.select(strategyField);
    }
    /**
     * 新增策略字段关系
     * 
     * @param strategyField 策略字段关系
     * @return 结果
     */
    @Override
    public int insertStrategyField(StrategyField strategyField)
    {
		 return strategyFieldMapper.insertSelective(strategyField);
    }

    @Override
    public int insertStrategyFieldBatch(StrategyFieldAddVo vo) {
        if(vo.getFields()==null||vo.getFields().length==0){
            throw new BaseException("请选择字段");
        }
        if(vo.getStrategyTypeId()==null){
            throw new BaseException("参数异常");
        }
        //先删除原来的
        StrategyField delete = new StrategyField();
        delete.setStrategyTypeId(vo.getStrategyTypeId());
        strategyFieldMapper.delete(delete);
        for(int i=0;i<vo.getFields().length;i++){
            StrategyField strategyField =  new StrategyField();
            strategyField.setStrategyTypeId(vo.getStrategyTypeId());
            strategyField.setField(vo.getFields()[i]);
            strategyFieldMapper.insertSelective(strategyField);
        }
        return 1;
    }

    /**
     * 修改策略字段关系
     * 
     * @param strategyField 策略字段关系
     * @return 结果
     */
    @Override
    public int updateStrategyField(StrategyField strategyField)
    {

        return strategyFieldMapper.updateByPrimaryKeySelective(strategyField);
    }

    /**
     * 批量删除策略字段关系
     * 
     * @param ids 需要删除的策略字段关系ID
     * @return 结果
     */
    @Override
    public int deleteStrategyFieldByIds(Long[] ids)
    {
        return strategyFieldMapper.deleteStrategyFieldByIds(ids);
    }

    /**
     * 删除策略字段关系信息
     * 
     * @param id 策略字段关系ID
     * @return 结果
     */
    @Override
    public int deleteStrategyFieldById(Long id)
    {
		return strategyFieldMapper.deleteByPrimaryKey(id);
        
    }
}
