package com.wallet.strategy.service;

import java.math.BigDecimal;
import java.util.List;
import com.wallet.strategy.domain.MartinStrageyShare;

/**
 * 分润统计Service接口
 * 
 * @author wallet
 * @date 2022-10-31
 */
public interface IMartinStrageyShareService 
{
    /**
     * 查询分润统计
     * 
     * @param id 分润统计ID
     * @return 分润统计
     */
    public MartinStrageyShare selectMartinStrageyShareById(Long id);

    /**
     * 查询分润统计列表
     * 
     * @param martinStrageyShare 分润统计
     * @return 分润统计集合
     */
    public List<MartinStrageyShare> selectMartinStrageyShareList(MartinStrageyShare martinStrageyShare);
    
    /**
     * 通用查询分润统计列表
     * 
     * @param martinStrageyShare 分润统计
     * @return 分润统计集合
     */
    public List<MartinStrageyShare> findSelect(MartinStrageyShare martinStrageyShare);

    /**
     * 新增分润统计
     * 
     * @param martinStrageyShare 分润统计
     * @return 结果
     */
    public int insertMartinStrageyShare(MartinStrageyShare martinStrageyShare);

    /**
     * 修改分润统计
     * 
     * @param martinStrageyShare 分润统计
     * @return 结果
     */
    public int updateMartinStrageyShare(MartinStrageyShare martinStrageyShare);

    /**
     * 批量删除分润统计
     * 
     * @param ids 需要删除的分润统计ID
     * @return 结果
     */
    public int deleteMartinStrageyShareByIds(Long[] ids);

    /**
     * 删除分润统计信息
     * 
     * @param id 分润统计ID
     * @return 结果
     */
    public int deleteMartinStrageyShareById(Long id);

    /**
     * 查询分润收益
     */
    public BigDecimal sumShareIncome();
}
