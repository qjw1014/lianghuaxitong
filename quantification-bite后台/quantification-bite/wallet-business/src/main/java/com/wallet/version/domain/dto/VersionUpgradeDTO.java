package com.wallet.version.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wallet.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class VersionUpgradeDTO {

    /** 客户端ios android */
    @ApiModelProperty(value = "客户端ios android ")
    private String appId;


    /** 大版本id */
    @ApiModelProperty(value = "大版本id ")
    private Long versionId;


    /** 小版本id */
    @ApiModelProperty(value = "小版本id ")
    private Long versionMini;


    /** 版本号 */
    @ApiModelProperty(value = "当前版本号 ")
    private String versionCode;


    /** 1升级，0不升级，2强制升级 */

    @ApiModelProperty(value = "1升级，0不升级，2强制升级  ")
    private Integer type;


    /** 下载地址 */

    @ApiModelProperty(value = "下载地址 ")
    private String appUrl;


    /** 升级提示 */
    @ApiModelProperty(value = "升级提示 ")
    private String upgradePoint;


    /** 状态 Y 开启 N不开启 */

    @ApiModelProperty(value = "状态 Y 开启 N不开启 ")
    private String dataStatus;

    /** 当前在审核的版本 */

    @ApiModelProperty(value = "当前在审核的版本 ")
    private String reviewingVrsion;



    /** 最新版本 */

    @ApiModelProperty(value = "最新版本 ")
    private String latestVersion;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Long getVersionMini() {
        return versionMini;
    }

    public void setVersionMini(Long versionMini) {
        this.versionMini = versionMini;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getUpgradePoint() {
        return upgradePoint;
    }

    public void setUpgradePoint(String upgradePoint) {
        this.upgradePoint = upgradePoint;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getReviewingVrsion() {
        return reviewingVrsion;
    }

    public void setReviewingVrsion(String reviewingVrsion) {
        this.reviewingVrsion = reviewingVrsion;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间 ")
    private Date createTime;

}
