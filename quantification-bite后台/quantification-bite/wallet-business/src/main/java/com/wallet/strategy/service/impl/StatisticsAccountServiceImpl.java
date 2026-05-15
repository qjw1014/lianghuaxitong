package com.wallet.strategy.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.DateUtils;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.StatisticsAccountMapper;
import com.wallet.strategy.domain.StatisticsAccount;
import com.wallet.strategy.service.IStatisticsAccountService;

/**
 * 统计账户信息Service业务层处理
 * 
 * @author wallet
 * @date 2023-03-11
 */
@Service
public class StatisticsAccountServiceImpl implements IStatisticsAccountService 
{
    @Autowired
    private StatisticsAccountMapper statisticsAccountMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询统计账户信息
     * 
     * @param apiAccountId 统计账户信息ID
     * @return 统计账户信息
     */
    @Override
    public StatisticsAccount selectStatisticsAccountById(Long apiAccountId)
    {
        Object object = redisUtil.hget(StrategyRedisConfig.b_account_detail,String.valueOf(apiAccountId));
        if(ObjectUtil.isNotNull(object)){
            JSONObject json = JSONObject.parseObject(object.toString());
            //获取本金
            BigDecimal initUsdt =json.containsKey("init_usdt")?json.getBigDecimal("init_usdt"): BigDecimal.ZERO;
            //获取充提
            BigDecimal charge =json.containsKey("charge")?json.getBigDecimal("charge"): BigDecimal.ZERO;
            //最新净值
            BigDecimal currentUsdt =json.containsKey("current_usdt")?json.getBigDecimal("current_usdt"): BigDecimal.ZERO;
            //7天收益
            BigDecimal days7Profit =json.containsKey("7days_profit")?json.getBigDecimal("7days_profit"): BigDecimal.ZERO;
            //7日年化收益率
            BigDecimal days7ProfitFloat =json.containsKey("7days_profit_float")?json.getBigDecimal("7days_profit_float"): BigDecimal.ZERO;
            //30天收益
            BigDecimal days30Profit =json.containsKey("30days_profit")?json.getBigDecimal("30days_profit"): BigDecimal.ZERO;
            //30日年化收益率
            BigDecimal days30ProfitFloat =json.containsKey("30days_profit_float")?json.getBigDecimal("30days_profit_float"): BigDecimal.ZERO;
            //累计收益
            BigDecimal totalProfit =json.containsKey("total_profit")?json.getBigDecimal("total_profit"): BigDecimal.ZERO;
            //累计年化收益率
            BigDecimal totalProfitFloat =json.containsKey("total_profit_float")?json.getBigDecimal("total_profit_float"): BigDecimal.ZERO;
            StatisticsAccount account = statisticsAccountMapper.selectByPrimaryKey(apiAccountId);
            if(account!=null){
                account.setInitUsdt(initUsdt.stripTrailingZeros());
                account.setCharge(charge.stripTrailingZeros());
                account.setCurrentUsdt(currentUsdt.stripTrailingZeros());
                account.setDays7Profit(days7Profit.stripTrailingZeros());
                account.setDays7ProfitFloat(days7ProfitFloat.stripTrailingZeros());
                account.setDays30Profit(days30Profit.stripTrailingZeros());
                account.setDays30ProfitFloat(days30ProfitFloat);
                account.setTotalProfit(totalProfit.stripTrailingZeros());
                account.setTotalProfitFloat(totalProfitFloat.stripTrailingZeros());
                account.setUpdateTime(new Date());
                statisticsAccountMapper.updateByPrimaryKey(account);
            }else{
                account = new StatisticsAccount();
                account.setApiAccountId(apiAccountId);
                account.setInitUsdt(initUsdt.stripTrailingZeros());
                account.setCharge(charge.stripTrailingZeros());
                account.setCurrentUsdt(currentUsdt.stripTrailingZeros());
                account.setDays7Profit(days7Profit.stripTrailingZeros());
                account.setDays7ProfitFloat(days7ProfitFloat.stripTrailingZeros());
                account.setDays30Profit(days30Profit.stripTrailingZeros());
                account.setDays30ProfitFloat(days30ProfitFloat);
                account.setTotalProfit(totalProfit.stripTrailingZeros());
                account.setTotalProfitFloat(totalProfitFloat.stripTrailingZeros());
                account.setUpdateTime(new Date());
                statisticsAccountMapper.insertSelective(account);
            }
            return account;
        }
        return statisticsAccountMapper.selectByPrimaryKey(apiAccountId);
    }

    /**
     * 查询统计账户信息列表
     * 
     * @param statisticsAccount 统计账户信息
     * @return 统计账户信息
     */
    @Override
    public List<StatisticsAccount> selectStatisticsAccountList(StatisticsAccount statisticsAccount)
    {
        return statisticsAccountMapper.select(statisticsAccount);
    }
	 @Override
    public List<StatisticsAccount> findSelect(StatisticsAccount statisticsAccount)
    {
        return statisticsAccountMapper.select(statisticsAccount);
    }
    /**
     * 新增统计账户信息
     * 
     * @param statisticsAccount 统计账户信息
     * @return 结果
     */
    @Override
    public int insertStatisticsAccount(StatisticsAccount statisticsAccount)
    {
		 return statisticsAccountMapper.insertSelective(statisticsAccount);
    }

    /**
     * 修改统计账户信息
     * 
     * @param statisticsAccount 统计账户信息
     * @return 结果
     */
    @Override
    public int updateStatisticsAccount(StatisticsAccount statisticsAccount)
    {

        return statisticsAccountMapper.updateByPrimaryKeySelective(statisticsAccount);
    }

    /**
     * 批量删除统计账户信息
     * 
     * @param apiAccountIds 需要删除的统计账户信息ID
     * @return 结果
     */
    @Override
    public int deleteStatisticsAccountByIds(Long[] apiAccountIds)
    {
        return statisticsAccountMapper.deleteStatisticsAccountByIds(apiAccountIds);
    }

    /**
     * 删除统计账户信息信息
     * 
     * @param apiAccountId 统计账户信息ID
     * @return 结果
     */
    @Override
    public int deleteStatisticsAccountById(Long apiAccountId)
    {
		return statisticsAccountMapper.deleteByPrimaryKey(apiAccountId);
        
    }
}
