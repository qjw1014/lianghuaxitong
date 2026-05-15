package com.wallet.chain.mapper;

import java.util.List;
import com.wallet.chain.domain.Chain;
import tk.mybatis.mapper.common.Mapper;
/**
 * 链Mapper接口
 * 
 * @author wallet
 * @date 2021-10-25
 */
public interface ChainMapper extends Mapper<Chain>
{
    
    /**
     * 批量删除链
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChainByIds(Long[] ids);
}
