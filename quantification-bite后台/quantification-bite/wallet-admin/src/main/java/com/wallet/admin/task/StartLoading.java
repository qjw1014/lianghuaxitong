package com.wallet.admin.task;


import com.wallet.common.core.domain.entity.SysMenu;
import com.wallet.common.enums.Enum;
import com.wallet.common.redis.RedisUtil;
import com.wallet.strategy.domain.UserPlatform;
import com.wallet.strategy.service.IUserPlatformService;
import com.wallet.system.service.ISysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目启动加载数据
 */
@Component
public class StartLoading implements ApplicationRunner {
    private Logger log = LoggerFactory.getLogger(StartLoading.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private IUserPlatformService userPlatformService;


    /***
     * 默认查询加载路由
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            log.info("默认查询加载路由");
            UserPlatform platform = new UserPlatform();
            platform.setIsDelete(Enum.rule_validate.N.getCode());
            List<UserPlatform> list = userPlatformService.selectUserPlatformList(platform);
            if(list!=null&&list.size()>0){
                for(UserPlatform userPlatform:list){
                    userPlatformService.insertMenu(userPlatform);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("默认查询加载路由错误",e);
        }

    }
}
