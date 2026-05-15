package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.MartinStrategyApi;
import tk.mybatis.mapper.common.Mapper;
/**
 * api对应策略信息Mapper接口
 * 
 * @author wallet
 * @date 2022-09-05
 */
public interface MartinStrategyApiMapper extends Mapper<MartinStrategyApi>
{
    
    /**
     * 批量删除api对应策略信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMartinStrategyApiByIds(Long[] ids);
}
