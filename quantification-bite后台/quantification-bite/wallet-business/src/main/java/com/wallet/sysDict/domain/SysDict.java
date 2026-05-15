package com.wallet.sysDict.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;

/**
 * 参数配置对象 w_sys_dict
 * 
 * @author wallet
 * @date 2022-01-06
 */
@Table(name = "w_sys_dict")
public class SysDict
{
    private static final long serialVersionUID = 1L;


    /** 编号 */

	@Id
    @Excel(name = "编号")

    private Long id;


    /** 排序 */

    @Excel(name = "排序")

    private Long num;


    /** 上级编号 */

    @Excel(name = "上级编号")

    private Long pid;


    /** 值 */

    @Excel(name = "值")

    private String name;


    /** 提示 */

    @Excel(name = "提示")

    private String tips;


    /** 标识 */

    @Excel(name = "标识")

    private String code;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTips(String tips) 
    {
        this.tips = tips;
    }

    public String getTips() 
    {
        return tips;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("pid", getPid())
            .append("name", getName())
            .append("tips", getTips())
            .append("code", getCode())
            .toString();
    }
}
