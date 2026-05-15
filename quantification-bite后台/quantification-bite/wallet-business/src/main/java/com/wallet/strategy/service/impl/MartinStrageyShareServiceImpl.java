package com.wallet.strategy.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.wallet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.MartinStrageyShareMapper;
import com.wallet.strategy.domain.MartinStrageyShare;
import com.wallet.strategy.service.IMartinStrageyShareService;

/**
 * 分润统计Service业务层处理
 * 
 * @author wallet
 * @date 2022-10-31
 */
@Service
public class MartinStrageyShareServiceImpl implements IMartinStrageyShareService 
{
    @Autowired
    private MartinStrageyShareMapper martinStrageyShareMapper;

    /**
     * 查询分润统计
     * 
     * @param id 分润统计ID
     * @return 分润统计
     */
    @Override
    public MartinStrageyShare selectMartinStrageyShareById(Long id)
    {
        return martinStrageyShareMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询分润统计列表
     * 
     * @param martinStrageyShare 分润统计
     * @return 分润统计
     */
    @Override
    public List<MartinStrageyShare> selectMartinStrageyShareList(MartinStrageyShare martinStrageyShare)
    {
        return martinStrageyShareMapper.select(martinStrageyShare);
    }
	 @Override
    public List<MartinStrageyShare> findSelect(MartinStrageyShare martinStrageyShare)
    {
        return martinStrageyShareMapper.select(martinStrageyShare);
    }
    /**
     * 新增分润统计
     * 
     * @param martinStrageyShare 分润统计
     * @return 结果
     */
    @Override
    public int insertMartinStrageyShare(MartinStrageyShare martinStrageyShare)
    {
		 return martinStrageyShareMapper.insertSelective(martinStrageyShare);
    }

    /**
     * 修改分润统计
     * 
     * @param martinStrageyShare 分润统计
     * @return 结果
     */
    @Override
    public int updateMartinStrageyShare(MartinStrageyShare martinStrageyShare)
    {

        return martinStrageyShareMapper.updateByPrimaryKeySelective(martinStrageyShare);
    }

    /**
     * 批量删除分润统计
     * 
     * @param ids 需要删除的分润统计ID
     * @return 结果
     */
    @Override
    public int deleteMartinStrageyShareByIds(Long[] ids)
    {
        return martinStrageyShareMapper.deleteMartinStrageyShareByIds(ids);
    }

    /**
     * 删除分润统计信息
     * 
     * @param id 分润统计ID
     * @return 结果
     */
    @Override
    public int deleteMartinStrageyShareById(Long id)
    {
		return martinStrageyShareMapper.deleteByPrimaryKey(id);
        
    }

    /**
     * 统计总分润收益
     * @return
     */
    @Override
    public BigDecimal sumShareIncome() {
        return martinStrageyShareMapper.sumShareIncome();
    }
}
