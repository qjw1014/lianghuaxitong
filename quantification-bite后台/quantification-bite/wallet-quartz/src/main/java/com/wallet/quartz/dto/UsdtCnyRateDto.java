package com.wallet.quartz.dto;

import com.wallet.common.utils.BigDecimalUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * okex
 */
public class UsdtCnyRateDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 兑换币种
     */
    private String  instrument_id;

    /**
     * 费率
     */
    private BigDecimal rate;
}
