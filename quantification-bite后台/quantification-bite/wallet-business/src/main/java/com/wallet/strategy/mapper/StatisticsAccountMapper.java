package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.StatisticsAccount;
import tk.mybatis.mapper.common.Mapper;
/**
 * 统计账户信息Mapper接口
 * 
 * @author wallet
 * @date 2023-03-11
 */
public interface StatisticsAccountMapper extends Mapper<StatisticsAccount>
{
    
    /**
     * 批量删除统计账户信息
     * 
     * @param apiAccountIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStatisticsAccountByIds(Long[] apiAccountIds);
}
