package com.wallet.common.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity基类 在pojo中对特定的date类型属性加了以下配置
 * 
 * @DateTimeFormat来控制入参，@JsonFormat来控制出参 jsonfield来源于fastjson，是阿里巴巴的开源框架，主要进行JSON解析和序列化。 jsonformat来源于jackson，Jackson是一个简单基于Java应用库
 * @JSONField(format="HH:mm:ss") @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
 * 
 */
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /** 创建者 */
    private String createBy;
    
    /** 创建时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    /** 更新者 */
    private String updateBy;
    
    /** 更新时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    /** 备注 */
    private String remark;
    
    /** 请求参数 */
    private Map<String, Object> params;
    
    /** 开始时间 */
    @JSONField(serialize = false)
//    @JsonIgnore
    private String beginTime;
    
    /** 结束时间 */
    @JSONField(serialize = false)
//    @JsonIgnore
    private String endTime;
    
    public String getBeginTime()
    {
        return beginTime;
    }
    
    public void setBeginTime(String beginTime)
    {
        this.beginTime = beginTime;
    }
    
    public String getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
    
    public String getCreateBy()
    {
        return createBy;
    }
    
    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public String getUpdateBy()
    {
        return updateBy;
    }
    
    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }
    
    public Date getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public String getRemark()
    {
        return remark;
    }
    
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }
    
    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}
