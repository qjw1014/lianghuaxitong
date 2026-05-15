package com.wallet.strategy.domain.bo;


import com.wallet.strategy.domain.RevenueCurve;

import java.util.Date;


/**
 * 收益曲线对象 b_revenue_curveBO
 * 
 * @author wallet
 * @date 2023-03-11
 */

public class RevenueCurveBO extends RevenueCurve
{
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
