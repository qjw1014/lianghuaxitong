package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.UserPlatform;
import tk.mybatis.mapper.common.Mapper;
/**
 * 用户平台私钥信息Mapper接口
 * 
 * @author wallet
 * @date 2022-09-01
 */
public interface UserPlatformMapper extends Mapper<UserPlatform>
{
    
    /**
     * 批量删除用户平台私钥信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserPlatformByIds(Long[] ids);


    /**
     * 添加
     * @param userPlatform
     * @return
     */
    public int insertUserPlatform(UserPlatform userPlatform);
}
