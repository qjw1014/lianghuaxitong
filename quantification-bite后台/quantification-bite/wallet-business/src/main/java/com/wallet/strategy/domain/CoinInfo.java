package com.wallet.strategy.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;
import com.wallet.common.core.domain.BaseEntity;

/**
 * 币种对象 t_coin_info
 * 
 * @author wallet
 * @date 2022-09-01
 */
@Table(name = "t_coin_info")
public class CoinInfo
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long id;


    /** 币种名称 */

    @Excel(name = "币种名称")

    private String name;


    /** 是否开启 Y是 N否 */

    @Excel(name = "是否开启 Y是 N否")

    private String isEnabled;


    /** 是否是临时币种 Y是 N否 */

    @Excel(name = "是否是临时币种 Y是 N否")

    private String isFixed;


    /** 创建时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createDate;


    /** 修改时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date updateDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setIsEnabled(String isEnabled) 
    {
        this.isEnabled = isEnabled;
    }

    public String getIsEnabled() 
    {
        return isEnabled;
    }
    public void setIsFixed(String isFixed) 
    {
        this.isFixed = isFixed;
    }

    public String getIsFixed() 
    {
        return isFixed;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("isEnabled", getIsEnabled())
            .append("isFixed", getIsFixed())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
