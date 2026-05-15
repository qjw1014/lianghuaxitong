package com.wallet.chain.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;
import com.wallet.common.core.domain.BaseEntity;

/**
 * 链对象 w_chain
 * 
 * @author wallet
 * @date 2021-10-25
 */
@Table(name = "w_chain")
public class Chain
{
    private static final long serialVersionUID = 1L;


    /** 链编号 */

	@Id
    @Excel(name = "链编号")

    private Long id;


    /** 链名称 */

    @Excel(name = "链名称")

    private String chainName;


    /** 链节点连接 */

    @Excel(name = "链节点连接")

    private String chainNodeUrl;


    /** 同步高度 */

    @Excel(name = "同步高度")

    private Long synchronousHeight;


    /** 用户名 */

    @Excel(name = "用户名")

    private String userName;


    /** 状态Y开启N */

    @Excel(name = "状态Y开启N")

    private String status;


    /** 密码 */

    @Excel(name = "密码")

    private String password;


    /** 区块高度更新时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "区块高度更新时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date blockTime;


    /** 创建时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createTime;


    /** 图片绝对路径 */

    @Excel(name = "图片绝对路径")

    private String chainIconUrlAbs;


    /** 图片相对路径 */

    @Excel(name = "图片相对路径")

    private String chainIconUrlAbsRelative;


    /** 主币名称 */

    @Excel(name = "主币名称")

    private String mainCoinName;
    
    @Excel(name = "浏览器链接")

    private String browserLinks;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setChainName(String chainName) 
    {
        this.chainName = chainName;
    }

    public String getChainName() 
    {
        return chainName;
    }
    public void setChainNodeUrl(String chainNodeUrl) 
    {
        this.chainNodeUrl = chainNodeUrl;
    }

    public String getChainNodeUrl() 
    {
        return chainNodeUrl;
    }
    public void setSynchronousHeight(Long synchronousHeight) 
    {
        this.synchronousHeight = synchronousHeight;
    }

    public Long getSynchronousHeight() 
    {
        return synchronousHeight;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setBlockTime(Date blockTime) 
    {
        this.blockTime = blockTime;
    }

    public Date getBlockTime() 
    {
        return blockTime;
    }
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }
    public void setChainIconUrlAbs(String chainIconUrlAbs) 
    {
        this.chainIconUrlAbs = chainIconUrlAbs;
    }

    public String getChainIconUrlAbs() 
    {
        return chainIconUrlAbs;
    }
    public void setChainIconUrlAbsRelative(String chainIconUrlAbsRelative) 
    {
        this.chainIconUrlAbsRelative = chainIconUrlAbsRelative;
    }

    public String getChainIconUrlAbsRelative() 
    {
        return chainIconUrlAbsRelative;
    }
    public void setMainCoinName(String mainCoinName) 
    {
        this.mainCoinName = mainCoinName;
    }

    public String getMainCoinName() 
    {
        return mainCoinName;
    }

    
    public String getBrowserLinks() {
		return browserLinks;
	}

	public void setBrowserLinks(String browserLinks) {
		this.browserLinks = browserLinks;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("chainName", getChainName())
            .append("chainNodeUrl", getChainNodeUrl())
            .append("synchronousHeight", getSynchronousHeight())
            .append("userName", getUserName())
            .append("status", getStatus())
            .append("password", getPassword())
            .append("blockTime", getBlockTime())
            .append("createTime", getCreateTime())
            .append("chainIconUrlAbs", getChainIconUrlAbs())
            .append("chainIconUrlAbsRelative", getChainIconUrlAbsRelative())
            .append("mainCoinName", getMainCoinName())
            .append("browserLinks",getBrowserLinks())
            .toString();
    }
}
