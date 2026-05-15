package com.wallet.chain.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;




public class ChainConfigUrl {
	
	@Component
	@ConfigurationProperties(prefix = "chain.data")
	public static class Data {
		private String ethUrl;
		private String ethContractUrl;
		private String trxUrl;
		private String trxContractUrl;
		private String ethEtherscanUrl;
		private String ethEtherscanContractUrl;
		private String ethSelectContract;

		public String getMbcUrl() {
			return mbcUrl;
		}

		public void setMbcUrl(String mbcUrl) {
			this.mbcUrl = mbcUrl;
		}

		public String getMbcContractUrl() {
			return mbcContractUrl;
		}

		public void setMbcContractUrl(String mbcContractUrl) {
			this.mbcContractUrl = mbcContractUrl;
		}

		private String mbcUrl;
		private String mbcContractUrl;



		private String mbcBalanceUrl;


		public String getMbcBalanceUrl() {
			return mbcBalanceUrl;
		}

		public void setMbcBalanceUrl(String mbcBalanceUrl) {
			this.mbcBalanceUrl = mbcBalanceUrl;
		}

		public String getEthSelectContract() {
			return ethSelectContract;
		}

		public void setEthSelectContract(String ethSelectContract) {
			this.ethSelectContract = ethSelectContract;
		}

		public String getEthEtherscanContractUrl() {
			return ethEtherscanContractUrl;
		}

		public void setEthEtherscanContractUrl(String ethEtherscanContractUrl) {
			this.ethEtherscanContractUrl = ethEtherscanContractUrl;
		}




		public String getEthEtherscanUrl() {
			return ethEtherscanUrl;
		}

		public void setEthEtherscanUrl(String ethEtherscanUrl) {
			this.ethEtherscanUrl = ethEtherscanUrl;
		}

		public String getEthUrl() {
			return ethUrl;
		}

		public void setEthUrl(String ethUrl) {
			this.ethUrl = ethUrl;
		}

		public String getEthContractUrl() {
			return ethContractUrl;
		}

		public void setEthContractUrl(String ethContractUrl) {
			this.ethContractUrl = ethContractUrl;
		}

		public String getTrxUrl() {
			return trxUrl;
		}

		public void setTrxUrl(String trxUrl) {
			this.trxUrl = trxUrl;
		}

		public String getTrxContractUrl() {
			return trxContractUrl;
		}

		public void setTrxContractUrl(String trxContractUrl) {
			this.trxContractUrl = trxContractUrl;
		}

	}
	@Component
	@ConfigurationProperties(prefix = "chain.balance")
	public static class Balance {
		private String trxBalance;
		private String ethBalance;

		public String getTrxBalance() {
			return trxBalance;
		}

		public void setTrxBalance(String trxBalance) {
			this.trxBalance = trxBalance;
		}

		public String getEthBalance() {
			return ethBalance;
		}

		public void setEthBalance(String ethBalance) {
			this.ethBalance = ethBalance;
		}
	}


	@Component
	@ConfigurationProperties(prefix = "trx.node")
	public static class Address {
		private String address;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
	}


	@Component
	@ConfigurationProperties(prefix = "chain.node")
	public static class NodeUrl {
		private String chainUrl;

		private String ethChainUrl;

		private Long chainId;

		private Long ethChainId;

		public Long getChainId() {
			return chainId;
		}

		public void setChainId(Long chainId) {
			this.chainId = chainId;
		}

		public Long getEthChainId() {
			return ethChainId;
		}

		public void setEthChainId(Long ethChainId) {
			this.ethChainId = ethChainId;
		}

		public String getChainUrl() {
			return chainUrl;
		}

		public void setChainUrl(String chainUrl) {
			this.chainUrl = chainUrl;
		}

		public String getEthChainUrl() {
			return ethChainUrl;
		}

		public void setEthChainUrl(String ethChainUrl) {
			this.ethChainUrl = ethChainUrl;
		}
	}


}
