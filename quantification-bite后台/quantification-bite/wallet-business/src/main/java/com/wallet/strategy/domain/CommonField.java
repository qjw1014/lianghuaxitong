package com.wallet.strategy.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;
import com.wallet.common.core.domain.BaseEntity;

/**
 * 策略公共字段描述对象 b_common_field
 * 
 * @author wallet
 * @date 2023-03-14
 */
@Table(name = "b_common_field")
public class CommonField
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long id;


    /** 字段名称 */

    @Excel(name = "字段名称")

    private String name;


    /** 显示名称 */

    @Excel(name = "显示名称")

    private String showName;


    /** 备注 */

    @Excel(name = "备注")

    private String remarks;


    /** 是否开启 */

    @Excel(name = "是否开启")

    private String isEnabled;


    /** 添加时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createTime;

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
    public void setShowName(String showName) 
    {
        this.showName = showName;
    }

    public String getShowName() 
    {
        return showName;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setIsEnabled(String isEnabled) 
    {
        this.isEnabled = isEnabled;
    }

    public String getIsEnabled() 
    {
        return isEnabled;
    }
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("showName", getShowName())
            .append("remarks", getRemarks())
            .append("isEnabled", getIsEnabled())
            .append("createTime", getCreateTime())
            .toString();
    }
}
