package com.wallet.common.core.domain.model;

/**
 * 用户登录对象
 * 
 * @author wallet
 */
public class CheckGoogleDto
{
    /**
     * 用户名
     */
    private Long id;

    /**
     * 验证码
     */
    private String googleCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoogleCode() {
        return googleCode;
    }

    public void setGoogleCode(String googleCode) {
        this.googleCode = googleCode;
    }
}
