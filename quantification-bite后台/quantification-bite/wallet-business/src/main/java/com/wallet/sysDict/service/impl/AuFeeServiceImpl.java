package com.wallet.sysDict.service.impl;

import java.util.Date;
import java.util.List;

import com.wallet.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.sysDict.mapper.AuFeeMapper;
import com.wallet.sysDict.domain.AuFee;
import com.wallet.sysDict.service.IAuFeeService;
import tk.mybatis.mapper.entity.Example;

/**
 * 手续费配置Service业务层处理
 * 
 * @author wallet
 * @date 2022-02-24
 */
@Service
public class AuFeeServiceImpl implements IAuFeeService 
{
    @Autowired
    private AuFeeMapper auFeeMapper;

    /**
     * 查询手续费配置
     * 
     * @param id 手续费配置ID
     * @return 手续费配置
     */
    @Override
    public AuFee selectAuFeeById(Long id)
    {
        return auFeeMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询手续费配置列表
     * 
     * @param auFee 手续费配置
     * @return 手续费配置
     */
    @Override
    public List<AuFee> selectAuFeeList(AuFee auFee)
    {
        return auFeeMapper.select(auFee);
    }
	 @Override
    public List<AuFee> findSelect(AuFee auFee)
    {
        return auFeeMapper.select(auFee);
    }
    /**
     * 新增手续费配置
     * 
     * @param auFee 手续费配置
     * @return 结果
     */
    @Override
    public int insertAuFee(AuFee auFee)
    {
        auFee.setCreateTime(new Date());
        //查询类型是否已存在
        AuFee query = new AuFee();
        query.setFeeType(auFee.getFeeType());
        AuFee au = auFeeMapper.selectOne(query);
        if(au!=null){//说明已存在
            throw new BaseException("功能类型已存在！");
        }
		return auFeeMapper.insertSelective(auFee);
    }

    /**
     * 修改手续费配置
     * 
     * @param auFee 手续费配置
     * @return 结果
     */
    @Override
    public int updateAuFee(AuFee auFee)
    {
        //查询类型是否已存在
        AuFee query = new AuFee();
        query.setFeeType(auFee.getFeeType());
        AuFee au = auFeeMapper.selectOne(query);
        if(au!=null){//说明已存在
            if(!au.getId().equals(auFee.getId())){
                throw new BaseException("功能类型已存在！");
            }
        }
        return auFeeMapper.updateByPrimaryKeySelective(auFee);
    }

    /**
     * 批量删除手续费配置
     * 
     * @param ids 需要删除的手续费配置ID
     * @return 结果
     */
    @Override
    public int deleteAuFeeByIds(Long[] ids)
    {
        return auFeeMapper.deleteAuFeeByIds(ids);
    }

    /**
     * 删除手续费配置信息
     * 
     * @param id 手续费配置ID
     * @return 结果
     */
    @Override
    public int deleteAuFeeById(Long id)
    {
		return auFeeMapper.deleteByPrimaryKey(id);
        
    }

    /**
     * 根据类型查询信息
     * @param feeType
     * @return
     */
    @Override
    public AuFee findAuFeeByfeeType(String feeType) {
        Example example = new Example(AuFee.class);
        Example.Criteria criteria =  example.createCriteria();
        criteria.andEqualTo("feeType",feeType);
        return auFeeMapper.selectOneByExample(example);
    }
}
