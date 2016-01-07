package com.dpc.utils;

import java.util.Random;

public class RandomNumberGenerator {

	/**
	 * 
	 * @description 随机生成scale位的数字
	 * @date 2016年1月5日 下午4:39:16
	 * @param scale
	 * @return
	 */
	public static String generateNumber(Integer scale) {
		String no = "";
		int num[] = new int[scale];
		int c = 0;
		for (int i = 0; i < scale; i++) {
			num[i] = new Random().nextInt(10);
			c = num[i];
			for (int j = 0; j < i; j++) {
				if (num[j] == c) {
					i--;
					break;
				}
			}
		}
		if (num.length > 0) {
			for (int i = 0; i < num.length; i++) {
				no += num[i];
			}
		}
		return no;
	}
}
