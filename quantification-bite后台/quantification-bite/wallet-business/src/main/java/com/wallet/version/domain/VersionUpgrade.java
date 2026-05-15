package com.wallet.version.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;
import com.wallet.common.core.domain.BaseEntity;

/**
 * app版本升级对象 w_version_upgrade
 * 
 * @author wallet
 * @date 2021-11-19
 */
@Table(name = "w_version_upgrade")
public class VersionUpgrade
{
    private static final long serialVersionUID = 1L;


    /** ID */

	@Id
    @Excel(name = "ID")

    private Long id;


    /** 客户端ios android */

    @Excel(name = "客户端ios android")

    private String appId;


    /** 大版本id */

    @Excel(name = "大版本id")

    private Long versionId;


    /** 小版本id */

    @Excel(name = "小版本id")

    private Long versionMini;


    /** 版本号 */

    @Excel(name = "版本号")

    private String versionCode;


    /** 1升级，0不升级，2强制升级 */

    @Excel(name = "1升级，0不升级，2强制升级")

    private Integer type;


    /** 下载地址 */

    @Excel(name = "下载地址")

    private String appUrl;


    /** 升级提示 */

    @Excel(name = "升级提示")

    private String upgradePoint;


    /** 状态 Y 开启 N不开启 */

    @Excel(name = "状态 Y 开启 N不开启")

    private String dataStatus;

    /** 当前在审核的版本 */

    @Excel(name = "当前在审核的版本")

    private String reviewingVersion;



    /** 最新版本 */

    @Excel(name = "最新版本")

    private String latestVersion;

    /** 创建时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createTime;




    /** 更新时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date updateTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAppId(String appId) 
    {
        this.appId = appId;
    }

    public String getAppId() 
    {
        return appId;
    }
    public void setVersionId(Long versionId) 
    {
        this.versionId = versionId;
    }

    public Long getVersionId() 
    {
        return versionId;
    }
    public void setVersionMini(Long versionMini) 
    {
        this.versionMini = versionMini;
    }

    public Long getVersionMini() 
    {
        return versionMini;
    }
    public void setVersionCode(String versionCode) 
    {
        this.versionCode = versionCode;
    }

    public String getVersionCode() 
    {
        return versionCode;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setAppUrl(String appUrl) 
    {
        this.appUrl = appUrl;
    }

    public String getAppUrl() 
    {
        return appUrl;
    }
    public void setUpgradePoint(String upgradePoint) 
    {
        this.upgradePoint = upgradePoint;
    }

    public String getUpgradePoint() 
    {
        return upgradePoint;
    }
    public void setDataStatus(String dataStatus) 
    {
        this.dataStatus = dataStatus;
    }

    public String getDataStatus() 
    {
        return dataStatus;
    }
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }
    public void setUpdateTime(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }

    public String getReviewingVersion() {
        return reviewingVersion;
    }

    public void setReviewingVersion(String reviewingVersion) {
        this.reviewingVersion = reviewingVersion;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appId", getAppId())
            .append("versionId", getVersionId())
            .append("versionMini", getVersionMini())
            .append("versionCode", getVersionCode())
            .append("type", getType())
            .append("appUrl", getAppUrl())
            .append("upgradePoint", getUpgradePoint())
            .append("dataStatus", getDataStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
