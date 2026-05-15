package com.wallet.strategy.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;
import com.wallet.common.core.domain.BaseEntity;

/**
 * 交易平台对象 t_platform
 * 
 * @author wallet
 * @date 2022-09-01
 */
@Table(name = "t_platform")
public class Platform
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long id;


    /** 平台名称 */

    @Excel(name = "平台名称")

    private String name;


    /** 是否开启 N否 Y是 */

    @Excel(name = "是否开启 N否 Y是")

    private String isEnabled;


    /** 创建时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createDate;


    /** 更新时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date updateDate;


    /** 图标 */

    @Excel(name = "图标")

    private String icon;

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
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("isEnabled", getIsEnabled())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("icon", getIcon())
            .toString();
    }
}
