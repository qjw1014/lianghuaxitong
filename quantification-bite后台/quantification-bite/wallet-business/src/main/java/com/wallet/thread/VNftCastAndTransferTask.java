package com.wallet.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * VNFT铸造并转让
 */
public class VNftCastAndTransferTask implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(VNftCastAndTransferTask.class);

	private String buyAddress;


	public VNftCastAndTransferTask(String buyAddress) {
		this.buyAddress = buyAddress;

	}

	@Override
	public void run() {
		logger.info("============铸造nft并且转让线程开始成功start========");
		//方法
		logger.info("============铸造nft并且转让线程结束线程成功end==========" );
	}

}
