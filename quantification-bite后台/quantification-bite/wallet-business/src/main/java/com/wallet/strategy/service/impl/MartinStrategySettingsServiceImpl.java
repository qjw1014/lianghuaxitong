package com.wallet.strategy.service.impl;

import java.util.List;
import com.wallet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.MartinStrategySettingsMapper;
import com.wallet.strategy.domain.MartinStrategySettings;
import com.wallet.strategy.service.IMartinStrategySettingsService;

/**
 * 策略比例设置Service业务层处理
 * 
 * @author wallet
 * @date 2022-08-31
 */
@Service
public class MartinStrategySettingsServiceImpl implements IMartinStrategySettingsService 
{
    @Autowired
    private MartinStrategySettingsMapper martinStrategySettingsMapper;

    /**
     * 查询策略比例设置
     * 
     * @param id 策略比例设置ID
     * @return 策略比例设置
     */
    @Override
    public MartinStrategySettings selectMartinStrategySettingsById(Long id)
    {
        return martinStrategySettingsMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询策略比例设置列表
     * 
     * @param martinStrategySettings 策略比例设置
     * @return 策略比例设置
     */
    @Override
    public List<MartinStrategySettings> selectMartinStrategySettingsList(MartinStrategySettings martinStrategySettings)
    {
        return martinStrategySettingsMapper.select(martinStrategySettings);
    }
	 @Override
    public List<MartinStrategySettings> findSelect(MartinStrategySettings martinStrategySettings)
    {
        return martinStrategySettingsMapper.select(martinStrategySettings);
    }
    /**
     * 新增策略比例设置
     * 
     * @param martinStrategySettings 策略比例设置
     * @return 结果
     */
    @Override
    public int insertMartinStrategySettings(MartinStrategySettings martinStrategySettings)
    {
		 return martinStrategySettingsMapper.insertSelective(martinStrategySettings);
    }

    /**
     * 修改策略比例设置
     * 
     * @param martinStrategySettings 策略比例设置
     * @return 结果
     */
    @Override
    public int updateMartinStrategySettings(MartinStrategySettings martinStrategySettings)
    {

        return martinStrategySettingsMapper.updateByPrimaryKeySelective(martinStrategySettings);
    }

    /**
     * 批量删除策略比例设置
     * 
     * @param ids 需要删除的策略比例设置ID
     * @return 结果
     */
    @Override
    public int deleteMartinStrategySettingsByIds(Long[] ids)
    {
        return martinStrategySettingsMapper.deleteMartinStrategySettingsByIds(ids);
    }

    /**
     * 删除策略比例设置信息
     * 
     * @param id 策略比例设置ID
     * @return 结果
     */
    @Override
    public int deleteMartinStrategySettingsById(Long id)
    {
		return martinStrategySettingsMapper.deleteByPrimaryKey(id);
        
    }
}
