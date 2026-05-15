package com.wallet.common.core.domain;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ErrMessage {
	public static final Integer CODE_SUCCESS = 200;
	public static final Integer CODE_FAIL = 201;
	public static final Integer CODE_PARAMETER_IS_EMPTY = 202;
	public static final Integer CODE_PARAMETER_IS_ERROR = 202;



	public static final Integer CODE_SYS_UNKNOWN = 500;

	public static final Integer CODE_SYS_CONFIG_IS_NULL= 501;

	// 用户错误码

	public static final Integer CODE_TICKET_ERROR = 601;
	public static final Integer CODE_EMAIL_OR_PHONE_ERROR = 602;



	// 钱包错误码
	public static final Integer CODE_RATE_NOT_CONFIGURED = 301;
	public static final Integer CODE_WRONG_ADDRESS = 302;
	public static final Integer CODE_CHAIN_NUMBER_ERROR = 303;
	public static final Integer NFT_POJECT_NON_EXISTENT = 304;
	public static final Integer APPID_ERROR = 305;
	public static final Integer BALANCE_ERROR = 306;
	public static final Integer MAIL_TYPE_ERROR = 307;
	public static final Integer CODE_ADDRESS = 308;
	public static final Integer ADDRESS_INFORMATION_IS_NOT_SYNCHRONIZEDADDRESS_OR_DOED_NOT_EXIST = 309;


	public static final Integer INFO_IS_NULL = 700;//信息不存在
	public static final Integer COIN_IS_NULL = 701;//币种配置为null
    public static final Integer PLEDGE_ASSETS_INSUFFICIENT = 702;//质押资金不足
	public static final Integer CAST_PROJECT_IS_NULL = 703;//铸造商品不存在
	public static final Integer INSUFFICIENT_DESTROY_PERMISSION = 704;//销毁权限不足
	public static final Integer CAST_ADDRESS_NOT_EXIST = 705;//铸造地址没有配置
	public static final Integer NOT_INCLUDED = 706;//余额未收录
	public static final Integer NFT_PROJECT_NOT_EXIST = 707;//铸造项目不存在
	public static final Integer PLEDGE_HASH_EXIST = 708;//交易hash已消费
	public static final Integer COMMODITY_DESTROYED = 709;//商品已销毁
	public static final Integer CAST_FAILURE = 710;//铸造失败
	public static final Integer INSUFFICIENT_TRANSFER_AUTHORITY = 711;//转让权限不足
	public static final Integer TRADING_PAIRS_DO_NOT_EXIST = 712;//交易对不存在
	public static final Integer RELEASE_BALANCE_IS_NULL = 713;//释放资金为0
	public static final Integer FREQUENT_OPERATION = 714;//操作频繁
	public static final Integer ADDRESS_ALREADY_EXISTS = 715;//操作频繁
	public static final Integer NOT_CASTING = 716;//不是铸造商
	public static final Integer PRICE_NOT_SATISFIED = 717;//价格不满足条件
	public static final Integer CASTIMAGE_CANNOT_BE_EMPTY = 718;//铸造图片不能为空
	public static final Integer CANNOT_BE_REPEATED = 719;//不可以重复上架
	public static final Integer PRICE_ERROE = 720;//不可以重复上架
	public static final Integer GOODS_NO_EXIST = 721;//商品不存在
	public static final Integer GOODS_BY_SELL = 722;//商品已下架、商品已出售
	public static final Integer ORDER_IS_NULL = 723;//订单已失效
	public static final Integer BOTH_AVATAR_AND_NAME_ARE_EMPTY = 724;//头像和昵称都为空
	public static final Integer NO_OTHER_CHAINS_HAVE_BEEN_OPENED = 725;//暂未开通其他链
	public static final Integer DELISTING_IS_NOT_ALLOWED_IN_COMMODITY_TRADING = 726;//商品交易中不允许下架

	public static final Integer MARKET_CUSTOM_ORDER_CLOSE=727;//订单已关闭
	public static final Integer MARKET_CUSTOM_ORDER_SOLD=728;//订单已出售
	public static final Integer MARKET_CUSTOM_ORDER_CANNOT_BE_PURCHASED=729;//自己的订单不能购买
	public static final Integer MARKET_TRANSFER_CONFORMITY=730;//转让价值不符合
	public static final Integer INSUFFICIENT_PERMISSIONS = 731;//权限不足
	public static final Integer ORDER_COMPLETED_OR_CLOSED = 732;//订单已完成或已关闭

	public static final Integer FOUNDRY_CANNOT_WITHDRAW = 733;//铸造商不能提现
	public static final Integer MAXIMUM_NUMBER_OF_VIOLATIONS = 734;//违规次数上限,今天内不能接单
	public static final Integer TOKEN_IN_TRANSACTION = 735;//nft提现中不能进行交易
	public static final Integer CANNOT_CLOSE_WHILE_PROCESSING = 736;//nft转让商家途中不能进行关闭
	public static final Integer INSUFFICIENT_SHELF_CONDITIONS = 737;//商品上架条件不足
	public static final Integer sell_address_error = 738;//商家地址有误
	public static final Integer NOT_A_FOUNDRY = 739;//不是铸造商
	public static final Integer EXCHANGE_FAILED = 740;//商家地址有误
	public static final Integer NOT_MINE_MACHINE = 741;//不是矿机产品信息
	public static final Integer OPEN_AND_READY= 742;//:正在开放准备中...

	public static final Integer INSUFFICIENT_BALANCE= 743;//余额不足
	public static final Integer WRONG_QUOTA_SETTING= 744;//最小，最大交易额度设置错误
	public static final Integer DESTROY_ERROR= 745;//销毁出错
	public static final Integer HASH_MISMATCH= 746;//哈希值不匹配



	public static final Integer EXCESSIVE_ORDER_QUANTITY= 747;//下单数量超额
	public static final Integer WRONG_FEE= 748;//手续费有误

	public static final Integer ERROR_IN_COUNTER_REPAYMENT_OF_HANDLING_CHARGES= 749;//反还手续费出错

	public static final Integer BUY_AND_SELL_TYPES_DO_NOT_MATCH= 750;//买卖类型不匹配

	public static final Integer HASH_CANNOT_BE_EMPTY= 751;//哈希不能为空

	public static final Integer THE_USER_HAS_NO_BALANCE_IN_THE_REGION= 752;//该用户在地区没有余额

	public static final Integer ORDER_DOES_NOT_EXIST= 753;//订单找不到

	public static final Integer ORDINARY_USERS_CANNOT_PUBLISH_ORDERS= 754;//普通用户不允许发布订单

	public static final Integer HASH_MISMATCH_ALREADY_REFUND= 755;//哈希值不匹配已经退款

	public static final Integer EXISTING_ORDERS_FOR_SALE= 756;//现有待售订单

	public static final Integer ASSET_TRANSFER_FAILED= 757;//现有待售订单

	public static final Integer THE_ORDER_IS_BEING_PUT_INTO_THE_SYSTEM= 758;//订单上架系统处理中


	public static final Integer THE_PURCHASE_AMOUNT_IS_TOO_SMALL= 759;//购买金额太小

	public static final Integer PURCHASE_AMOUNT_EXCEEDS_THE_MAXIMUM= 760;//购买金额超过最大值

	public static final Integer USERS_DO_NOT_HAVE_PERMISSION_TO_PLACE_ORDERS_IN_THIS_AREA= 761;//用户在该地区没有权限发布订单


	//1000开始是vnft标识
	public static final Integer VNFT_ROLE_CODE_IS_NULL= 1001;//角色标识异常
	public static final Integer VNFT_ROLE_EXIST= 1002;//用户已是该角色
	public static final Integer VNFT_ROLE_UPGRADE_EXIST= 1003;//角色申请已存在
	public static final Integer VNFT_TRANSFER_ACCOUNT_ERROT= 1004;//转账账户错误
	public static final Integer VNFT_NOT_PLEDGE_AUTH= 1005;//没有质押权限
	public static final Integer VNFT_NOT_TRANSFER_AUTH= 1006;//该地址角色没有划转权限
	public static final Integer VNFT_INSUFFICIENT_CASTING_QUOTA= 1007;//铸造额度不足
	public static final Integer VNFT_INSUFFICIENT_CAST= 1008;//创建地址铸造权限不足（是自由存管人）
	public static final Integer VNFT_INSUFFICIENT_WITHDRAWAL= 1009;//申请交割地址权限不足
	public static final Integer VNFT_WITHDRAWAL_STATUS_CHANGE= 1010;//提现申请状态改变，请刷新
	public static final Integer VNFT_WITHDRAWAL_TOKEN_NOT= 1011;//提现VNFT不符合
	public static final Integer VNFT_WITHDRAWAL_INSUFFICIENT_AMOUNT= 1012;//提现NFT不符合
	public static final Integer VNFT_ROLE_NOT_UPDATE= 1013;//该角色不能修改地址
	public static final Integer VNFT_INCORRECT_TRANSFER_ADDRESS= 1014;//转出地址不正确，请填写有效地址
	public static final Integer VNFT_INCORRECT_TRANSFER_IN_ADDRESS= 1015;//转入地址不正确，请填写有效地址
	public static final Integer VNFT_SUCCESS_WAITING_CONTRACT= 1016;//转入地址不正确，请填写有效地址
	public static final Integer VNFT_SUCCESS_WAITING_FOR_EXECUTION= 1017;//操作成功，等待合约执行
	public static final Integer VNFT_ORDER_TO_BE_DETERMINED= 1018;//订单在等待上链确认，无法取消
	public static final Integer VNFT_NOT_TENASFER_ME= 1019;//不能转账给自己




	public static final Map<Integer, String> MESSAGES;
	static {
		Map<Integer, String> workingMessage = new HashMap<Integer, String>();
		workingMessage.put(CODE_SUCCESS, "Successful operation");
		workingMessage.put(CODE_FAIL, "Request failed");
		workingMessage.put(CODE_SYS_UNKNOWN, "系统错误");
		workingMessage.put(CODE_SYS_CONFIG_IS_NULL, "系统配置信息为空");
		workingMessage.put(CODE_PARAMETER_IS_EMPTY, "请求参数不可为空");
		workingMessage.put(CODE_PARAMETER_IS_ERROR, "请求参数异常");

        workingMessage.put(CODE_WRONG_ADDRESS, "Wrong wallet address");
        workingMessage.put(CODE_CHAIN_NUMBER_ERROR, "Wrong chain name");
        workingMessage.put(NFT_POJECT_NON_EXISTENT, "nft project non-existent");
		workingMessage.put(APPID_ERROR, "appid error");
		workingMessage.put(BALANCE_ERROR, "balance 0");
		workingMessage.put(MAIL_TYPE_ERROR, "Wrong message notification type");
		workingMessage.put(CODE_ADDRESS, "Wrong  address");
		workingMessage.put(ADDRESS_INFORMATION_IS_NOT_SYNCHRONIZEDADDRESS_OR_DOED_NOT_EXIST, "Address information is not synchronized or does not exist");

		workingMessage.put(INFO_IS_NULL,"Information does not exist");
		workingMessage.put(COIN_IS_NULL, "Currency configuration is blank");
        workingMessage.put(PLEDGE_ASSETS_INSUFFICIENT, "Insufficient pledged funds");
		workingMessage.put(CAST_PROJECT_IS_NULL, "Casting item does not exist");
		workingMessage.put(INSUFFICIENT_DESTROY_PERMISSION, "Insufficient destroy permission");
		workingMessage.put(CAST_ADDRESS_NOT_EXIST, "Casting address does not exist");
		workingMessage.put(NOT_INCLUDED, "Not included");
		workingMessage.put(COMMODITY_DESTROYED, "Commodity destroyed");
		workingMessage.put(INSUFFICIENT_TRANSFER_AUTHORITY, "Insufficient transfer authority");
		workingMessage.put(TRADING_PAIRS_DO_NOT_EXIST, "Trading pairs do not exist");
		workingMessage.put(RELEASE_BALANCE_IS_NULL, "The fund balance is 0");

		workingMessage.put(NFT_PROJECT_NOT_EXIST, "Casting NFT item does not exist");
		workingMessage.put(PLEDGE_HASH_EXIST, "Transaction already exists");
		workingMessage.put(FREQUENT_OPERATION, "Frequent operation");

		workingMessage.put(ADDRESS_ALREADY_EXISTS, "Address already exists");
		workingMessage.put(NOT_CASTING, "Not Casting");
		workingMessage.put(PRICE_NOT_SATISFIED, "Not satisfied price");
		workingMessage.put(CASTIMAGE_CANNOT_BE_EMPTY, "castImage cannot be empty");
		workingMessage.put(CANNOT_BE_REPEATED, "Cannot be repeated");
		workingMessage.put(PRICE_ERROE, "PRICE_ERROE");
		workingMessage.put(GOODS_NO_EXIST,"goods does not exist");
		workingMessage.put(GOODS_BY_SELL,"The item is off the shelf / for sale");
		workingMessage.put(ORDER_IS_NULL,"The order has expired");
		workingMessage.put(BOTH_AVATAR_AND_NAME_ARE_EMPTY,"Both avatar and name are empty");
		workingMessage.put(NO_OTHER_CHAINS_HAVE_BEEN_OPENED,"No other chains have been opened");
		workingMessage.put(DELISTING_IS_NOT_ALLOWED_IN_COMMODITY_TRADING,"Delisting is not allowed in commodity trading");
		workingMessage.put(NOT_A_FOUNDRY,"Not a foundry");
		workingMessage.put(TOKEN_IN_TRANSACTION,"nft withdrawalin transaction ");
		workingMessage.put(INSUFFICIENT_SHELF_CONDITIONS,"Insufficient shelf conditions");


		workingMessage.put(MARKET_CUSTOM_ORDER_CLOSE, "order closed");
		workingMessage.put(MARKET_CUSTOM_ORDER_SOLD, "order sold");
		workingMessage.put(MARKET_CUSTOM_ORDER_CANNOT_BE_PURCHASED, "Your order cannot be purchased");
		workingMessage.put(MARKET_TRANSFER_CONFORMITY, "Transfer value does not meet");
		workingMessage.put(INSUFFICIENT_PERMISSIONS, "Insufficient permissions");
		workingMessage.put(ORDER_COMPLETED_OR_CLOSED, "Order completed or closed");
		workingMessage.put(FOUNDRY_CANNOT_WITHDRAW, "Foundry cannot withdraw");
		workingMessage.put(MAXIMUM_NUMBER_OF_VIOLATIONS, "The maximum number of violations. You can't take orders today");
		workingMessage.put(CANNOT_CLOSE_WHILE_PROCESSING, "Cannot close while processing");
		workingMessage.put(sell_address_error, "Sell address error");
		workingMessage.put(EXCHANGE_FAILED, "Exchange failed");
		workingMessage.put(NOT_MINE_MACHINE, "Not mine product information");
		workingMessage.put(OPEN_AND_READY, "Open and ready...");
		workingMessage.put(INSUFFICIENT_BALANCE, "Insufficient balance");
		workingMessage.put(WRONG_QUOTA_SETTING, "The minimum and maximum transaction amount is set incorrectly");
		workingMessage.put(DESTROY_ERROR, "Destroy error");
		workingMessage.put(HASH_MISMATCH, "Hash value mismatch");
		workingMessage.put(EXCESSIVE_ORDER_QUANTITY, "Excessive order quantity");
		workingMessage.put(WRONG_FEE, "wrong fee");
		workingMessage.put(ERROR_IN_COUNTER_REPAYMENT_OF_HANDLING_CHARGES, "Error in counter repayment of handling charges");


		//VNFT模块
		workingMessage.put(VNFT_ROLE_CODE_IS_NULL, "Abnormal role");
		workingMessage.put(VNFT_ROLE_UPGRADE_EXIST, "Role application already exists");
		workingMessage.put(VNFT_TRANSFER_ACCOUNT_ERROT, "Transfer account error");
		workingMessage.put(VNFT_NOT_PLEDGE_AUTH, "No pledge authority");
		workingMessage.put(VNFT_NOT_TRANSFER_AUTH, "No transfer authority");
		workingMessage.put(VNFT_INSUFFICIENT_CASTING_QUOTA, "Insufficient casting quota");
		workingMessage.put(VNFT_INSUFFICIENT_CAST, "Incorrect casting address");
		workingMessage.put(BUY_AND_SELL_TYPES_DO_NOT_MATCH, "Buy and sell types do not match");
		workingMessage.put(ORDINARY_USERS_CANNOT_PUBLISH_ORDERS, "Ordinary users cannot publish orders");
		workingMessage.put(HASH_CANNOT_BE_EMPTY, "Cannot be empty and hash");
		workingMessage.put(VNFT_WITHDRAWAL_TOKEN_NOT, "Withdrawal VNFT belongs to this region");
		workingMessage.put(VNFT_WITHDRAWAL_INSUFFICIENT_AMOUNT, "Insufficient amount of withdrawal VNFT");
		workingMessage.put(VNFT_INSUFFICIENT_WITHDRAWAL, "Delivery address is incorrect");
		workingMessage.put(THE_USER_HAS_NO_BALANCE_IN_THE_REGION, "The user has no balance in the region");
		workingMessage.put(ORDER_DOES_NOT_EXIST, "Order does not exist");
		workingMessage.put(HASH_MISMATCH_ALREADY_REFUND, "Hash mismatch already refund");
		workingMessage.put(EXISTING_ORDERS_FOR_SALE, "Existing orders for sale");
		workingMessage.put(ASSET_TRANSFER_FAILED, "Asset transfer failed");
		workingMessage.put(VNFT_ROLE_NOT_UPDATE, "This role cannot modify region");
		workingMessage.put(THE_ORDER_IS_BEING_PUT_INTO_THE_SYSTEM, "The order is being put into the system");
		workingMessage.put(VNFT_WITHDRAWAL_STATUS_CHANGE, "Order status has changed, please refresh");
		workingMessage.put(VNFT_INCORRECT_TRANSFER_ADDRESS, "The transfer out address is incorrect. Please fill in the valid address");
		workingMessage.put(VNFT_INCORRECT_TRANSFER_IN_ADDRESS, "The transfer in address is uncertain. Please enter a valid address");
		workingMessage.put(VNFT_SUCCESS_WAITING_CONTRACT, "Successful execution, waiting for contract execution");
		workingMessage.put(THE_PURCHASE_AMOUNT_IS_TOO_SMALL, "The purchase amount is too small");
		workingMessage.put(PURCHASE_AMOUNT_EXCEEDS_THE_MAXIMUM, "Purchase amount exceeds the maximum");
		workingMessage.put(VNFT_SUCCESS_WAITING_FOR_EXECUTION, "The operation is successful, waiting for the contract to execute");
		workingMessage.put(VNFT_ORDER_TO_BE_DETERMINED, "The order is waiting for confirmation on the chain and cannot be cancelled");
		workingMessage.put(USERS_DO_NOT_HAVE_PERMISSION_TO_PLACE_ORDERS_IN_THIS_AREA, "Users do not have permission to place orders in this area");
		workingMessage.put(VNFT_NOT_TENASFER_ME, "Can't transfer it to yourself");
		MESSAGES = Collections.unmodifiableMap(workingMessage);
	}

	public static String get(String code, String... tokens) {
		String message = MESSAGES.get(code);
		if (message == null) {
			return code;
		} else {
			return MessageFormat.format(message, (Object[]) tokens);
		}
	}

	public static Object[] getAsArray(Integer code) {
		String message = MESSAGES.get(code);
		if (message == null) {
			return new Object[] { code, null };
		}
		return new Object[] { code, message };
	}

	public static String get(Integer code){
		String message = MESSAGES.get(code);
		return message;
	}
}
