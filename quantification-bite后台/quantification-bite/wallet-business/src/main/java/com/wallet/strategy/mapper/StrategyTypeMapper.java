package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.StrategyType;
import tk.mybatis.mapper.common.Mapper;
/**
 * 策略类型Mapper接口
 * 
 * @author wallet
 * @date 2022-09-01
 */
public interface StrategyTypeMapper extends Mapper<StrategyType>
{
    
    /**
     * 批量删除策略类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStrategyTypeByIds(Long[] ids);


    StrategyType selectInfoByType(Integer type);
}
