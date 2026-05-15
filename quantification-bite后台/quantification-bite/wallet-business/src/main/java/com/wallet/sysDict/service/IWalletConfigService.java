package com.wallet.sysDict.service;

import java.util.List;
import com.wallet.sysDict.domain.WalletConfig;

/**
 * 钱包配置信息Service接口
 * 
 * @author wallet
 * @date 2022-03-02
 */
public interface IWalletConfigService 
{
    /**
     * 查询钱包配置信息
     * 
     * @param id 钱包配置信息ID
     * @return 钱包配置信息
     */
    public WalletConfig selectWalletConfigById(Long id);

    /**
     * 查询钱包配置信息列表
     * 
     * @param walletConfig 钱包配置信息
     * @return 钱包配置信息集合
     */
    public List<WalletConfig> selectWalletConfigList(WalletConfig walletConfig);
    
    /**
     * 通用查询钱包配置信息列表
     * 
     * @param walletConfig 钱包配置信息
     * @return 钱包配置信息集合
     */
    public List<WalletConfig> findSelect(WalletConfig walletConfig);

    /**
     * 新增钱包配置信息
     * 
     * @param walletConfig 钱包配置信息
     * @return 结果
     */
    public int insertWalletConfig(WalletConfig walletConfig);

    /**
     * 修改钱包配置信息
     * 
     * @param walletConfig 钱包配置信息
     * @return 结果
     */
    public int updateWalletConfig(WalletConfig walletConfig);

    /**
     * 批量删除钱包配置信息
     * 
     * @param ids 需要删除的钱包配置信息ID
     * @return 结果
     */
    public int deleteWalletConfigByIds(Long[] ids);

    /**
     * 删除钱包配置信息信息
     * 
     * @param id 钱包配置信息ID
     * @return 结果
     */
    public int deleteWalletConfigById(Long id);

    /**
     * 查询秘钥信息
     * @param code
     * @return
     */
    String findWalletConfigPrivateKey(String code);


    /**
     * 查询秘钥信息
     * @param code
     * @return
     */
    WalletConfig findWalletConfigOne(String code);

    /**
     * 查询list
     * @param list
     * @return
     */
    List<WalletConfig> findWalletConfigList(List<String> list);
}
