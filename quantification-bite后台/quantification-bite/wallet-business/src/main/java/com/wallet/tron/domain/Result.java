package com.wallet.tron.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map<String, Object> result;
	public List<Object> constant_result;


	/**
	 * 获取结果
	 *
	 * @param index
	 * @param <T>
	 * @return
	 */
	public <T> T getConstantResult(int index) {
		if (constant_result == null || constant_result.size() <= index) {
			return null;
		}
		return (T) constant_result.get(index);
	}


	public boolean isSuccess() {
		if (result != null) {
			Object obj = this.result.get("result");
			if (obj.toString().contains("true")) {
				return true;
			}
		}
		return false;
	}
	
	
	public static class Transaction implements Serializable {
		private String txID;
		private Map<String, Object> raw_data;
		private String raw_data_hex;
		private boolean visible;
		private String[] signature;
	}

	
	public static class RawData implements Serializable {
		private List<Contract> contract;
		private String ref_block_bytes;
		private String ref_block_hash;
		private long expiration;
		private long timestamp;
		private long fee_limit;
	}

	
	public static class Contract implements Serializable {
		private Map<String, Object> parameter;
		private String type;
	}
}
