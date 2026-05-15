package com.wallet.sysDict.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;
import com.wallet.common.core.domain.BaseEntity;

/**
 * 钱包配置信息对象 w_wallet_config
 * 
 * @author wallet
 * @date 2022-03-02
 */
@Table(name = "w_wallet_config")
public class WalletConfig
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long id;


    /** 唯一标识 */

    @Excel(name = "唯一标识")

    private String code;


    /** 是否是合约 Y是 N否 */

    @Excel(name = "是否是合约 Y是 N否")

    private String type;


    /** 钱包地址 */

    @Excel(name = "钱包地址")

    private String address;


    /** 合约地址 合约时必填 */

    @Excel(name = "合约地址 合约时必填")

    private String contractAddress;


    /** 秘钥 */

    @Excel(name = "秘钥")

    private String privateKey;


    /** 加密后秘钥 */

    @Excel(name = "加密后秘钥")

    private String encryKey;


    /** 描述 */

    @Excel(name = "描述")

    private String tips;


    /** 排序 */

    @Excel(name = "排序")

    private Long sort;


    /** 添加修改时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加修改时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date updateTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setContractAddress(String contractAddress) 
    {
        this.contractAddress = contractAddress;
    }

    public String getContractAddress() 
    {
        return contractAddress;
    }
    public void setPrivateKey(String privateKey) 
    {
        this.privateKey = privateKey;
    }

    public String getPrivateKey() 
    {
        return privateKey;
    }
    public void setEncryKey(String encryKey) 
    {
        this.encryKey = encryKey;
    }

    public String getEncryKey() 
    {
        return encryKey;
    }
    public void setTips(String tips) 
    {
        this.tips = tips;
    }

    public String getTips() 
    {
        return tips;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setUpdateTime(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("type", getType())
            .append("address", getAddress())
            .append("contractAddress", getContractAddress())
            .append("privateKey", getPrivateKey())
            .append("encryKey", getEncryKey())
            .append("tips", getTips())
            .append("sort", getSort())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
