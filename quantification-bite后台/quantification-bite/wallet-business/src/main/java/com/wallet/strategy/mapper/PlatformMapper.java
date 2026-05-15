package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.Platform;
import tk.mybatis.mapper.common.Mapper;
/**
 * 交易平台Mapper接口
 * 
 * @author wallet
 * @date 2022-09-01
 */
public interface PlatformMapper extends Mapper<Platform>
{
    
    /**
     * 批量删除交易平台
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlatformByIds(Long[] ids);
}
