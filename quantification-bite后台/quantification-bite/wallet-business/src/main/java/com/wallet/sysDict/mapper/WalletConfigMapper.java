package com.wallet.sysDict.mapper;

import java.util.List;
import com.wallet.sysDict.domain.WalletConfig;
import tk.mybatis.mapper.common.Mapper;
/**
 * 钱包配置信息Mapper接口
 * 
 * @author wallet
 * @date 2022-03-02
 */
public interface WalletConfigMapper extends Mapper<WalletConfig>
{
    
    /**
     * 批量删除钱包配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWalletConfigByIds(Long[] ids);
}
