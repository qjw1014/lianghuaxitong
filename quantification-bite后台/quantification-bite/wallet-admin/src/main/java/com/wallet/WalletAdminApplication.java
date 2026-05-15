package com.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author wallet
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WalletAdminApplication
{
    public static void main(String[] args)
    {
//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(WalletAdminApplication.class, args);
        System.out.println("Admin wallet启动成功  ");
    }
}
