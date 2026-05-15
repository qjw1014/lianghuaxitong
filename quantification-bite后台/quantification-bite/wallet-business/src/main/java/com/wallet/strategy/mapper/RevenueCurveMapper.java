package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.RevenueCurve;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
/**
 * 收益曲线Mapper接口
 * 
 * @author wallet
 * @date 2023-03-11
 */
public interface RevenueCurveMapper extends Mapper<RevenueCurve>
{
    
    /**
     * 批量删除收益曲线
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRevenueCurveByIds(Long[] ids);



    /**
     * 根据子账号和日期参数查询数据是否存在
     *
     * @return 结果
     */
    public RevenueCurve selectAccountIdAndDateTime(@Param("apiAccountId") Long apiAccountId,
                                                   @Param("dateTime") String dateTime);
}
