package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.CommonField;

/**
 * 策略公共字段描述Service接口
 * 
 * @author wallet
 * @date 2023-03-14
 */
public interface ICommonFieldService 
{
    /**
     * 查询策略公共字段描述
     * 
     * @param id 策略公共字段描述ID
     * @return 策略公共字段描述
     */
    public CommonField selectCommonFieldById(Long id);

    /**
     * 查询策略公共字段描述列表
     * 
     * @param commonField 策略公共字段描述
     * @return 策略公共字段描述集合
     */
    public List<CommonField> selectCommonFieldList(CommonField commonField);

    /**
     * 根据类型查询
     * @return 策略公共字段描述集合
     */
    public List<CommonField> selectCommonFieldListByType(Long strategyType);
    
    /**
     * 通用查询策略公共字段描述列表
     * 
     * @param commonField 策略公共字段描述
     * @return 策略公共字段描述集合
     */
    public List<CommonField> findSelect(CommonField commonField);

    /**
     * 新增策略公共字段描述
     * 
     * @param commonField 策略公共字段描述
     * @return 结果
     */
    public int insertCommonField(CommonField commonField);

    /**
     * 修改策略公共字段描述
     * 
     * @param commonField 策略公共字段描述
     * @return 结果
     */
    public int updateCommonField(CommonField commonField);

    /**
     * 批量删除策略公共字段描述
     * 
     * @param ids 需要删除的策略公共字段描述ID
     * @return 结果
     */
    public int deleteCommonFieldByIds(Long[] ids);

    /**
     * 删除策略公共字段描述信息
     * 
     * @param id 策略公共字段描述ID
     * @return 结果
     */
    public int deleteCommonFieldById(Long id);
}
