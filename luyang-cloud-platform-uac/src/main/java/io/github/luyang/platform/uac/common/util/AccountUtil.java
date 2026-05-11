package io.github.luyang.platform.uac.common.util;

/**
 * @author yang.lu
 */
public class AccountUtil {

	public static String generateAccount() {

		return null;
	}
	private static final int[] WEIGHTS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9};
	public static String generateEmployeeCode(int seq) {

		String empType = "E";
		String countryCode = "CN";

		// 2. 获取自增序号
		String seqStr = String.format("%05d", seq);

		// 3. 生成基础码 (不含校验位)
		String baseCode = empType + countryCode + seqStr;

		// 4. 计算校验位
		char checkDigit = calculateCheckDigit(baseCode);

		// 5. 返回完整工号
		return baseCode + checkDigit;
	}

	private static char calculateCheckDigit(String baseCode) {
		int sum = 0;

		for (int i = 0; i < baseCode.length(); i++) {
			char c = baseCode.charAt(i);
			int value = charToNumericValue(c);
			int weight = WEIGHTS[i % WEIGHTS.length];
			sum += value * weight;
		}

		int mod = sum % 10;
		int checkDigit = (10 - mod) % 10;  // 避免checkDigit为10的情况

		// 将0-9转换为字符
		return Character.forDigit(checkDigit, 10);
	}

	private static int charToNumericValue(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		} else if (c >= 'A' && c <= 'Z') {
			return (c - 'A') + 10;
		} else if (c >= 'a' && c <= 'z') {
			return (c - 'a') + 10;
		} else {
			throw new IllegalArgumentException("非法字符: " + c);
		}
	}

	public static void main(String[] args) {
		for (int i = 1; i < 100+1; i++) {
			System.out.println(generateEmployeeCode(i));
		};
	}
}
