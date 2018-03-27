package com.yto.template.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Zc on 2017/12/20.
 */

public class RegexpUtils {
    private RegexpUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 验证银卡卡号
     *
     * @param cardNo
     * @return
     */
    public static final boolean isBankCard(String cardNo) {
        Pattern p = Pattern
                .compile("^\\d{16,19}$|^\\d{6}[- ]\\d{10,13}$|^\\d{4}[- ]\\d{4}[- ]\\d{4}[- ]\\d{4,7}$");
        Matcher m = p.matcher(cardNo);

        return m.matches();
    }

    /**
     * 身份证验证
     *
     * @param idCard
     * @return
     */
    public static final boolean isIdCard(String idCard) {
        // 15位和18位身份证号码的正则表达式
        String regIdCard = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";
        Pattern p = Pattern.compile(regIdCard);
        return p.matcher(idCard).matches();
    }

    /**
     * 验证车牌号
     * @param carNo
     * @return
     * */
    public static boolean isCarNo(String carNo){
        Pattern pattern = Pattern.compile("^[A-Z]{1}[A-Z0-9]{5}$|^[A-Z]{1}[A-Z0-9]{4}[挂学警军港澳]{1}$");
        Matcher matcher = pattern.matcher(carNo);
        return matcher.matches();
    }

    /**
     * 验证手机号
     * 由于现在有网络手机号码出现，做过多次匹配
     * @param mobileNo
     * @return
     * */
    public static boolean isMobileNO(String mobileNo) {
        if(mobileNo!=null&&mobileNo.length()!=11) return false;
        else{
//	          Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
//            Pattern p = Pattern.compile("13\\d{9}|14[57]\\d{8}|15[012356789]\\d{8}|18\\d{9}|17[0678]\\d{8}");
            Pattern p = Pattern.compile("1[0-9]\\d{9}");
            Matcher m = p.matcher(mobileNo);
            return m.matches();
        }
    }

    /**
     * 验证邮编
     * @param zipNo
     * @return
     * */
    public static boolean isZip(String zipNo){
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        Matcher matcher = pattern.matcher(zipNo);
        return matcher.matches();
    }

    /**
     * 验证邮箱
     * @param emailNo
     * @return
     * */
    public static boolean isEmail(String emailNo){
        String EMAIL_REGEXP = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern pattern = Pattern.compile(EMAIL_REGEXP);
        Matcher matcher = pattern.matcher(emailNo);
        return matcher.matches();
    }
}
