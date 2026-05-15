package com.wallet.chain.service;

import java.util.List;
import com.wallet.chain.domain.Chain;

/**
 * 链Service接口
 * 
 * @author wallet
 * @date 2021-10-25
 */
public interface IChainService 
{
    /**
     * 查询链
     * 
     * @param id 链ID
     * @return 链
     */
    public Chain selectChainById(Long id);

    /**
     * 查询链
     *
     * @param id 链ID
     * @return 链
     */
    public Chain selectChainByName(String chainName);

    /**
     * 查询链列表
     * 
     * @param chain 链
     * @return 链集合
     */
    public List<Chain> selectChainList(Chain chain);

    /**
     * 查询链列表
     *
     * @param chain 链
     * @return 链集合
     */
    public List<Chain> selectChainListAll(Chain chain);
    
    /**
     * 通用查询链列表
     * 
     * @param chain 链
     * @return 链集合
     */
    public List<Chain> findSelect(Chain chain);

    /**
     * 新增链
     * 
     * @param chain 链
     * @return 结果
     */
    public int insertChain(Chain chain);

    /**
     * 修改链
     * 
     * @param chain 链
     * @return 结果
     */
    public int updateChain(Chain chain);

    /**
     * 批量删除链
     * 
     * @param ids 需要删除的链ID
     * @return 结果
     */
    public int deleteChainByIds(Long[] ids);

    /**
     * 删除链信息
     * 
     * @param id 链ID
     * @return 结果
     */
    public int deleteChainById(Long id);
}
