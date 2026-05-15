package com.wallet.eth.domain;

import jnr.ffi.annotations.In;

import java.math.BigDecimal;

/**
 * 共用合约（修改-铸造-转让-销毁-修改）VNFT toeknId方法类
 */
public class HoldNftDTO {

    /**
     * 最高权限地址
     */
    private String castAddress;

    /**
     * 秘钥
     */
    private String privateKey;

    /**
     * 链编号
     */
    private Long chainId;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 卖出方tokenId
     */
    private String sellTokenId;

    /**
     * 买入方tokenId
     */
    private String buyTokenId;

    /**
     * 新token NFT价值
     */
    private BigDecimal exPrice;

    /**
     * 新token
     */
    private String exTokenID;

    /**
     * 新token url
     */
    private String extokenURI;

    /**
     * 卖家更新后的价值url
     */
    private String selltokenURI;

    /**
     * 买家更新后的价值url
     */
    private String buytokenURI;

    /**
     * 类型 1.卖出方减小  2.收入增加
     */
    private Integer type;

    /**
     * 转出地址
     */
    private String fromAddress;

    /**
     * 转入地址
     */
    private String toAddress;

    /**
     * 精度（一般18位数）
     */
    private Integer unit;

    /**
     * 合约地址
     */
    private String contractAddress;

    public HoldNftDTO(){

    }

    /**
     * 修改买(卖)方的
     * @param method 方法名
     * @param buyTokenId 买（卖）方tokenId
     * @param exPrice 新铸造价值
     * @param exTokenID 新铸造tokenId
     * @param extokenURI 新铸造图片路径
     * @param buytokenURI 买（卖）方修改后URL
     * @param fromAddress 转让地址
     * @param castAddress 最高权限地址
     * @param privateKey 私钥
     * @param contractAddress 合约地址
     * @param chainId 链ID
     * @param unit 主币位数
     * @param type 类型 1.买方修改 2卖方修改
     */
    public HoldNftDTO(String method,String buyTokenId,BigDecimal exPrice,String exTokenID,String extokenURI,
                      String buytokenURI,String fromAddress,String castAddress,String privateKey,String contractAddress,
                      Long chainId,int unit,Integer type){
        if(type==1){
            this.buyTokenId=buyTokenId;
            this.buytokenURI=buytokenURI;
        }else{
            this.sellTokenId=buyTokenId;
            this.selltokenURI=buytokenURI;
        }
        this.method=method;
        this.exPrice=exPrice;
        this.exTokenID=exTokenID;
        this.extokenURI=extokenURI;
        this.fromAddress=fromAddress;
        this.castAddress=castAddress;
        this.privateKey=privateKey;
        this.contractAddress=contractAddress;
        this.chainId=chainId;
        this.unit=unit;
        this.type=type;
    }

    /**
     * 铸造转移销毁
     * @param method 方法名称
     * @param exPrice 新铸造价值
     * @param exTokenID 新铸造tokenId
     * @param extokenURI 新铸造图片路径
     * @param fromAddress 转让地址
     * @param toAddress 转入地址
     * @param castAddress  最高权限地址
     * @param privateKey 私钥
     * @param contractAddress 合约地址
     * @param chainId 链ID
     * @param unit 主币位数
     */
    public HoldNftDTO(String method,BigDecimal exPrice,String exTokenID,String extokenURI
                      ,String fromAddress,String toAddress,String castAddress,
                      String privateKey,String contractAddress,
                      Long chainId,int unit){
        this.method=method;
        this.exPrice=exPrice;
        this.exTokenID=exTokenID;
        this.extokenURI=extokenURI;
        this.fromAddress=fromAddress;
        this.toAddress=toAddress;
        this.castAddress=castAddress;
        this.privateKey=privateKey;
        this.contractAddress=contractAddress;
        this.chainId=chainId;
        this.unit=unit;
    }


    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Long getChainId() {
        return chainId;
    }

    public void setChainId(Long chainId) {
        this.chainId = chainId;
    }

    public String getCastAddress() {
        return castAddress;
    }

    public void setCastAddress(String castAddress) {
        this.castAddress = castAddress;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSellTokenId() {
        return sellTokenId;
    }

    public void setSellTokenId(String sellTokenId) {
        this.sellTokenId = sellTokenId;
    }

    public String getBuyTokenId() {
        return buyTokenId;
    }

    public void setBuyTokenId(String buyTokenId) {
        this.buyTokenId = buyTokenId;
    }

    public BigDecimal getExPrice() {
        return exPrice;
    }

    public void setExPrice(BigDecimal exPrice) {
        this.exPrice = exPrice;
    }

    public String getExTokenID() {
        return exTokenID;
    }

    public void setExTokenID(String exTokenID) {
        this.exTokenID = exTokenID;
    }

    public String getExtokenURI() {
        return extokenURI;
    }

    public void setExtokenURI(String extokenURI) {
        this.extokenURI = extokenURI;
    }

    public String getSelltokenURI() {
        return selltokenURI;
    }

    public void setSelltokenURI(String selltokenURI) {
        this.selltokenURI = selltokenURI;
    }

    public String getBuytokenURI() {
        return buytokenURI;
    }

    public void setBuytokenURI(String buytokenURI) {
        this.buytokenURI = buytokenURI;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }
}
