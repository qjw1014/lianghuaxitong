package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.MartinStrategyInfo;
import com.wallet.strategy.domain.vo.MartinStrategyInfoVO;

/**
 * 策略信息Service接口
 * 
 * @author wallet
 * @date 2022-08-31
 */
public interface IMartinStrategyInfoService 
{
    /**
     * 查询策略信息
     * 
     * @param strategyId 策略信息ID
     * @return 策略信息
     */
    public MartinStrategyInfoVO selectMartinStrategyInfoById(Long strategyId);

    /**
     * 查询策略信息列表
     * 
     * @param martinStrategyInfo 策略信息
     * @return 策略信息集合
     */
    public List<MartinStrategyInfo> selectMartinStrategyInfoList(MartinStrategyInfo martinStrategyInfo);

    /**
     * 查询策略信息列表
     *
     * @param martinStrategyInfo 策略信息
     * @return 策略信息集合
     */
    public List<MartinStrategyInfo> selectMartinStrategyInfoListOrderBy(MartinStrategyInfo martinStrategyInfo);
    
    /**
     * 通用查询策略信息列表
     * 
     * @param martinStrategyInfo 策略信息
     * @return 策略信息集合
     */
    public List<MartinStrategyInfo> findSelect(MartinStrategyInfo martinStrategyInfo);

    /**
     * 新增策略信息
     * 
     * @param martinStrategyInfo 策略信息
     * @return 结果
     */
    public int insertMartinStrategyInfo(MartinStrategyInfoVO martinStrategyInfo);

    /**
     * 修改策略信息
     * 
     * @param martinStrategyInfo 策略信息
     * @return 结果
     */
    public int updateMartinStrategyInfo(MartinStrategyInfoVO martinStrategyInfo);

    /**
     * 批量删除策略信息
     * 
     * @param strategyIds 需要删除的策略信息ID
     * @return 结果
     */
    public int deleteMartinStrategyInfoByIds(Long[] strategyIds);

    /**
     * 删除策略信息信息
     * 
     * @param strategyId 策略信息ID
     * @return 结果
     */
    public int deleteMartinStrategyInfoById(Long strategyId);

    /**
     * 业务删除
     * @param strategyId
     * @return
     */
    public int updateDelete(Long strategyId);
}
