package com.wallet.common.enums;


    /**
     * ETH智能合约方法编码
     *
     * @author wallet
     */
    public enum EthContractSignEnum
    {
        transfer("0xa9059cbb", "转账"),
        balanceOf("0x70a08231", "查询余额"),
        decimals("0x313ce567", "查询精度"),
        allowance("0xdd62ed3e", "授权剩余配额"),
        symbol("0x95d89b41", "合约币种"),
        totalSupply("0x18160ddd", "发行总数"),
        name("0x06fdde03", "简称"),
        approve("0x095ea7b3", "授权"),
        transferFrom("0x23b872dd", "nft转账"),
        mintToken("0x79c65068","增发"),//mintToken(address target, uint256 mintedAmount)
        noOneHoldNft("0x6dbe99a9","铸造转移销毁"),//noOneHoldNft(uint256,uint256,memory,address,address)
        oneHoldNft("0x8091c8f7","单方面铸造转移修改"),// oneHoldNft(uint256,uint256,uint256,string,string,address,uint256)
        allHoldNft("0x08270d69","双方面修改"),//allHoldNft(uint256,uint256,uint256,uint256,string,string,string)
        resetTokenInfo("0x2cbdbae4","修改"),//resetTokenInfo(uint256,string,uint256)
        ;

        private final String code;
        private final String info;

        EthContractSignEnum(String code, String info)
        {
            this.code = code;
            this.info = info;
        }

        public String getCode()
        {
            return code;
        }

        public String getInfo()
        {
            return info;
        }
    }


