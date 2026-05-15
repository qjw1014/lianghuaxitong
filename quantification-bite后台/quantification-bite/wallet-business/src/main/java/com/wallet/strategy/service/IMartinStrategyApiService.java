package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.MartinStrategyApi;

/**
 * api对应策略信息Service接口
 * 
 * @author wallet
 * @date 2022-09-05
 */
public interface IMartinStrategyApiService 
{
    /**
     * 查询api对应策略信息
     * 
     * @param id api对应策略信息ID
     * @return api对应策略信息
     */
    public MartinStrategyApi selectMartinStrategyApiById(Long id);

    /**
     * 查询api对应策略信息列表
     * 
     * @param martinStrategyApi api对应策略信息
     * @return api对应策略信息集合
     */
    public List<MartinStrategyApi> selectMartinStrategyApiList(MartinStrategyApi martinStrategyApi);
    
    /**
     * 通用查询api对应策略信息列表
     * 
     * @param martinStrategyApi api对应策略信息
     * @return api对应策略信息集合
     */
    public List<MartinStrategyApi> findSelect(MartinStrategyApi martinStrategyApi);

    /**
     * 新增api对应策略信息
     * 
     * @param martinStrategyApi api对应策略信息
     * @return 结果
     */
    public int insertMartinStrategyApi(MartinStrategyApi martinStrategyApi);

    /**
     * 修改api对应策略信息
     * 
     * @param martinStrategyApi api对应策略信息
     * @return 结果
     */
    public int updateMartinStrategyApi(MartinStrategyApi martinStrategyApi);

    /**
     * 修改api对应策略信息
     *
     * @param martinStrategyApi api对应策略信息
     * @return 结果
     */
    public int updateBaseRate(MartinStrategyApi martinStrategyApi);


    /**
     * 开启策略
     *
     * @param martinStrategyApi api对应策略信息
     * @return 结果
     */
    public int startMartinStrategy(MartinStrategyApi martinStrategyApi);


    /**
     * 停止策略
     *
     * @param martinStrategyApi api对应策略信息
     * @return 结果
     */
    public int stopMartinStrategy(MartinStrategyApi martinStrategyApi);


    /**
     * 批量删除api对应策略信息
     * 
     * @param ids 需要删除的api对应策略信息ID
     * @return 结果
     */
    public int deleteMartinStrategyApiByIds(Long[] ids);

    /**
     * 删除api对应策略信息信息
     * 
     * @param id api对应策略信息ID
     * @return 结果
     */
    public int deleteMartinStrategyApiById(Long id);

    /**
     * 修改策略类型
     * @return
     */
    public int updateStrategyType(Long strategyId,Long strategyType);

    /**
     * 删除策略
     * @return
     */
    public int deleteStrategyApi(Long strategyId);
}
