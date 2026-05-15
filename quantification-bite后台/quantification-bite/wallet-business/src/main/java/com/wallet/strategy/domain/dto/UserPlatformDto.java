package com.wallet.strategy.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户平台私钥信息对象
 * 
 * @author wallet
 * @date 2022-09-01
 */
@ApiModel("用户平台私钥信息对象")
public class UserPlatformDto
{

    /** 流水编号 */
    @ApiModelProperty(value = "流水编号")
    private Long id;


    /** 用户随机id */
    @ApiModelProperty(value = "用户随机id")
    private String userUuid;


    /** 用户手机号 */

    @ApiModelProperty(value = "用户手机号")
    private String phone;


    /** 平台名称 */

    @ApiModelProperty(value = "平台名称")
    private String platformName;


    /** 名称 */

    @ApiModelProperty(value = "名称")
    private String name;


    /** key */

    @ApiModelProperty(value = "key")
    private String appkey;


    /** 平台私钥 */

    @ApiModelProperty(value = "平台私钥")
    private String appsecret;


    /** 交易密码 */

    @ApiModelProperty(value = "交易密码")
    private String tradePassword;


    /** 初始资金 */
    @ApiModelProperty(value = "初始资金")
    private BigDecimal initUsdt;

    /** 初始资金 */
    @ApiModelProperty(value = "初始资金")
    private BigDecimal currentUsdt;


    /** 是否开启 N否 Y是 */

    @ApiModelProperty(value = "是否开启 N否 Y是")
    private String isEnabled;


    /** 是否删除 N否 Y是 */
    @ApiModelProperty(value = "是否删除 N否 Y是")
    private String isDelete;


    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value ="创建时间")
    private Date createDate;


    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value ="更新时间")
    private Date updateDate;


    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value ="到期时间")
    private Date expirationDate;


    /** 同步时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value ="同步时间")
    private Date syncDate;


    /** 状态 N否 Y是 */
    @ApiModelProperty(value ="状态 N否 Y是")
    private String syncStatus;

    @ApiModelProperty(value ="总收益")
    private BigDecimal totalIncome;

    @ApiModelProperty(value ="周收益")
    private BigDecimal currentIncome;

    @ApiModelProperty(value ="分润")
    private BigDecimal shareIncome;


    @ApiModelProperty(value = "控制列表")
    private List<MartinStrategyApiDto> strategyApiList;

    /** 分润比例 */
    @ApiModelProperty(value = "分润比例")
    private BigDecimal shareRate;

    public BigDecimal getShareRate() {
        return shareRate;
    }

    public void setShareRate(BigDecimal shareRate) {
        this.shareRate = shareRate;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(BigDecimal currentIncome) {
        this.currentIncome = currentIncome;
    }

    public BigDecimal getShareIncome() {
        return shareIncome;
    }

    public void setShareIncome(BigDecimal shareIncome) {
        this.shareIncome = shareIncome;
    }

    public BigDecimal getCurrentUsdt() {
        return currentUsdt;
    }

    public void setCurrentUsdt(BigDecimal currentUsdt) {
        this.currentUsdt = currentUsdt;
    }

    public BigDecimal getInitUsdt() {
        return initUsdt;
    }

    public void setInitUsdt(BigDecimal initUsdt) {
        this.initUsdt = initUsdt;
    }

    public List<MartinStrategyApiDto> getStrategyApiList() {
        return strategyApiList;
    }

    public void setStrategyApiList(List<MartinStrategyApiDto> strategyApiList) {
        this.strategyApiList = strategyApiList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }
}
