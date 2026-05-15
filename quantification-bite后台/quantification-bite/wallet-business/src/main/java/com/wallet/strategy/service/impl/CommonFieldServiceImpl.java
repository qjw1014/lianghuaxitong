package com.wallet.strategy.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wallet.common.exception.BaseException;
import com.wallet.common.utils.DateUtils;
import com.wallet.strategy.domain.StrategyField;
import com.wallet.strategy.mapper.StrategyFieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.CommonFieldMapper;
import com.wallet.strategy.domain.CommonField;
import com.wallet.strategy.service.ICommonFieldService;
import org.springframework.util.CollectionUtils;

/**
 * 策略公共字段描述Service业务层处理
 * 
 * @author wallet
 * @date 2023-03-14
 */
@Service
public class CommonFieldServiceImpl implements ICommonFieldService 
{
    @Autowired
    private CommonFieldMapper commonFieldMapper;

    @Autowired
    private StrategyFieldMapper strategyFieldMapper;

    /**
     * 查询策略公共字段描述
     * 
     * @param id 策略公共字段描述ID
     * @return 策略公共字段描述
     */
    @Override
    public CommonField selectCommonFieldById(Long id)
    {
        return commonFieldMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询策略公共字段描述列表
     * 
     * @param commonField 策略公共字段描述
     * @return 策略公共字段描述
     */
    @Override
    public List<CommonField> selectCommonFieldList(CommonField commonField)
    {
        return commonFieldMapper.select(commonField);
    }


    /**
     * 根据类型查询
     * @param strategyType
     * @return
     */
    @Override
    public List<CommonField> selectCommonFieldListByType(Long strategyType) {
        List<CommonField> result = new ArrayList<>();
        StrategyField field = new StrategyField();
        field.setStrategyTypeId(strategyType);
        List<StrategyField> list = strategyFieldMapper.select(field);
        if(!CollectionUtils.isEmpty(list)){
            for(StrategyField strategyField:list){
                CommonField commonField = commonFieldMapper.selectByPrimaryKey(strategyField.getField());
                if(commonField!=null){
                    result.add(commonField);
                }
            }
        }
        return result;
    }

    @Override
    public List<CommonField> findSelect(CommonField commonField)
    {
        return commonFieldMapper.select(commonField);
    }
    /**
     * 新增策略公共字段描述
     * 
     * @param commonField 策略公共字段描述
     * @return 结果
     */
    @Override
    public int insertCommonField(CommonField commonField)
    {
        //判断是否已存在
        CommonField field = new CommonField();
        field.setName(commonField.getName().trim());
        int count = commonFieldMapper.selectCount(field);
        if(count>0){
            throw new BaseException("字段已存在");
        }
		return commonFieldMapper.insertSelective(commonField);
    }

    /**
     * 修改策略公共字段描述
     * 
     * @param commonField 策略公共字段描述
     * @return 结果
     */
    @Override
    public int updateCommonField(CommonField commonField)
    {

        return commonFieldMapper.updateByPrimaryKeySelective(commonField);
    }

    /**
     * 批量删除策略公共字段描述
     * 
     * @param ids 需要删除的策略公共字段描述ID
     * @return 结果
     */
    @Override
    public int deleteCommonFieldByIds(Long[] ids)
    {
        return commonFieldMapper.deleteCommonFieldByIds(ids);
    }

    /**
     * 删除策略公共字段描述信息
     * 
     * @param id 策略公共字段描述ID
     * @return 结果
     */
    @Override
    public int deleteCommonFieldById(Long id)
    {
		return commonFieldMapper.deleteByPrimaryKey(id);
        
    }
}
