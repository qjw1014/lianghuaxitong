package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.ChargeHistory;
import tk.mybatis.mapper.common.Mapper;
/**
 * 充值记录	Mapper接口
 * 
 * @author wallet
 * @date 2023-03-11
 */
public interface ChargeHistoryMapper extends Mapper<ChargeHistory>
{
    
    /**
     * 批量删除充值记录	
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChargeHistoryByIds(Long[] ids);
}
