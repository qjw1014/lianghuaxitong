package com.wallet.sysDict.service.impl;

import java.util.Date;
import java.util.List;

import com.wallet.common.enums.Enum;
import com.wallet.common.exception.BaseException;
import com.wallet.common.utils.AESUtil;
import com.wallet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.sysDict.mapper.WalletConfigMapper;
import com.wallet.sysDict.domain.WalletConfig;
import com.wallet.sysDict.service.IWalletConfigService;
import tk.mybatis.mapper.entity.Example;

/**
 * 钱包配置信息Service业务层处理
 * 
 * @author wallet
 * @date 2022-03-02
 */
@Service
public class WalletConfigServiceImpl implements IWalletConfigService 
{
    @Autowired
    private WalletConfigMapper walletConfigMapper;

    /**
     * 查询钱包配置信息
     * 
     * @param id 钱包配置信息ID
     * @return 钱包配置信息
     */
    @Override
    public WalletConfig selectWalletConfigById(Long id)
    {
        return walletConfigMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询钱包配置信息列表
     * 
     * @param walletConfig 钱包配置信息
     * @return 钱包配置信息
     */
    @Override
    public List<WalletConfig> selectWalletConfigList(WalletConfig walletConfig)
    {
        return walletConfigMapper.select(walletConfig);
    }
	 @Override
    public List<WalletConfig> findSelect(WalletConfig walletConfig)
    {
        return walletConfigMapper.select(walletConfig);
    }
    /**
     * 新增钱包配置信息
     * 
     * @param walletConfig 钱包配置信息
     * @return 结果
     */
    @Override
    public int insertWalletConfig(WalletConfig walletConfig)
    {
        if(walletConfig.getPrivateKey().length()<10){
            throw new BaseException("秘钥异常！");
        }
         //判断code是否已存在
        WalletConfig query = new WalletConfig();
        query.setCode(walletConfig.getCode());
        WalletConfig config =  walletConfigMapper.selectOne(query);
        if(config!=null){
            throw new BaseException("业务类型已存在！");
        }
        //加密
        StringBuffer buffer = new StringBuffer();
        buffer.append(walletConfig.getAddress());
        buffer.append(AESUtil.SecretKeySpec);
        buffer.append(walletConfig.getPrivateKey());
        if(walletConfig.getType().equalsIgnoreCase(Enum.rule_validate.Y.getCode())){//是合约
            buffer.append(AESUtil.SecretKeySpec).append(walletConfig.getContractAddress());
        }
        String encryKey = AESUtil.encrypt(buffer.toString(),AESUtil.password);
        walletConfig.setEncryKey(encryKey);
        String privateKey = walletConfig.getPrivateKey();
        privateKey =privateKey.substring(0,3)+"..."+privateKey.substring(privateKey.length()-3);
        walletConfig.setPrivateKey(privateKey);
        walletConfig.setUpdateTime(new Date());
		return walletConfigMapper.insertSelective(walletConfig);
    }

    /**
     * 修改钱包配置信息
     * 
     * @param walletConfig 钱包配置信息
     * @return 结果
     */
    @Override
    public int updateWalletConfig(WalletConfig walletConfig)
    {
        //判断code是否已存在
        WalletConfig query = new WalletConfig();
        query.setCode(walletConfig.getCode());
        WalletConfig config =  walletConfigMapper.selectOne(query);
        if(config!=null){
            if(!config.getId().equals(walletConfig.getId())){
                throw new BaseException("业务类型已存在！");
            }
        }
        //加密
        StringBuffer buffer = new StringBuffer();
        buffer.append(walletConfig.getAddress());
        buffer.append(AESUtil.SecretKeySpec);
        buffer.append(walletConfig.getPrivateKey());
        if(walletConfig.getType().equalsIgnoreCase(Enum.rule_validate.Y.getCode())){//是合约
            buffer.append(AESUtil.SecretKeySpec).append(walletConfig.getContractAddress());
        }
        String encryKey = AESUtil.encrypt(buffer.toString(),AESUtil.password);
        walletConfig.setEncryKey(encryKey);
        String privateKey = walletConfig.getPrivateKey();
        privateKey =privateKey.substring(0,3)+"..."+privateKey.substring(privateKey.length()-3);
        walletConfig.setPrivateKey(privateKey);
        walletConfig.setUpdateTime(new Date());
        return walletConfigMapper.updateByPrimaryKeySelective(walletConfig);
    }

    /**
     * 批量删除钱包配置信息
     * 
     * @param ids 需要删除的钱包配置信息ID
     * @return 结果
     */
    @Override
    public int deleteWalletConfigByIds(Long[] ids)
    {
        return walletConfigMapper.deleteWalletConfigByIds(ids);
    }

    /**
     * 删除钱包配置信息信息
     * 
     * @param id 钱包配置信息ID
     * @return 结果
     */
    @Override
    public int deleteWalletConfigById(Long id)
    {
		return walletConfigMapper.deleteByPrimaryKey(id);
        
    }

    /**
     * 获取秘钥信息
     * @param code
     * @return
     */
    @Override
    public String findWalletConfigPrivateKey(String code) {
        WalletConfig config = new WalletConfig();
        config.setCode(code);
        WalletConfig walletConfig = walletConfigMapper.selectOne(config);
        if(walletConfig!=null){
            return walletConfig.getEncryKey();
        }
        return null;
    }

    /**
     * 查询信息
     * @param code
     * @return
     */
    @Override
    public WalletConfig findWalletConfigOne(String code) {
        WalletConfig config = new WalletConfig();
        config.setCode(code);
        WalletConfig walletConfig = walletConfigMapper.selectOne(config);
        return walletConfig;
    }

    /**
     * 查询list
     * @param list
     * @return
     */
    @Override
    public List<WalletConfig> findWalletConfigList(List<String> list) {
        Example example = new Example(WalletConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("code",list);
        return walletConfigMapper.selectByExample(example);
    }
}
