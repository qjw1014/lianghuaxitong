package com.wallet.strategy.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;
import com.wallet.common.core.domain.BaseEntity;

/**
 * 用户平台私钥信息对象 t_user_platform
 * 
 * @author wallet
 * @date 2022-09-01
 */
@Table(name = "t_user_platform")
public class UserPlatform
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /** 用户随机id */

    @Excel(name = "用户随机id")

    private String userUuid;


    /** 用户手机号 */

    @Excel(name = "用户手机号")

    private String phone;


    /** 平台名称 */

    @Excel(name = "平台名称")

    private String platformName;


    /** 名称 */

    @Excel(name = "名称")

    private String name;


    /** key */

    @Excel(name = "key")

    private String appkey;


    /** 平台私钥 */

    @Excel(name = "平台私钥")

    private String appsecret;


    /** 交易密码 */

    @Excel(name = "交易密码")

    private String tradePassword;


    /** 初始资金 */
    @Excel(name = "初始资金")
    private BigDecimal initUsdt;


    /** 是否开启 N否 Y是 */

    @Excel(name = "是否开启 N否 Y是")

    private String isEnabled;


    /** 是否删除 N否 Y是 */

    @Excel(name = "是否删除 N否 Y是")

    private String isDelete;


    /** 创建时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createDate;


    /** 更新时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date updateDate;


    /** 到期时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date expirationDate;


    /** 同步时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "同步时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date syncDate;


    /** 状态 N否 Y是 */

    @Excel(name = "状态 N否 Y是")

    private String syncStatus;

    /** 分润比例 */
    @Excel(name = "分润比例")
    private BigDecimal shareRate;

    public BigDecimal getShareRate() {
        return shareRate;
    }

    public void setShareRate(BigDecimal shareRate) {
        this.shareRate = shareRate;
    }

    public BigDecimal getInitUsdt() {
        return initUsdt;
    }

    public void setInitUsdt(BigDecimal initUsdt) {
        this.initUsdt = initUsdt;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserUuid(String userUuid) 
    {
        this.userUuid = userUuid;
    }

    public String getUserUuid() 
    {
        return userUuid;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setPlatformName(String platformName) 
    {
        this.platformName = platformName;
    }

    public String getPlatformName() 
    {
        return platformName;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setAppkey(String appkey) 
    {
        this.appkey = appkey;
    }

    public String getAppkey() 
    {
        return appkey;
    }
    public void setAppsecret(String appsecret) 
    {
        this.appsecret = appsecret;
    }

    public String getAppsecret() 
    {
        return appsecret;
    }
    public void setTradePassword(String tradePassword) 
    {
        this.tradePassword = tradePassword;
    }

    public String getTradePassword() 
    {
        return tradePassword;
    }
    public void setIsEnabled(String isEnabled) 
    {
        this.isEnabled = isEnabled;
    }

    public String getIsEnabled() 
    {
        return isEnabled;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }
    public void setExpirationDate(Date expirationDate) 
    {
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() 
    {
        return expirationDate;
    }
    public void setSyncDate(Date syncDate) 
    {
        this.syncDate = syncDate;
    }

    public Date getSyncDate() 
    {
        return syncDate;
    }
    public void setSyncStatus(String syncStatus) 
    {
        this.syncStatus = syncStatus;
    }

    public String getSyncStatus() 
    {
        return syncStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userUuid", getUserUuid())
            .append("phone", getPhone())
            .append("platformName", getPlatformName())
            .append("name", getName())
            .append("appkey", getAppkey())
            .append("appsecret", getAppsecret())
            .append("tradePassword", getTradePassword())
            .append("isEnabled", getIsEnabled())
            .append("isDelete", getIsDelete())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("expirationDate", getExpirationDate())
            .append("syncDate", getSyncDate())
            .append("syncStatus", getSyncStatus())
            .toString();
    }
}
