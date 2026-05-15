package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.StrageyPositionInfo;

/**
 * 策略仓位Service接口
 * 
 * @author wallet
 * @date 2023-03-11
 */
public interface IStrageyPositionInfoService 
{
    /**
     * 查询策略仓位
     * 
     * @param id 策略仓位ID
     * @return 策略仓位
     */
    public StrageyPositionInfo selectStrageyPositionInfoById(Long id);


    /**
     * 根据子账号查询仓位列表信息
     *
     * @param accountId 策略仓位ID
     * @return 策略仓位
     */
    public List<StrageyPositionInfo> selectStrageyPositionInfoByAccount(Long accountId);

    /**
     * 查询策略仓位列表
     * 
     * @param strageyPositionInfo 策略仓位
     * @return 策略仓位集合
     */
    public List<StrageyPositionInfo> selectStrageyPositionInfoList(StrageyPositionInfo strageyPositionInfo);
    
    /**
     * 通用查询策略仓位列表
     * 
     * @param strageyPositionInfo 策略仓位
     * @return 策略仓位集合
     */
    public List<StrageyPositionInfo> findSelect(StrageyPositionInfo strageyPositionInfo);

    /**
     * 新增策略仓位
     * 
     * @param strageyPositionInfo 策略仓位
     * @return 结果
     */
    public int insertStrageyPositionInfo(StrageyPositionInfo strageyPositionInfo);

    /**
     * 修改策略仓位
     * 
     * @param strageyPositionInfo 策略仓位
     * @return 结果
     */
    public int updateStrageyPositionInfo(StrageyPositionInfo strageyPositionInfo);

    /**
     * 批量删除策略仓位
     * 
     * @param ids 需要删除的策略仓位ID
     * @return 结果
     */
    public int deleteStrageyPositionInfoByIds(Long[] ids);

    /**
     * 删除策略仓位信息
     * 
     * @param id 策略仓位ID
     * @return 结果
     */
    public int deleteStrageyPositionInfoById(Long id);
}
