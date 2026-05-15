package com.wallet.strategy.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.wallet.strategy.domain.MartinStrageyShare;
import tk.mybatis.mapper.common.Mapper;
/**
 * 分润统计Mapper接口
 * 
 * @author wallet
 * @date 2022-10-31
 */
public interface MartinStrageyShareMapper extends Mapper<MartinStrageyShare>
{
    
    /**
     * 批量删除分润统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMartinStrageyShareByIds(Long[] ids);


    /**
     * 分润收益
     * @return
     */
    public BigDecimal sumShareIncome();
}
