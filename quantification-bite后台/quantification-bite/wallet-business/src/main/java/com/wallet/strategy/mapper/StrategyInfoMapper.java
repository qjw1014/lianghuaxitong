package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.StrategyInfo;
import tk.mybatis.mapper.common.Mapper;
/**
 * 策略信息Mapper接口
 * 
 * @author wallet
 * @date 2023-03-14
 */
public interface StrategyInfoMapper extends Mapper<StrategyInfo>
{
    
    /**
     * 批量删除策略信息
     * 
     * @param strategyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStrategyInfoByIds(Long[] strategyIds);
}
