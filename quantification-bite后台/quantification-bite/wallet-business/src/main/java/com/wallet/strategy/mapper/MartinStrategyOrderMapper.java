package com.wallet.strategy.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.wallet.strategy.domain.MartinStrategyOrder;
import tk.mybatis.mapper.common.Mapper;
/**
 * 策略订单信息Mapper接口
 * 
 * @author wallet
 * @date 2022-10-28
 */
public interface MartinStrategyOrderMapper extends Mapper<MartinStrategyOrder>
{
    
    /**
     * 批量删除策略订单信息
     * 
     * @param serialIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMartinStrategyOrderByIds(Long[] serialIds);


    /**
     * 统计收益
     * @return
     */
    BigDecimal findOrderTotalIncome(MartinStrategyOrder order);

    /**
     * 确定分润
     * @param accountId
     * @return
     */
    int deterShare(Long accountId);
}
