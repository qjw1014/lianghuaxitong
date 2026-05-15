package com.wallet.version.service.impl;

import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import com.wallet.common.utils.DateUtils;
import com.wallet.version.domain.VersionUpgradeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.version.mapper.VersionUpgradeMapper;
import com.wallet.version.domain.VersionUpgrade;
import com.wallet.version.service.IVersionUpgradeService;

/**
 * app版本升级Service业务层处理
 * 
 * @author wallet
 * @date 2021-11-19
 */
@Service
public class VersionUpgradeServiceImpl implements IVersionUpgradeService 
{
    @Autowired
    private VersionUpgradeMapper versionUpgradeMapper;

    /**
     * 查询app版本升级
     * 
     * @param id app版本升级ID
     * @return app版本升级
     */
    @Override
    public VersionUpgrade selectVersionUpgradeById(Long id)
    {
        return versionUpgradeMapper.selectByPrimaryKey(id);
    }

    @Override
    public VersionUpgrade selectVersionUpgradeByAppId(String appId)
    {
        VersionUpgrade versionUpgrade  = new VersionUpgrade();
        versionUpgrade.setAppId(appId);
        return versionUpgradeMapper.selectOne(versionUpgrade);
    }

    /**
     * 查询app版本升级列表
     * 
     * @param versionUpgrade app版本升级
     * @return app版本升级
     */
    @Override
    public List<VersionUpgrade> selectVersionUpgradeList(VersionUpgrade versionUpgrade)
    {
        return versionUpgradeMapper.select(versionUpgrade);
    }
	 @Override
    public List<VersionUpgrade> findSelect(VersionUpgrade versionUpgrade)
    {
        return versionUpgradeMapper.select(versionUpgrade);
    }
    /**
     * 新增app版本升级
     * 
     * @param versionUpgrade app版本升级
     * @return 结果
     */
    @Override
    public int insertVersionUpgrade(VersionUpgrade versionUpgrade)
    {
		 return versionUpgradeMapper.insertSelective(versionUpgrade);
    }

    /**
     * 修改app版本升级
     * 
     * @param versionUpgrade app版本升级
     * @return 结果
     */
    @Override
    public int updateVersionUpgrade(VersionUpgrade versionUpgrade)
    {

        return versionUpgradeMapper.updateByPrimaryKeySelective(versionUpgrade);
    }

    /**
     * 批量删除app版本升级
     * 
     * @param ids 需要删除的app版本升级ID
     * @return 结果
     */
    @Override
    public int deleteVersionUpgradeByIds(Long[] ids)
    {
        return versionUpgradeMapper.deleteVersionUpgradeByIds(ids);
    }

    /**
     * 删除app版本升级信息
     * 
     * @param id app版本升级ID
     * @return 结果
     */
    @Override
    public int deleteVersionUpgradeById(Long id)
    {
		return versionUpgradeMapper.deleteByPrimaryKey(id);
        
    }

    public VersionUpgrade findAppId(String appId){
        VersionUpgradeExample testExample=new VersionUpgradeExample();
        testExample.setOrderByClause("id DESC");
        testExample.createCriteria().andAppIdEqualTo(appId);

        List<VersionUpgrade> list= versionUpgradeMapper.selectByExample(testExample);

        return  CollectionUtil.isEmpty(list)?new VersionUpgrade():list.get(0);
    }

}
