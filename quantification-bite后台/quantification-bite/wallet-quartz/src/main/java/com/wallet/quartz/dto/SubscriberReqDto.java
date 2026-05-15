package com.wallet.quartz.dto;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.zhognan.datacenter.dto
 * @class_name
 * @auth Administrator
 * @create_time 2019/4/18 18:19
 * @company 中安链信公司
 * @comments
 * @method_name
 * @return 中安链信公司版权所有
 * 注意：本内容仅限于中安链信公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class SubscriberReqDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 类型 add_ticker，add_depath，del_ticker，del_depath,add_funds,del_funds,add_martin
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
