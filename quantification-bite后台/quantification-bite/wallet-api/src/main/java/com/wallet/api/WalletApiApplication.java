package com.wallet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 启动程序
 * 
 * @author wallet
 */
@EnableAsync
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = {"com.wallet"})
public class WalletApiApplication
{
	
    public static void main(String[] args)
    {
        SpringApplication.run(WalletApiApplication.class, args);
         System.out.println("(♥◠‿◠)ﾉﾞ  wallet启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
