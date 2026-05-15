
package com.wallet.common.utils;

public class Enum {

	

	public enum public_status {
		open {
			@Override
			public String getContent() {
				return "开启";
			}

			@Override
			public String getCode() {
				return "open";
			}
		},
		close {
			@Override
			public String getContent() {
				return "关闭";
			}

			@Override
			public String getCode() {
				return "close";
			}

		};

		public abstract String getContent();

		public abstract String getCode();
	}

	public enum user_status {
		normal {
			@Override
			public String getContent() {
				return "正常";
			}

			@Override
			public String getCode() {
				return "normal";
			}
		},
		frozen {
			@Override
			public String getContent() {
				return "冻结";
			}

			@Override
			public String getCode() {
				return "frozen";
			}

		};

		public abstract String getContent();

		public abstract String getCode();
	}

	public enum Y_N {
		Y {
			@Override
			public String getContent() {
				return "是";
			}

			@Override
			public String getCode() {
				return "Y";
			}
		},
		N {
			@Override
			public String getContent() {
				return "否";
			}

			@Override
			public String getCode() {
				return "N";
			}

		};

		public abstract String getContent();

		public abstract String getCode();
	}

	public enum working_status {
		first {
			@Override
			public String getContent() {
				return "第一";
			}

			@Override
			public String getCode() {
				return "first";
			}
		},
		second {
			@Override
			public String getContent() {
				return "第二";
			}

			@Override
			public String getCode() {
				return "second";
			}

		},
		third {
			@Override
			public String getContent() {
				return "第三";
			}

			@Override
			public String getCode() {
				return "third";
			}

		},
		fourth {
			@Override
			public String getContent() {
				return "第四";
			}

			@Override
			public String getCode() {
				return "fourth";
			}

		};

		public abstract String getContent();

		public abstract String getCode();
	}
	public enum out_in {

		out {
			@Override
			public String getContent() {
				return "出";
			}

			@Override
			public String getCode() {
				return "out";
			}

		},
		in {
			@Override
			public String getContent() {
				return "入";
			}

			@Override
			public String getCode() {
				return "in";
			}

		};

		public abstract String getContent();

		public abstract String getCode();

	}
	
	
	
}
