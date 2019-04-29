package com.licj.apphub.commons.utils;

import android.text.TextUtils;

/**
 * Created by licj on 2018/3/10.
 */

public class RegExpUtils {

    /**
     * 验证手机格式
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        String telRegex = "[1][345678]\\d{9}";// "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return mobiles.matches(telRegex);
    }

    /**
     * 校验名字是否是汉字组成
     *
     * @param name
     * @return
     */
    public static boolean isHanZi(String name) {
        String regx = "[\\u4e00-\\u9fa5]+";
        return name.matches(regx);
    }

    /**
     * 验证密码格式
     *
     * @param pwd
     * @return
     */
    public static boolean isPwd(String pwd) {
        String regx = "^[a-zA-Z][a-zA-Z0-9]{5,19}";
        return pwd.matches(regx);
    }

    /**
     * 验证身份证号码
     *
     * @param text
     * @return
     */
    public static boolean personIdValidation(String text) {
        String regx = "[0-9]{17}[Xx]";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

    /**
     * 182****7882
     * 隐藏手机号中间四位
     *
     * @param mobile
     * @return
     */
    public static String HideMobile(String mobile) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(mobile) && mobile.length() > 6) {

            for (int i = 0; i < mobile.length(); i++) {
                char c = mobile.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }

        }
        return sb.toString();
    }

    /**
     * 421182********137X
     * 身份证号 隐藏 年月日  从第7-14
     *
     * @param number
     * @return
     */
    public static String HideIdentity(String number) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(number) && number.length() > 16) {
            for (int i = 0; i < number.length(); i++) {
                char c = number.charAt(i);
                if (i >= 6 && i <= 13) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 验证是否是银行卡号
     *
     * @param card
     * @return
     */
    public static boolean isBankCard(String card) {
        String regx = "^\\d{19}$";
        return card.matches(regx);
    }

}
