package com.wallet.strategy.service.impl.okex.bean.swap.param;

import java.util.List;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.wallet.strategy.service.impl.okex.bean.swap.param
 * @class_name
 * @auth Administrator
 * @create_time 2020\1\2 0002 16:36
 * @company 中安链信公司
 * @comments
 * @method_name
 * @return 中安链信公司版权所有
 * 注意：本内容仅限于中安链信公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class PpOrderInfo {
    private List<PpOrderDetailAll> order_info;

    public List<PpOrderDetailAll> getOrder_info() {
        return order_info;
    }

    public void setOrder_info(List<PpOrderDetailAll> order_info) {
        this.order_info = order_info;
    }
}
