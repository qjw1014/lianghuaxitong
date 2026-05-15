package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.MartinStrategySettings;
import tk.mybatis.mapper.common.Mapper;
/**
 * 策略比例设置Mapper接口
 * 
 * @author wallet
 * @date 2022-08-31
 */
public interface MartinStrategySettingsMapper extends Mapper<MartinStrategySettings>
{
    
    /**
     * 批量删除策略比例设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMartinStrategySettingsByIds(Long[] ids);


    /**
     * 删除策略
     *
     * @param strategyId 需要删除策略编号
     * @return 结果
     */
    public int deleteMartinStrategySettingsByStrategyId(Long strategyId);
}
