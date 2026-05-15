package com.wallet.strategy.service;

import com.wallet.common.exception.BaseException;
import com.wallet.strategy.domain.dto.PlatformKeyInfoDto;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.wallet.strategy.service
 * @class_name 交易所统对外
 * @auth Administrator
 * @create_time 2019/7/2 17:08
 * @company 中安链信公司
 * @comments
 * @method_name
 * @return 中安链信公司版权所有
 * 注意：本内容仅限于中安链信公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface BourseService {

    /**
     * 测试连接
     * @param dto
     * @return
     */
    boolean testConnect(PlatformKeyInfoDto dto);


}
