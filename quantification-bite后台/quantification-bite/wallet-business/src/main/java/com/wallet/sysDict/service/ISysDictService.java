package com.wallet.sysDict.service;

import java.util.List;
import com.wallet.sysDict.domain.SysDict;

/**
 * 参数配置Service接口
 * 
 * @author wallet
 * @date 2022-01-06
 */
public interface ISysDictService 
{
    /**
     * 查询参数配置
     * 
     * @param id 参数配置ID
     * @return 参数配置
     */
    public SysDict selectSysDictById(Long id);

    /**
     * 查询参数配置列表
     * 
     * @param sysDict 参数配置
     * @return 参数配置集合
     */
    public List<SysDict> selectSysDictList(SysDict sysDict);


    /**
     * 查询参数配置列表
     *
     * @param sysDict 参数配置
     * @return 参数配置集合
     */
    public List<SysDict> selectSysDictListBy(SysDict sysDict);

    /**
     * 查询参数配置列表
     *
     * @param sysDict 参数配置
     * @return 参数配置集合
     */
    public List<SysDict> selectSysDictListParentBy(SysDict sysDict);
    
    /**
     * 通用查询参数配置列表
     * 
     * @param sysDict 参数配置
     * @return 参数配置集合
     */
    public List<SysDict> findSelect(SysDict sysDict);

    /**
     * 新增参数配置
     * 
     * @param sysDict 参数配置
     * @return 结果
     */
    public int insertSysDict(SysDict sysDict);

    /**
     * 修改参数配置
     * 
     * @param sysDict 参数配置
     * @return 结果
     */
    public int updateSysDict(SysDict sysDict);

    /**
     * 批量删除参数配置
     * 
     * @param ids 需要删除的参数配置ID
     * @return 结果
     */
    public int deleteSysDictByIds(Long[] ids);

    /**
     * 删除参数配置信息
     * 
     * @param id 参数配置ID
     * @return 结果
     */
    public int deleteSysDictById(Long id);

    /**
     * 根据配置code获取信息
     * @param code
     * @return
     */
    String findDictByCode(String code);

    /**
     * 查询mbc转账密文
     */
    public  SysDict  selectMbcByCode(SysDict sysDict);

    /**
     * 查询list,多个
     * @param list
     * @return
     */
    List<SysDict> findDictList(List<String> list);
}
