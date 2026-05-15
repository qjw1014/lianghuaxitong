package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.StatisticsAccount;

/**
 * 统计账户信息Service接口
 * 
 * @author wallet
 * @date 2023-03-11
 */
public interface IStatisticsAccountService 
{
    /**
     * 查询统计账户信息
     * 
     * @param apiAccountId 统计账户信息ID
     * @return 统计账户信息
     */
    public StatisticsAccount selectStatisticsAccountById(Long apiAccountId);

    /**
     * 查询统计账户信息列表
     * 
     * @param statisticsAccount 统计账户信息
     * @return 统计账户信息集合
     */
    public List<StatisticsAccount> selectStatisticsAccountList(StatisticsAccount statisticsAccount);
    
    /**
     * 通用查询统计账户信息列表
     * 
     * @param statisticsAccount 统计账户信息
     * @return 统计账户信息集合
     */
    public List<StatisticsAccount> findSelect(StatisticsAccount statisticsAccount);

    /**
     * 新增统计账户信息
     * 
     * @param statisticsAccount 统计账户信息
     * @return 结果
     */
    public int insertStatisticsAccount(StatisticsAccount statisticsAccount);

    /**
     * 修改统计账户信息
     * 
     * @param statisticsAccount 统计账户信息
     * @return 结果
     */
    public int updateStatisticsAccount(StatisticsAccount statisticsAccount);

    /**
     * 批量删除统计账户信息
     * 
     * @param apiAccountIds 需要删除的统计账户信息ID
     * @return 结果
     */
    public int deleteStatisticsAccountByIds(Long[] apiAccountIds);

    /**
     * 删除统计账户信息信息
     * 
     * @param apiAccountId 统计账户信息ID
     * @return 结果
     */
    public int deleteStatisticsAccountById(Long apiAccountId);
}
