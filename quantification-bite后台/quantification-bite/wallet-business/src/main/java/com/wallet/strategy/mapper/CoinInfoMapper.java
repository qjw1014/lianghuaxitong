package com.wallet.strategy.mapper;

import com.wallet.strategy.domain.CoinInfo;
import tk.mybatis.mapper.common.Mapper;
/**
 * 币种Mapper接口
 * 
 * @author wallet
 * @date 2022-09-01
 */
public interface CoinInfoMapper extends Mapper<CoinInfo>
{
    
    /**
     * 批量删除币种
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCoinInfoByIds(Long[] ids);

    /**
     * 修改临时币种
     * @param coinInfo
     * @return
     */
    int updateCoinFixed(CoinInfo coinInfo);
}
