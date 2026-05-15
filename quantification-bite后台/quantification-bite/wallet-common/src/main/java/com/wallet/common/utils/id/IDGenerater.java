package com.wallet.common.utils.id;

public class IDGenerater {

	private static Sequence sequence = null;


	
	public static Long nextId() {
		if (sequence == null) {
			sequence = new Sequence(1, 1);
		}
		return sequence.nextId();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			// System.out.println(IDGenerater.nextId());
		}
	}
}
