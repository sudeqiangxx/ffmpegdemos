package com.sdq.qxq.ffmpegdemos.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: Kenny
 * @date: 2019-05-13 11:14
 * @version: 1.0
 */
public class StringUtils {

    private StringUtils() {
        //no instance
    }

    /**
     * 判断字符串是否为空
     * <p>
     * 包括null和""，其中"null"
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0 || "null".equals(s);
    }

    /**
     * 判断字符串是否为整数
     *
     * @param str 传入字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        if (isBlank(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[1-9][\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否为正整数,不包括+0
     *
     * @param str
     * @return
     */
    public static boolean isPositiveInteger(String str) {
        if (isBlank(str)) {
            return false;
        }
        return str.matches("^[\\+]?[1-9][\\d]*$");
    }

    /**
     * 判断是否为浮点数，包括double和float
     *
     * @param str 传入字符串
     * @return 是浮点数返回true, 否则返回false
     */
    public static boolean isDouble(String str) {
        if (isBlank(str)) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[-\\+]?[1-9][\\d]*[.]?[\\d]+$");
        return pattern.matcher(str).matches();

    }

    /**
     * 判断是否为正浮点数，包括double和float(包括+0)
     *
     * @param str 传入字符串
     * @return 是正浮点数返回true, 否则返回false
     */
    public static boolean isPositivieDouble(String str) {
        if (isBlank(str) || str.endsWith(".")) {
            return false;
        }
        return str.matches("^[\\+]?[0-9][\\d]*[.]?[\\d]*$");
    }

    /**
     * 判断是否为正百分数
     *
     * @param str
     * @return
     */
    public static boolean isPositivePercent(String str) {
        if (isBlank(str) || str.endsWith(".")) {
            return false;
        }
        return str.matches("^[\\+]?[0-9][\\d]*[.]?[\\d]*%$");
    }

    /**
     * 判断输入的字符串是否符合Email样式.
     *
     * @param str 传入字符串
     * @return 是Email样式返回true, 否则返回false
     */
    public static boolean isAnEmail(String str) {
        if (isBlank(str)) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否是电话号码
     * <p>
     * <li>
     * 可接受的电话格式有: ^\\(? : 可以使用 "(" 作为开头 (\\d{3}): 紧接着三个数字 \\)? : 可以使用")"接续
     * [- ]? : 在上述格式后可以使用具选择性的 "-". (\\d{4}) : 再紧接着三个数字 [- ]? : 可以使用具选择性的
     * "-" 接续. (\\d{4})$: 以四个数字结束. 可以比较下列数字格式: (123)456-78900,
     * 123-4560-7890, 12345678900, (123)-4560-7890
     * </li>
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        boolean isValid = false;
        String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
        String expression2 = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(phoneNumber);
        Pattern pattern2 = Pattern.compile(expression2);
        Matcher matcher2 = pattern2.matcher(phoneNumber);
        if (matcher.matches() || matcher2.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 判断是否为空，
     * <p>
     * 包括null和""，其中"  "无论有多少空格都返回true，表示为空
     *
     * @param str 传入字符串
     * @return 为空返回true，否则返回false
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 比较两个字符串
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equals(str2);
    }

    /**
     * 比较两个字符串
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isEquals(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 比较两个字符串（大小写不敏感）
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equalsIgnoreCase(str2);
    }

    /**
     * 判断字符串是否只包含unicode字母和数字。
     *
     * @param str 要检查的字符串
     * @return 如果字符串非<code>null</code>并且全由unicode字母数字组成，则返回<code>true</code>
     */
    public static boolean isAlphanumeric(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将字符串转换成大写
     *
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * 将字符串转换成小写
     *
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * 将字符串的首字符转成小写，其它字符不变
     *
     * @param str 要转换的字符串
     * @return 首字符为小写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String uncapitalize(String str) {
        int strLen;

        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }

        return new StringBuffer(strLen)
                .append(Character.toLowerCase(str.charAt(0)))
                .append(str.substring(1)).toString();
    }

    /**
     * 反转字符串的大小写。
     *
     * @param str 要转换的字符串
     * @return 大小写被反转的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String swapCase(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }
        StringBuilder buffer = new StringBuilder(strLen);
        char ch;
        for (int i = 0; i < strLen; i++) {
            ch = str.charAt(i);

            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }
            buffer.append(ch);
        }

        return buffer.toString();
    }

    /**
     * 全角转半角
     *
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 半角转全角
     *
     * @param input String.
     * @return 全角字符串.
     */
    public static String toSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';                 //采用十六进制,相当于十进制的12288


            } else if (c[i] < '\177') {    //采用八进制,相当于十进制的127
                c[i] = (char) (c[i] + 65248);

            }
        }
        return new String(c);
    }


    /**
     * ROT13
     *
     * @param input
     * @return
     */
    public static String rot13(String input) {
        StringBuilder sb = new StringBuilder();
        int len = input.length();
        for (int i = 0; i < len; i++) {
            char c = input.charAt(i);
            if (c >= 'a' && c <= 'm') c += 13;
            else if (c >= 'A' && c <= 'M') c += 13;
            else if (c >= 'n' && c <= 'z') c -= 13;
            else if (c >= 'N' && c <= 'Z') c -= 13;
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 为字符串添加括号
     *
     * @param s
     * @return (abcd)
     */
    public static String addBrackets(String s) {
        return String.format("(%s)", s);
    }

    /**
     * 删除字符串后面的回车符
     *
     * @param src
     * @return
     */
    public static String removeLastCR(String src) {
        if (isBlank(src))
            return src;
        char[] srcChs = src.toCharArray();
        int end = srcChs.length - 1;

        while (0 <= end && (srcChs[end] == '\n' || srcChs[end] == '\r')) {
            --end;
        }

        return String.valueOf(srcChs, 0, end + 1);
    }


    public static String getMaxLengthStringFromArray(ArrayList<String> array) {
        int maxLength = 0;
        String maxLengthText = "";
        for (String text : array) {
            if (!isEmpty(text)) {
                int len = text.length();
                if (len > maxLength) {
                    maxLength = len;
                    maxLengthText = text;
                }
            }
        }
        return maxLengthText;
    }

    public static boolean isStrHasChinese(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    // GENERAL_PUNCTUATION 判断中文的“号
    // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
    // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 获取字符串内容，如果为空则返回默认值
     *
     * @param content
     * @param defaultValue
     * @return
     */
    public static String getContentString(String content, String defaultValue) {
        return !isEmpty(content) ? content : defaultValue;
    }

    public static String getContentString(String content) {
        return getContentString(content, "");
    }

    /**
     * 从url截取名称
     *
     * @param url
     * @return
     */
    public static String getCutoutNameFromUrl(String url) {
        if (!isEmpty(url)) {
            int lastFlashIndex = url.lastIndexOf("/");
            if (lastFlashIndex >= 0) {
                lastFlashIndex++;
                if (lastFlashIndex <= url.length()) {
                    return url.substring(lastFlashIndex);
                }
            }
        }
        return "";
    }

    public static String getCutoutNameFromUrlWithDefault(String url) {
        String fileName = getCutoutNameFromUrl(url);
        if (isEmpty(fileName)) {
            fileName = url;
        }
        return fileName;
    }
}
