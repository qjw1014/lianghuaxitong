package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.Platform;

/**
 * 交易平台Service接口
 * 
 * @author wallet
 * @date 2022-09-01
 */
public interface IPlatformService 
{
    /**
     * 查询交易平台
     * 
     * @param id 交易平台ID
     * @return 交易平台
     */
    public Platform selectPlatformById(Long id);

    /**
     * 查询交易平台列表
     * 
     * @param platform 交易平台
     * @return 交易平台集合
     */
    public List<Platform> selectPlatformList(Platform platform);
    
    /**
     * 通用查询交易平台列表
     * 
     * @param platform 交易平台
     * @return 交易平台集合
     */
    public List<Platform> findSelect(Platform platform);

    /**
     * 新增交易平台
     * 
     * @param platform 交易平台
     * @return 结果
     */
    public int insertPlatform(Platform platform);

    /**
     * 修改交易平台
     * 
     * @param platform 交易平台
     * @return 结果
     */
    public int updatePlatform(Platform platform);

    /**
     * 批量删除交易平台
     * 
     * @param ids 需要删除的交易平台ID
     * @return 结果
     */
    public int deletePlatformByIds(Long[] ids);

    /**
     * 删除交易平台信息
     * 
     * @param id 交易平台ID
     * @return 结果
     */
    public int deletePlatformById(Long id);
}
