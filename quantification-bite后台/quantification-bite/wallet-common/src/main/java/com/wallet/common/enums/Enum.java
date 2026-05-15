package com.wallet.common.enums;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class Enum
{
    
    /***
     * 数据状态Y有效N无效
     * 
     * @author Administrator
     *
     */
    public enum rule_validate
    {
        Y
        {
            @Override
            public String getContent()
            {
                return "启用";
            }
            
            @Override
            public String getCode()
            {
                return "Y";
            }
        },
        N
        {
            @Override
            public String getContent()
            {
                return "禁用";
            }
            
            @Override
            public String getCode()
            {
                return "N";
            }
        };
        
        public abstract String getContent();
        
        public abstract String getCode();
        
        public static String exist(String code)
        {
            for (rule_validate obj : rule_validate.values())
            {
                if (obj.getCode().equals(code))
                {
                    return obj.getContent();
                }
            }
            return null;
        }
    }



    //转账合约枚举
    public enum transfer_type {
        mbc {
            @Override
            public String getContent() {
                return "MBC链合约转账";
            }

            @Override
            public String getCode() {
                return "MBC_KEY";
            }

            @Override
            public Long getChainId() {
                return 77777L;
            }
        },
        main_currency{
            @Override
            public String getContent() {
                return "MBC链主币转账";
            }

            @Override
            public String getCode() {
                return "MBC_MAIN_CURRENCY_KEY";
            }

            @Override
            public Long getChainId() {
                return 77777L;
            }
        },


        eth {
            @Override
            public String getContent() {
                return "eth链合约转账";
            }

            @Override
            public String getCode() {
                return "ETH_KEY";
            }

            @Override
            public Long getChainId() {
                return 1L;
            }
        },


        trx  {
            @Override
            public String getContent() {
                return "TRX链合约转账";
            }

            @Override
            public String getCode() {
                return "TRX_KEY";
            }

            @Override
            public Long getChainId() {
                return null;
            }
        };

        public static Map<String, transfer_type> enumMap = new HashMap<>();

        static {
            EnumSet<transfer_type> set = EnumSet.allOf(transfer_type.class);
            for (transfer_type each : set) {
                enumMap.put(each.toString(), each);
            }
        }

        public abstract String getContent();

        public abstract String getCode();

        public abstract Long getChainId();
    }


    //黑洞地址
    public enum address {
        black_hole_address  {
            @Override
            public String getContent() {
                return "黑洞地址";
            }

            @Override
            public String getCode() {
                return "0x0000000000000000000000000000000000000000";
            }
        };
        public abstract String getContent();

        public abstract String getCode();
    }

    //链名称
    public enum chain_name {
        mbc  {
            @Override
            public String getContent() {
                return "MBC主链";
            }

            @Override
            public String getCode() {
                return "MBC";
            }
        },
        trx  {
            @Override
            public String getContent() {
                return "波场";
            }

            @Override
            public String getCode() {
                return "TRX";
            }
        },
        eth  {
            @Override
            public String getContent() {
                return "以太坊";
            }

            @Override
            public String getCode() {
                return "ETH";
            }
        };


        public abstract String getContent();

        public abstract String getCode();
    }


}
