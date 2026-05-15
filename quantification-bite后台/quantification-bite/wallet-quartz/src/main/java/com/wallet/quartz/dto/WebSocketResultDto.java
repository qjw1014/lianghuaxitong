package com.wallet.quartz.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.zhognan.datacenter.dto
 * @class_name
 * @auth Administrator
 * @create_time 2019/4/22 17:13
 * @company 中安链信公司
 * @comments
 * @method_name
 * @return 中安链信公司版权所有
 * 注意：本内容仅限于中安链信公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class WebSocketResultDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String type;
    
    private Object value;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
