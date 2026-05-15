package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.RevenueDetail;

/**
 * 收益明细	Service接口
 * 
 * @author wallet
 * @date 2023-03-11
 */
public interface IRevenueDetailService 
{
    /**
     * 查询收益明细	
     * 
     * @param id 收益明细	ID
     * @return 收益明细	
     */
    public RevenueDetail selectRevenueDetailById(Long id);

    /**
     * 查询收益明细	列表
     * 
     * @param revenueDetail 收益明细	
     * @return 收益明细	集合
     */
    public List<RevenueDetail> selectRevenueDetailList(RevenueDetail revenueDetail);


    /**
     * 查询收益明细	列表
     * @param apiAccountId 收益明细
     * @return 收益明细	集合
     */
    public List<RevenueDetail> selectRevenueDetailList(Long apiAccountId);
    
    /**
     * 通用查询收益明细	列表
     * 
     * @param revenueDetail 收益明细	
     * @return 收益明细	集合
     */
    public List<RevenueDetail> findSelect(RevenueDetail revenueDetail);

    /**
     * 新增收益明细	
     * 
     * @param revenueDetail 收益明细	
     * @return 结果
     */
    public int insertRevenueDetail(RevenueDetail revenueDetail);

    /**
     * 修改收益明细	
     * 
     * @param revenueDetail 收益明细	
     * @return 结果
     */
    public int updateRevenueDetail(RevenueDetail revenueDetail);

    /**
     * 批量删除收益明细	
     * 
     * @param ids 需要删除的收益明细	ID
     * @return 结果
     */
    public int deleteRevenueDetailByIds(Long[] ids);

    /**
     * 删除收益明细	信息
     * 
     * @param id 收益明细	ID
     * @return 结果
     */
    public int deleteRevenueDetailById(Long id);
}
