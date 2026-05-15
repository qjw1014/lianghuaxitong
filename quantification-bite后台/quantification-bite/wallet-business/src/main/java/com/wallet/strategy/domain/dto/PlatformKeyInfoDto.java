package com.wallet.strategy.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 连接信息
 */
@ApiModel
public class PlatformKeyInfoDto implements Serializable {
    
    @ApiModelProperty(value = "平台",required = true)
    private String platform;
    
    @ApiModelProperty(value = "appId",required = true)
    private String appId;
    
    @ApiModelProperty(value = "secret",required = true)
    private String secret;
    
    @ApiModelProperty(value = "密码")
    private String password;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
    
}
