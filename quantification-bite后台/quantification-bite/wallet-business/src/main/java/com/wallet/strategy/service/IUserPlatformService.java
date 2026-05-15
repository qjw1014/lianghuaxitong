package com.wallet.strategy.service;

import java.math.BigDecimal;
import java.util.List;

import com.wallet.common.core.domain.entity.SysMenu;
import com.wallet.strategy.domain.UserPlatform;

/**
 * 用户平台私钥信息Service接口
 * 
 * @author wallet
 * @date 2022-09-01
 */
public interface IUserPlatformService 
{
    /**
     * 查询用户平台私钥信息
     * 
     * @param id 用户平台私钥信息ID
     * @return 用户平台私钥信息
     */
    public UserPlatform selectUserPlatformById(Long id);

    /**
     * 查询用户平台私钥信息列表
     * 
     * @param userPlatform 用户平台私钥信息
     * @return 用户平台私钥信息集合
     */
    public List<UserPlatform> selectUserPlatformList(UserPlatform userPlatform);
    
    /**
     * 通用查询用户平台私钥信息列表
     * 
     * @param userPlatform 用户平台私钥信息
     * @return 用户平台私钥信息集合
     */
    public List<UserPlatform> findSelect(UserPlatform userPlatform);

    /**
     * 新增用户平台私钥信息
     * 
     * @param userPlatform 用户平台私钥信息
     * @return 结果
     */
    public int insertUserPlatform(UserPlatform userPlatform);

    /**
     * 修改用户平台私钥信息
     * 
     * @param userPlatform 用户平台私钥信息
     * @return 结果
     */
    public int updateUserPlatform(UserPlatform userPlatform);

    /**
     * 批量删除用户平台私钥信息
     * 
     * @param ids 需要删除的用户平台私钥信息ID
     * @return 结果
     */
    public int deleteUserPlatformByIds(Long[] ids);


    /**
     * 删除用户平台私钥信息信息
     * 
     * @param id 用户平台私钥信息ID
     * @return 结果
     */
    public int deleteUserPlatformById(Long id);

    /**
     * 查询key是否已存在
     * @param key
     * @return
     */
    public int findUserPlatformByKey(String key);

    /**
     * 查询名称是否已存在
     * @return
     */
    public int findUserPlatformByName(String name,Long id);

    /**
     * 业务删除数据
     * @return 结果
     */
    public int updateUserPlatformByDelete(Long id);


    /**
     * 确定分润
     * @return 结果
     */
    public int deterShare(Long accountId, BigDecimal shareRate);


    /**
     * 添加api为菜单名称
     * @return 结果
     */
    public void insertMenu(UserPlatform userPlatform);

    /**
     * 修改菜单名称
     * @return 结果
     */
    public void updateMenuName(UserPlatform userPlatform);


    /**
     * 修改菜单名称
     * @return 结果
     */
    public void deleteMenuName(UserPlatform userPlatform);


}
