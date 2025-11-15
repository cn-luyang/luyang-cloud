package io.github.luyang.business.plan._common.util;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.business.plan._common.enums.db.CalendarColor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

/**
 * @author yang.lu
 */
public class ColorUtil {

	public static CalendarColor findClosestColor(String codeHex) {
		// 校验颜色格式是否为 #RRGGBB 的十六进制格式
		if (StrUtil.isBlank(codeHex) || !codeHex.matches("^#[0-9a-fA-F]{6}$")) {
			return CalendarColor.BLUE;
		}

		// 1. 优先尝试精确匹配枚举中的颜色
		Optional<CalendarColor> exactMatch = Arrays.stream(CalendarColor.values())
			.filter(c -> StrUtil.equalsIgnoreCase(c.getCode(), codeHex))
			.findFirst();
		if (exactMatch.isPresent()) {
			return exactMatch.get();
		}

		// 2. 将目标颜色转为 RGB 数组
		int[] targetRgb = hexToRgb(codeHex);

		// 3. 遍历枚举，找到与目标颜色最接近的颜色（欧几里得距离最小）
		return Arrays.stream(CalendarColor.values())
			.min(Comparator.comparingDouble(c -> colorDistance(targetRgb, hexToRgb(c.getCode()))))
			.orElse(null); // 理论上不会为 null，除非 Color.values() 为空
	}

	/**
	 * 将十六进制颜色字符串（如 #FFAA00）转换为 RGB 数组
	 *
	 * @param hex 十六进制颜色字符串
	 * @return RGB 数组：int[0]=Red, int[1]=Green, int[2]=Blue
	 * @author yang.lu
	 */
	private static int[] hexToRgb(String hex) {
		return new int[]{
			Integer.parseInt(hex.substring(1, 3), 16),
			Integer.parseInt(hex.substring(3, 5), 16), // 绿色分量
			Integer.parseInt(hex.substring(5, 7), 16)  // 蓝色分量
		};
	}

	/**
	 * 计算两个 RGB 颜色之间的欧几里得距离，用于衡量颜色相近程度
	 *
	 * @param rgb1 第一个颜色的 RGB 数组
	 * @param rgb2 第二个颜色的 RGB 数组
	 * @return 两个颜色之间的距离，值越小表示越相近
	 * @author yang.lu
	 */
	private static double colorDistance(int[] rgb1, int[] rgb2) {
		return Math.sqrt(
			Math.pow(rgb1[0] - rgb2[0], 2) +
				Math.pow(rgb1[1] - rgb2[1], 2) +
				Math.pow(rgb1[2] - rgb2[2], 2)
		);
	}
}
