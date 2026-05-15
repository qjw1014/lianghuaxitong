package com.wallet.sysDict.mapper;

import com.wallet.sysDict.domain.AuFee;
import tk.mybatis.mapper.common.Mapper;
/**
 * 手续费配置Mapper接口
 * 
 * @author wallet
 * @date 2022-02-24
 */
public interface AuFeeMapper extends Mapper<AuFee>
{
    
    /**
     * 批量删除手续费配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAuFeeByIds(Long[] ids);
}
