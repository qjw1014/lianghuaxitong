package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.CoinInfo;

/**
 * 币种Service接口
 * 
 * @author wallet
 * @date 2022-09-01
 */
public interface ICoinInfoService 
{
    /**
     * 查询币种
     * 
     * @param id 币种ID
     * @return 币种
     */
    public CoinInfo selectCoinInfoById(Long id);

    /**
     * 查询币种列表
     * 
     * @param coinInfo 币种
     * @return 币种集合
     */
    public List<CoinInfo> selectCoinInfoList(CoinInfo coinInfo);
    
    /**
     * 通用查询币种列表
     * 
     * @param coinInfo 币种
     * @return 币种集合
     */
    public List<CoinInfo> findSelect(CoinInfo coinInfo);

    /**
     * 新增币种
     * 
     * @param coinInfo 币种
     * @return 结果
     */
    public int insertCoinInfo(CoinInfo coinInfo);

    /**
     * 修改币种
     * 
     * @param coinInfo 币种
     * @return 结果
     */
    public int updateCoinInfo(CoinInfo coinInfo);

    /**
     * 批量删除币种
     * 
     * @param ids 需要删除的币种ID
     * @return 结果
     */
    public int deleteCoinInfoByIds(Long[] ids);

    /**
     * 删除币种信息
     * 
     * @param id 币种ID
     * @return 结果
     */
    public int deleteCoinInfoById(Long id);
}
