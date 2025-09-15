package io.github.luyang.platform.open.util;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.open.beans.enums.TokenStyle;
import lombok.experimental.UtilityClass;

/**
 * @author yang.lu
 */
@UtilityClass
public class TokenUtil {

	/** 令牌后缀 */
	private static final String SUFFIX = "__";
	/** 访问令牌总长度 */
	private static final int ACCESS_TOKEN_LENGTH = 32;
	/** 刷新令牌总长度 */
	private static final int REFRESH_TOKEN_LENGTH = 64;


	/**
	 * 生成令牌
	 *
	 * @param tokenStyle 令牌风格枚举
	 * @return String
	 * @author yang.lu
	 */
	public static String generateToken(TokenStyle tokenStyle) {
		String prefix;
		int totalLength = switch (tokenStyle) {
			case ACCESS_TOKEN -> {
				prefix = "AT_";
				yield ACCESS_TOKEN_LENGTH;
			}
			case REFRESH_TOKEN -> {
				prefix = "RT_";
				yield REFRESH_TOKEN_LENGTH;
			}
			case CLIENT_TOKEN -> {
				prefix = "CT_";
				yield ACCESS_TOKEN_LENGTH;
			}
			default -> throw new IllegalArgumentException("不支持的令牌类型: " + tokenStyle.name());
		};

		int remainingLength = totalLength
			- prefix.length()
			- SUFFIX.length()
			- 2 * StrPool.UNDERLINE.length();

		int segmentLength = remainingLength / 3;
		int lastSegmentLength = remainingLength - 2 * segmentLength;

		return prefix
			+ IdUtil.nanoId(segmentLength)
			+ StrPool.UNDERLINE
			+ IdUtil.nanoId(segmentLength)
			+ StrPool.UNDERLINE
			+ IdUtil.nanoId(lastSegmentLength)
			+ SUFFIX;
	}

	public static void main(String[] args) {
		System.out.println("AccessToken: " + generateToken(TokenStyle.ACCESS_TOKEN));
		System.out.println("RefreshToken: " + generateToken(TokenStyle.REFRESH_TOKEN));
		System.out.println("ClientToken: " + generateToken(TokenStyle.CLIENT_TOKEN));
	}
}
