package com.wallet.sysDict.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wallet.common.exception.BaseException;
import com.wallet.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.sysDict.mapper.SysDictMapper;
import com.wallet.sysDict.domain.SysDict;
import com.wallet.sysDict.service.ISysDictService;
import tk.mybatis.mapper.entity.Example;

/**
 * 参数配置Service业务层处理
 * 
 * @author wallet
 * @date 2022-01-06
 */
@Service
public class SysDictServiceImpl implements ISysDictService 
{
    @Autowired
    private SysDictMapper sysDictMapper;

    /**
     * 查询参数配置
     * 
     * @param id 参数配置ID
     * @return 参数配置
     */
    @Override
    public SysDict selectSysDictById(Long id)
    {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询参数配置列表
     * 
     * @param sysDict 参数配置
     * @return 参数配置
     */
    @Override
    public List<SysDict> selectSysDictList(SysDict sysDict)
    {
        return sysDictMapper.select(sysDict);
    }

    /**
     * 查询参数配置列表
     * @param sysDict 参数配置
     * @return
     */
    @Override
    public List<SysDict> selectSysDictListBy(SysDict sysDict) {
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(sysDict.getName())){
            criteria.andEqualTo("name",sysDict.getName());
        }
        if(StringUtils.isNotBlank(sysDict.getCode())){
            criteria.andEqualTo("code",sysDict.getCode());
        }
        if(sysDict.getPid()!=null){
            criteria.andEqualTo("pid",sysDict.getPid());
        }
        if(StringUtils.isNotBlank(sysDict.getTips())){
            criteria.andLike("tips",sysDict.getTips());
        }
        List<Long> list = new ArrayList<>();
        list.add(36L);
        list.add(55L);
        criteria.andNotIn("pid",list);
        List<Long> idList = new ArrayList<>();
        idList.add(36L);
        idList.add(55L);
        criteria.andNotIn("id",idList);
        example.setOrderByClause("num asc");
        return sysDictMapper.selectByExample(example);
    }

    /**
     * 查询参数配置列表
     * @param sysDict 参数配置
     * @return
     */
    @Override
    public List<SysDict> selectSysDictListParentBy(SysDict sysDict) {
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(sysDict.getName())){
            criteria.andEqualTo("name",sysDict.getName());
        }
        if(StringUtils.isNotBlank(sysDict.getCode())){
            criteria.andEqualTo("code",sysDict.getCode());
        }
        if(sysDict.getPid()!=null){
            criteria.andEqualTo("pid",sysDict.getPid());
        }
        if(StringUtils.isNotBlank(sysDict.getTips())){
            criteria.andLike("tips",sysDict.getTips());
        }
        List<Long> list = new ArrayList<>();
        list.add(36L);
        list.add(55L);
        criteria.andNotIn("id",list);
        return sysDictMapper.selectByExample(example);
    }

    @Override
    public List<SysDict> findSelect(SysDict sysDict)
    {
        return sysDictMapper.select(sysDict);
    }
    /**
     * 新增参数配置
     * 
     * @param sysDict 参数配置
     * @return 结果
     */
    @Override
    public int insertSysDict(SysDict sysDict)
    {
         if(StringUtils.isBlank(sysDict.getCode())){
             throw new BaseException("标识参数不能为null");
         }
         Example example = new Example(SysDict.class);
         Example.Criteria criteria = example.createCriteria();
         criteria.andEqualTo("code",sysDict.getCode());
         SysDict dict = sysDictMapper.selectOneByExample(example);
         if(dict!=null){
             throw new BaseException("标识已存在!");
         }
		 return sysDictMapper.insertSelective(sysDict);
    }

    /**
     * 修改参数配置
     * 
     * @param sysDict 参数配置
     * @return 结果
     */
    @Override
    public int updateSysDict(SysDict sysDict)
    {
        if(StringUtils.isBlank(sysDict.getCode())){
            throw new BaseException("标识参数不能为null");
        }
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code",sysDict.getCode());
        SysDict dict = sysDictMapper.selectOneByExample(example);
        if(dict!=null){
            if(!dict.getId().equals(sysDict.getId())){//不同
                throw new BaseException("标识已存在!");
            }
        }
        return sysDictMapper.updateByPrimaryKeySelective(sysDict);
    }

    /**
     * 批量删除参数配置
     * 
     * @param ids 需要删除的参数配置ID
     * @return 结果
     */
    @Override
    public int deleteSysDictByIds(Long[] ids)
    {
        return sysDictMapper.deleteSysDictByIds(ids);
    }

    /**
     * 删除参数配置信息
     * 
     * @param id 参数配置ID
     * @return 结果
     */
    @Override
    public int deleteSysDictById(Long id)
    {
		return sysDictMapper.deleteByPrimaryKey(id);
        
    }

    /**
     * 根据code获取常用配置信息
     * @param code
     * @return
     */
    @Override
    public String findDictByCode(String code) {
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code",code);
        SysDict dict = sysDictMapper.selectOneByExample(example);
        if(dict!=null){
            return dict.getName();
        }
        return null;
    }

    @Override
    public SysDict selectMbcByCode(SysDict sysDict)
    {
        return sysDictMapper.selectOne(sysDict);
    }

    /**
     * 查询多个
     * @param list
     * @return
     */
    @Override
    public List<SysDict> findDictList(List<String> list) {
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("code",list);
        return sysDictMapper.selectByExample(example);
    }
}
