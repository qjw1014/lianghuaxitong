package com.wallet.strategy.service;

import java.util.List;
import com.wallet.strategy.domain.RevenueCurve;

/**
 * 收益曲线Service接口
 * 
 * @author wallet
 * @date 2023-03-11
 */
public interface IRevenueCurveService 
{
    /**
     * 查询收益曲线
     * 
     * @param id 收益曲线ID
     * @return 收益曲线
     */
    public RevenueCurve selectRevenueCurveById(Long id);

    /**
     * 查询收益曲线列表
     * 
     * @param revenueCurve 收益曲线
     * @return 收益曲线集合
     */
    public List<RevenueCurve> selectRevenueCurveList(RevenueCurve revenueCurve);

    /**
     * 查询所有收益信息，按时间排序
     * @return 收益曲线集合
     */
    public List<RevenueCurve> selectRevenueCurveListOrderByTime(Long accountId);
    
    /**
     * 通用查询收益曲线列表
     * 
     * @param revenueCurve 收益曲线
     * @return 收益曲线集合
     */
    public List<RevenueCurve> findSelect(RevenueCurve revenueCurve);

    /**
     * 新增收益曲线
     * 
     * @param revenueCurve 收益曲线
     * @return 结果
     */
    public int insertRevenueCurve(RevenueCurve revenueCurve);

    /**
     * 修改收益曲线
     * 
     * @param revenueCurve 收益曲线
     * @return 结果
     */
    public int updateRevenueCurve(RevenueCurve revenueCurve);

    /**
     * 批量删除收益曲线
     * 
     * @param ids 需要删除的收益曲线ID
     * @return 结果
     */
    public int deleteRevenueCurveByIds(Long[] ids);

    /**
     * 删除收益曲线信息
     * 
     * @param id 收益曲线ID
     * @return 结果
     */
    public int deleteRevenueCurveById(Long id);
}
