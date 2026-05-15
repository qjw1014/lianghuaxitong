package com.wallet.chain.service.impl;

import java.util.Date;
import java.util.List;
import com.wallet.common.exception.BaseException;
import com.wallet.common.utils.Enum;
import com.wallet.common.utils.MessageUtils;
import com.wallet.common.utils.id.IDGenerater;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.chain.mapper.ChainMapper;
import com.wallet.chain.domain.Chain;
import com.wallet.chain.service.IChainService;
import tk.mybatis.mapper.entity.Example;

/**
 * 链Service业务层处理
 * 
 * @author wallet
 * @date 2021-10-25
 */
@Service
public class ChainServiceImpl implements IChainService 
{
    @Autowired
    private ChainMapper chainMapper;
    /**
     * 查询链
     * 
     * @param id 链ID
     * @return 链
     */
    @Override
    public Chain selectChainById(Long id)
    {
        return chainMapper.selectByPrimaryKey(id);
    }

    @Override
    public Chain selectChainByName(String chainName) {
        Example example = new Example(Chain.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("chainName",chainName);
        criteria.andEqualTo("status", com.wallet.common.enums.Enum.rule_validate.Y.getCode());
        return chainMapper.selectOneByExample(example);
    }

    /**
     * 查询链列表
     * 
     * @param chain 链
     * @return 链
     */
    @Override
    public List<Chain> selectChainList(Chain chain)
    {
        if(StringUtils.isBlank(chain.getMainCoinName())){
            chain.setMainCoinName(null);
        }
        return chainMapper.select(chain);
    }

    @Override
    public List<Chain> selectChainListAll(Chain chain) {
        if(chain==null){
            chain = new Chain();
        }
        chain.setStatus(Enum.Y_N.Y.getCode());
        return chainMapper.select(chain);    }

    @Override
    public List<Chain> findSelect(Chain chain)
    {
        return chainMapper.select(chain);
    }
    /**
     * 新增链
     * 
     * @param chain 链
     * @return 结果
     */
    @Override
    public int insertChain(Chain chain)
    {
        chain.setId(IDGenerater.nextId());
        chain.setCreateTime(new Date());
		return chainMapper.insertSelective(chain);
    }

    /**
     * 修改链
     * 
     * @param chain 链
     * @return 结果
     */
    @Override
    public int updateChain(Chain chain)
    {
        //查询链信息
        Chain old = chainMapper.selectByPrimaryKey(chain.getId());
        if(old==null){
            throw new BaseException(MessageUtils.message("chain.no.exist"));
        }
        int result = chainMapper.updateByPrimaryKeySelective(chain);
        if(result>0){
            if(StringUtils.isBlank(chain.getChainName())){
                return result;
            }
        }
        return result;
    }

    /**
     * 批量删除链
     * 
     * @param ids 需要删除的链ID
     * @return 结果
     */
    @Override
    public int deleteChainByIds(Long[] ids)
    {
        return chainMapper.deleteChainByIds(ids);
    }

    /**
     * 删除链信息
     * 
     * @param id 链ID
     * @return 结果
     */
    @Override
    public int deleteChainById(Long id)
    {
		return chainMapper.deleteByPrimaryKey(id);
        
    }
}
