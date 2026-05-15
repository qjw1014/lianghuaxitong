package com.wallet.version.service;

import java.util.List;
import com.wallet.version.domain.VersionUpgrade;

/**
 * app版本升级Service接口
 * 
 * @author wallet
 * @date 2021-11-19
 */
public interface IVersionUpgradeService 
{
    /**
     * 查询app版本升级
     * 
     * @param id app版本升级ID
     * @return app版本升级
     */
    public VersionUpgrade selectVersionUpgradeById(Long id);



    public VersionUpgrade selectVersionUpgradeByAppId(String appId);
    /**
     * 查询app版本升级列表
     * 
     * @param versionUpgrade app版本升级
     * @return app版本升级集合
     */
    public List<VersionUpgrade> selectVersionUpgradeList(VersionUpgrade versionUpgrade);
    
    /**
     * 通用查询app版本升级列表
     * 
     * @param versionUpgrade app版本升级
     * @return app版本升级集合
     */
    public List<VersionUpgrade> findSelect(VersionUpgrade versionUpgrade);


    /**
     * 新增app版本升级
     * 
     * @param versionUpgrade app版本升级
     * @return 结果
     */
    public int insertVersionUpgrade(VersionUpgrade versionUpgrade);

    /**
     * 修改app版本升级
     * 
     * @param versionUpgrade app版本升级
     * @return 结果
     */
    public int updateVersionUpgrade(VersionUpgrade versionUpgrade);

    /**
     * 批量删除app版本升级
     * 
     * @param ids 需要删除的app版本升级ID
     * @return 结果
     */
    public int deleteVersionUpgradeByIds(Long[] ids);

    /**
     * 删除app版本升级信息
     * 
     * @param id app版本升级ID
     * @return 结果
     */
    public int deleteVersionUpgradeById(Long id);

    public VersionUpgrade findAppId(String appId);
}
