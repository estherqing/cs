package com.test.util;

import java.lang.Character.UnicodeBlock;

public class UnicodeUtil {
	public static String encodeUnicode(String s) {
		StringBuffer ret = new StringBuffer();

		char[] chars = s.toCharArray();
		for (char c : chars) {
			if (c < 10) {
				ret.append("\\u000").append((int) c);
				continue;
			}
			UnicodeBlock ub = UnicodeBlock.of(c);
			if (ub == UnicodeBlock.BASIC_LATIN) {
				// 英文及数字等
				ret.append(c);
			} else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				// 全角半角字符
				ret.append((char) (c - 65248));
			} else {
				// 汉字
				String hexS = Integer.toHexString(c);
				String unicode = "\\u" + hexS;
				ret.append(unicode.toLowerCase());
			}
		}

		return ret.toString();
	}

	public static String decodeUnicode(String unicodeStr) {
		StringBuffer ret = new StringBuffer();

		char[] chars = unicodeStr.toCharArray();
		char c;

		for (int i = 0, len = chars.length; i < len;) {
			c = chars[i++];
			if (c == '\\') {
				c = chars[i++];
				if (c == 'u') {
					int decimalNum = 0;
					for (int j = 0; j < 4; j++) {
						c = chars[i++];
						if (c >= '0' && c <= '9') {
							decimalNum = (decimalNum << 4) + c - '0';
						} else if (c >= 'a' && c <= 'f') {
							decimalNum = (decimalNum << 4) + 10 + c - 'a';
						} else if (c >= 'A' && c <= 'F') {
							decimalNum = (decimalNum << 4) + 10 + c - 'A';
						} else {
							throw new IllegalArgumentException("Wrong format, correct format is \\uxxxx.");
						}
					}

					ret.append((char) decimalNum);
				} else {
					if (c == 'b') {
						ret.append('\b');
					} else if (c == 'f') {
						ret.append('\f');
					} else if (c == 'n') {
						ret.append('\n');
					} else if (c == 'r') {
						ret.append('\r');
					} else if (c == 't') {
						ret.append('\t');
					} else {
						ret.append(c);
					}
				}
			} else {
				ret.append(c);
			}
		}

		return ret.toString();
	}
}
