package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.StrageyPositionInfo;
import tk.mybatis.mapper.common.Mapper;
/**
 * 策略仓位Mapper接口
 * 
 * @author wallet
 * @date 2023-03-11
 */
public interface StrageyPositionInfoMapper extends Mapper<StrageyPositionInfo>
{
    
    /**
     * 批量删除策略仓位
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStrageyPositionInfoByIds(Long[] ids);
}
