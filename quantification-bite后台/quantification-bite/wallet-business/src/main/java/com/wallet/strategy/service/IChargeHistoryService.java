package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.ChargeHistory;

/**
 * 充值记录	Service接口
 * 
 * @author wallet
 * @date 2023-03-11
 */
public interface IChargeHistoryService 
{
    /**
     * 查询充值记录	
     * 
     * @param id 充值记录	ID
     * @return 充值记录	
     */
    public ChargeHistory selectChargeHistoryById(Long id);

    /**
     * 查询充值记录	列表
     * 
     * @param chargeHistory 充值记录	
     * @return 充值记录	集合
     */
    public List<ChargeHistory> selectChargeHistoryList(ChargeHistory chargeHistory);

    /**
     * 查询充值记录	列表
     *
     */
    public List<ChargeHistory> selectChargeHistoryListByAccountId(Long accountId);
    
    /**
     * 通用查询充值记录	列表
     * 
     * @param chargeHistory 充值记录	
     * @return 充值记录	集合
     */
    public List<ChargeHistory> findSelect(ChargeHistory chargeHistory);

    /**
     * 新增充值记录	
     * 
     * @param chargeHistory 充值记录	
     * @return 结果
     */
    public int insertChargeHistory(ChargeHistory chargeHistory);

    /**
     * 修改充值记录	
     * 
     * @param chargeHistory 充值记录	
     * @return 结果
     */
    public int updateChargeHistory(ChargeHistory chargeHistory);

    /**
     * 批量删除充值记录	
     * 
     * @param ids 需要删除的充值记录	ID
     * @return 结果
     */
    public int deleteChargeHistoryByIds(Long[] ids);

    /**
     * 删除充值记录	信息
     * 
     * @param id 充值记录	ID
     * @return 结果
     */
    public int deleteChargeHistoryById(Long id);
}
