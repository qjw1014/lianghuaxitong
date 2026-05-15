package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.StrategyField;
import tk.mybatis.mapper.common.Mapper;
/**
 * 策略字段关系Mapper接口
 * 
 * @author wallet
 * @date 2023-03-14
 */
public interface StrategyFieldMapper extends Mapper<StrategyField>
{
    
    /**
     * 批量删除策略字段关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStrategyFieldByIds(Long[] ids);
}
