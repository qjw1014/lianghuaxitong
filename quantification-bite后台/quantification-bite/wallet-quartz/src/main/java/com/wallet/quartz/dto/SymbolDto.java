package com.wallet.quartz.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *  行情数据
 * @package_name com.zhognan.datacenter.dto
 * @class_name
 * @auth Administrator
 * @create_time 2019/4/17 10:06
 * @comments
 * @method_name
 */
public class SymbolDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 排序
     */
    private Integer sort;


    /**
     * 图标
     * @return
     */
    private String icon;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
