package com.wallet.sysDict.service;

import java.util.List;
import com.wallet.sysDict.domain.AuFee;

/**
 * 手续费配置Service接口
 * 
 * @author wallet
 * @date 2022-02-24
 */
public interface IAuFeeService 
{
    /**
     * 查询手续费配置
     * 
     * @param id 手续费配置ID
     * @return 手续费配置
     */
    public AuFee selectAuFeeById(Long id);

    /**
     * 查询手续费配置列表
     * 
     * @param auFee 手续费配置
     * @return 手续费配置集合
     */
    public List<AuFee> selectAuFeeList(AuFee auFee);
    
    /**
     * 通用查询手续费配置列表
     * 
     * @param auFee 手续费配置
     * @return 手续费配置集合
     */
    public List<AuFee> findSelect(AuFee auFee);

    /**
     * 新增手续费配置
     * 
     * @param auFee 手续费配置
     * @return 结果
     */
    public int insertAuFee(AuFee auFee);

    /**
     * 修改手续费配置
     * 
     * @param auFee 手续费配置
     * @return 结果
     */
    public int updateAuFee(AuFee auFee);

    /**
     * 批量删除手续费配置
     * 
     * @param ids 需要删除的手续费配置ID
     * @return 结果
     */
    public int deleteAuFeeByIds(Long[] ids);

    /**
     * 删除手续费配置信息
     * 
     * @param id 手续费配置ID
     * @return 结果
     */
    public int deleteAuFeeById(Long id);

    /**
     * 根据feeType查询手续费信息
     */
    AuFee findAuFeeByfeeType(String feeType);
}
