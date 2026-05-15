package com.wallet.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 * 
 * @author wallet
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = {"com.wallet"})
public class WalletQuartzApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(WalletQuartzApplication.class, args);
        // System.out.println("(♥◠‿◠)ﾉﾞ  wallet启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
