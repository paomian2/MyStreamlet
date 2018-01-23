package com.linxz.streamlet.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：正则表达式
 *
 * @author Linxz
 *         E-mail:lin_xiao_zhang@163.com
 * @version V1.0
 * @ddate 2018年01月22日  9:41
 */
public class PatternUtils {

    /**邮箱正则*/
    private static final Pattern EMAIL_PATTERN=Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    /**
     * 判断邮箱地址是否有效限
     */
    public static boolean isEmailAddValid(String address) {
        if (address != null && address.length() > 0) {
            char[] cAddress = address.toCharArray();
            for (char c : cAddress) {
                if (c > 127) {
                    return false;
                }
            }
            Matcher m = EMAIL_PATTERN.matcher(address);
            return m.matches();
        }
        return false;
    }
}
