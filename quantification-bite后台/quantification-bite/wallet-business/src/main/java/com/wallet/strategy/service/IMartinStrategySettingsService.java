package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.MartinStrategySettings;

/**
 * 策略比例设置Service接口
 * 
 * @author wallet
 * @date 2022-08-31
 */
public interface IMartinStrategySettingsService 
{
    /**
     * 查询策略比例设置
     * 
     * @param id 策略比例设置ID
     * @return 策略比例设置
     */
    public MartinStrategySettings selectMartinStrategySettingsById(Long id);

    /**
     * 查询策略比例设置列表
     * 
     * @param martinStrategySettings 策略比例设置
     * @return 策略比例设置集合
     */
    public List<MartinStrategySettings> selectMartinStrategySettingsList(MartinStrategySettings martinStrategySettings);
    
    /**
     * 通用查询策略比例设置列表
     * 
     * @param martinStrategySettings 策略比例设置
     * @return 策略比例设置集合
     */
    public List<MartinStrategySettings> findSelect(MartinStrategySettings martinStrategySettings);

    /**
     * 新增策略比例设置
     * 
     * @param martinStrategySettings 策略比例设置
     * @return 结果
     */
    public int insertMartinStrategySettings(MartinStrategySettings martinStrategySettings);

    /**
     * 修改策略比例设置
     * 
     * @param martinStrategySettings 策略比例设置
     * @return 结果
     */
    public int updateMartinStrategySettings(MartinStrategySettings martinStrategySettings);

    /**
     * 批量删除策略比例设置
     * 
     * @param ids 需要删除的策略比例设置ID
     * @return 结果
     */
    public int deleteMartinStrategySettingsByIds(Long[] ids);

    /**
     * 删除策略比例设置信息
     * 
     * @param id 策略比例设置ID
     * @return 结果
     */
    public int deleteMartinStrategySettingsById(Long id);
}
