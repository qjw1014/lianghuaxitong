package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.RevenueDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
/**
 * 收益明细	Mapper接口
 * 
 * @author wallet
 * @date 2023-03-11
 */
public interface RevenueDetailMapper extends Mapper<RevenueDetail>
{
    
    /**
     * 批量删除收益明细	
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRevenueDetailByIds(Long[] ids);


    public RevenueDetail selectRevenueDetailByAccountIdAndDateTime(@Param("apiAccountId") Long apiAccountId,
                                                         @Param("dateTime") String dateTime);

}
