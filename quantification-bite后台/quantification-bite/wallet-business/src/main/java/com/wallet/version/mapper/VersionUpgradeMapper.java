package com.wallet.version.mapper;

import java.util.List;
import com.wallet.version.domain.VersionUpgrade;
import tk.mybatis.mapper.common.Mapper;
/**
 * app版本升级Mapper接口
 * 
 * @author wallet
 * @date 2021-11-19
 */
public interface VersionUpgradeMapper extends Mapper<VersionUpgrade>
{
    
    /**
     * 批量删除app版本升级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVersionUpgradeByIds(Long[] ids);
}
