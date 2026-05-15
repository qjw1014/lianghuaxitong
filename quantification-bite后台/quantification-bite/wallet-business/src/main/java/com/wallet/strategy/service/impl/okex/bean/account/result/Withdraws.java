package com.wallet.strategy.service.impl.okex.bean.account.result;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.wallet.strategy.service.impl.okex.bean.account.result
 * @class_name
 * @auth Administrator
 * @create_time 2019/7/25 10:09
 * @company 中安链信公司
 * @comments
 * @method_name
 * @return 中安链信公司版权所有
 * 注意：本内容仅限于中安链信公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Withdraws {
    
    //数量
    private String amount;
    
    //提币手续费和对应币种，如0.00000009btc
    private String fee;
    
    //提币地址(如果收币地址是OKEx平台地址，则此处将显示用户账户)
    private String from;
    
    //收币地址
    private String to;
    
    //提币申请时间
    private String timestamp;
    
    //提现状态（-3:撤销中;-2:已撤销;-1:失败;0:等待提现;1:提现中;2:已汇出;3:邮箱确认;4:人工审核中5:等待身份认证）
    private String status;
    
    //提币哈希记录(内部转账将不返回此字段)
    private String txid;


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }
}
