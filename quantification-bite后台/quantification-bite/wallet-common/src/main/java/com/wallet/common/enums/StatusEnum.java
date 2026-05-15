package com.wallet.common.enums;

/**
 * 状态：1.是，2.否
 * 
 * @author wallet
 */
public enum StatusEnum
{
    TRUE(1, "是"), FALSE(2, "否");

    private final Integer code;
    private final String info;

    StatusEnum(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
