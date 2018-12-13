package com.mibo.modules.al.sma.util;

public class DeviceTypeTAG {
	/* 24 */ private static boolean state = false;

	/* 26 */ private static String[] controlStr = { "HOSOZB", "HOSWZB01", "HOSWZB02", "HOSWZB03" };

	public static boolean isSensor(String productModel) {
		/* 35 */ String[] str = { "HODSZB", "HOSAZB", "HOGSZB", "HOMSZB" };
		/* 36 */ for (int i = 0; i < str.length; i++) {
			/* 37 */ state = productModel.contains(str[i]);
			/* 38 */ if (state) {
				/* 39 */ return state;
			}
		}
		/* 42 */ return state;
	}

	public static boolean isControl(String productModel) {
		/* 52 */ for (int i = 0; i < controlStr.length; i++) {
			/* 53 */ state = productModel.contains(controlStr[i]);
			/* 54 */ if (state) {
				/* 55 */ return state;
			}
		}
		/* 58 */ return state;
	}

	public static int controlCount(String productModel) {
		/* 68 */ int count = 0;
		/* 69 */ for (int i = 0; i < controlStr.length; i++) {
			/* 70 */ state = productModel.contains(controlStr[i]);
			/* 71 */ if (state) {
				/* 72 */ count = i;
			}
		}
		/* 75 */ if ((count == 0) || (count == 1)) {
			/* 76 */ return 0;
		}
		/* 78 */ return count - 1;
	}
}
