package com.yto.template.utils;
import android.util.Log;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Zc on 2017/10/13.
 */

public class DateUtil {
    /**
     * 英文简写如：2010
     */
    public static String FORMAT_Y = "yyyy";

    /**
     * 英文简写如：12:01
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * 英文简写如：1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 英文全称  如：2010-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL_SN = "yyyyMMddHHmmssS";

    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";

    /**
     * 中文简写  如：2010年12月01日  12时
     */
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";

    /**
     * 中文简写  如：2010年12月01日  12时12分
     */
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    public static Calendar calendar = null;
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date str2Date(String str) {
        return str2Date(str, null);
    }


    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);
    }


    public static Calendar str2Calendar(String str, String format) {

        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }


    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }


    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }


    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }


    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }


    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + "-" +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
                ":" + c.get(Calendar.SECOND);
    }


    /**
     * 获得当前日期的字符串格式
     * @param format    格式化的类型
     * @return  返回格式化之后的事件
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);

    }


    /**
     *
     * @param time 当前的时间
     * @return  格式到秒
     */
    //
    public static String getMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }


    /**
     *
     * @param time  当前的时间
     * @return  当前的天
     */
    public static String getDay(long time) {

        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }


    /**
     *
     * @param time 时间
     * @return 返回一个毫秒
     */
    // 格式到毫秒
    public static String getSMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }


    /**
     * 在日期上增加数个整月
     * @param date 日期
     * @param n 要增加的月数
     * @return   增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();

    }


    /**
     * 在日期上增加天数
     * @param date 日期
     * @param n 要增加的天数
     * @return   增加之后的天数
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();

    }

    /**
     * 获取当月的 天数
     * */
    public static int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     * */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;

        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }

    /**
     * 获取距现在某一小时的时刻
     *
     * @param format 格式化时间的格式
     * @param h 距现在的小时 例如：h=-1为上一个小时，h=1为下一个小时
     * @return  获取距现在某一小时的时刻
     */
    public static String getNextHour(String format, int h) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(date.getTime() + h * 60 * 60 * 1000);
        return sdf.format(date);

    }


    /**
     * 获取时间戳
     * @return 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());

    }

    /**
     * 功能描述：返回年
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getYear(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * 功能描述：返回日
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 功能描述：返回小
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }


    /**
     * 获得默认的 date pattern
     * @return  默认的格式
     */
    public static String getDatePattern() {

        return FORMAT_YMDHMS;
    }


    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();

        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }


    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return 提取字符串的日期
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());

    }


    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }


    /**
     * 按默认格式的字符串距离今天的天数
     *
     * @param date 日期字符串
     * @return 按默认格式的字符串距离今天的天数
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }


    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return  提取字符串日期
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date 日期字符串
     * @param format 日期格式
     * @return  按用户格式字符串距离今天的天数
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }

    private Date endDate;

    private static SimpleDateFormat formatBuilder;
    public static final int WEEKDAYS = 7;
    public static String[] WEEK = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public DateUtil(long endTime) {
        endDate = new Date(endTime);
    }


    public long timeMills = 0;


    /**
     * 时间转换
     *
     * @param time 时间
     * @param timeFormat 时间的格式 eg: yyyy-MM-dd hh:mm:ss
     * @return String
     */
    public String convert(String time, String timeFormat) {

        timeMills = Long.parseLong(time);
        Date date = new Date(timeMills);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }


    /**
     * @param startTime 开始的事件
     * @return 返回距离发帖时间的时间差
     */
    public String twoDateDistance(String startTime) {

        if (startTime.equals("")) {

            return "";
        }
        timeMills = Long.parseLong(startTime);
        Date startDate = new Date(timeMills);

        if (startDate == null || endDate == null) {
            return null;
        }
        long timeLong = endDate.getTime() - startDate.getTime();
        if (timeLong <= 0) {
            return "刚刚";
        } else if (timeLong < 60 * 1000) {
            return timeLong / 1000 + "秒前";
        } else if (timeLong < 60 * 60 * 1000) {
            timeLong = timeLong / 1000 / 60;
            return timeLong + "分钟前";
        } else if (timeLong < 60 * 60 * 24 * 1000) {
            timeLong = timeLong / 60 / 60 / 1000;
            return timeLong + "小时前";
        } else if (timeLong < 60 * 60 * 24 * 1000 * 7) {
            timeLong = timeLong / 1000 / 60 / 60 / 24;
            return timeLong + "天前";
        } else if (timeLong < 60 * 60 * 24 * 1000 * 7 * 4) {
            timeLong = timeLong / 1000 / 60 / 60 / 24 / 7;
            return timeLong + "周前";
        } else {
            timeLong = timeLong / 1000 / 60 / 60 / 24;
            return timeLong + "天前";
        }
    }

    /**
     * UTM转换成日期描述，如三周前，上午，昨天等
     *
     * @param milliseconds milliseconds
     * @param isShowWeek 是否采用周的形式显示  true 显示为3周前，false 则显示为时间格式mm-dd
     * @return 如三周前，上午，昨天等
     */

    public static String getTimeDesc(long milliseconds, boolean isShowWeek) {
        StringBuffer sb = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        long hour = calendar.get(Calendar.HOUR_OF_DAY);

        calendar.setTimeInMillis(System.currentTimeMillis());
        long hourNow = calendar.get(Calendar.HOUR_OF_DAY);

        Log.v("---------->---", System.currentTimeMillis() + "----------" + milliseconds);
        long datetime = System.currentTimeMillis() - (milliseconds);
        long day = (long) Math.floor(datetime / 24 / 60 / 60 / 1000.0f) + (hourNow < hour ? 1 : 0);// 天前

        if (day <= 7) {// 一周内
            if (day == 0) {// 今天
                if (hour <= 4) {
                    sb.append(" 凌晨 ");
                } else if (hour > 4 && hour <= 6) {
                    sb.append(" 早上 ");
                } else if (hour > 6 && hour <= 11) {
                    sb.append(" 上午 ");
                } else if (hour > 11 && hour <= 13) {
                    sb.append(" 中午 ");
                } else if (hour > 13 && hour <= 18) {
                    sb.append(" 下午 ");
                } else if (hour > 18 && hour <= 19) {
                    sb.append(" 傍晚 ");
                } else if (hour > 19 && hour <= 24) {
                    sb.append(" 晚上 ");
                } else {
                    sb.append("今天 ");
                }
            } else if (day == 1) {// 昨天
                sb.append(" 昨天 ");
            } else if (day == 2) {// 前天
                sb.append(" 前天 ");
            } else {
                sb.append(" " + DateToWeek(milliseconds) + " ");
            }
        } else {// 一周之前
            if (isShowWeek) {
                sb.append((day % 7 == 0 ? (day / 7) : (day / 7 + 1)) + "周前");
            } else {
                formatBuilder = new SimpleDateFormat("MM-dd");
                String time = formatBuilder.format(milliseconds);
                sb.append(time);
            }
        }
        Log.v("sb---", sb.toString() + "");
        return sb.toString();

    }

    /**
     * UTM转换成日期描述，如三周前，上午，昨天等
     *
     * @param milliseconds 时间
     * @return UTM转换成日期描述，如三周前，上午，昨天等
     */
    public static String getTimeDesc(long milliseconds) {

        return getTimeDesc(milliseconds, true);
    }

    /**
     * UTM转换成日期 ,hh:mm
     *
     * @param milliseconds milliseconds
     * @return UTM转换成日期 ,hh:mm
     */

    public static String getDisplayTime(long milliseconds) {
        formatBuilder = new SimpleDateFormat("HH:mm");
        String time = formatBuilder.format(milliseconds);
        return time;
    }

    /**
     * UTM转换成带描述的日期
     *
     * @param milliseconds milliseconds
     * @return UTM转换成带描述的日期
     */

    public static String getDisplayTimeAndDesc(long milliseconds) {
        formatBuilder = new SimpleDateFormat("HH:mm");
        String time = formatBuilder.format(milliseconds);
        StringBuffer sb = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        long hour = calendar.get(Calendar.HOUR_OF_DAY);
        Log.v("---------->---", System.currentTimeMillis() + "----------" + milliseconds);
        long datetime = System.currentTimeMillis() - (milliseconds);
        long day = (long) Math.ceil(datetime / 24 / 60 / 60 / 1000.0f);// 天前
        Log.v("day---hour---time---", day + "----" + hour + "-----" + time);

        if (day <= 7) {// 一周内
            if (day == 0) {// 今天
                if (hour <= 4) {
                    sb.append(" 凌晨 " + time);
                } else if (hour > 4 && hour <= 6) {
                    sb.append(" 早上 " + time);
                } else if (hour > 6 && hour <= 11) {
                    sb.append(" 上午 " + time);
                } else if (hour > 11 && hour <= 13) {
                    sb.append(" 中午 " + time);
                } else if (hour > 13 && hour <= 18) {
                    sb.append(" 下午 " + time);
                } else if (hour > 18 && hour <= 19) {
                    sb.append(" 傍晚 " + time);
                } else if (hour > 19 && hour <= 24) {
                    sb.append(" 晚上 " + time);
                } else {
                    sb.append("今天 " + time);
                }
            } else if (day == 1) {// 昨天
                sb.append("昨天 " + time);
            } else if (day == 2) {// 前天
                sb.append("前天 " + time);
            } else {
                sb.append(DateToWeek(milliseconds) + time);
            }
        } else {// 一周之前
            sb.append(day % 7 + "周前");
        }
        Log.v("sb---", sb.toString() + "");
        return sb.toString();

    }

    /**
     * 日期变量转成对应的星期字符串
     *
     * @param milliseconds data
     * @return 日期变量转成对应的星期字符串
     */
    public static String DateToWeek(long milliseconds) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }

        return WEEK[dayIndex - 1];
    }

    /**
     * 将时间间隔转换成描述性字符串，如2天前，3月1天后等。
     *
     * @param toDate 相对的日期
     * @param isFull 是否全部显示 true 全部显示 false 简单显示
     * @return 将时间间隔转换成描述性字符串，如2天前，3月1天后等。
     */
    public static String diffDateAsDesc(Date toDate, boolean isFull) {
        String diffDesc = "";
        String fix = "";
        Long diffTime;
        Date curDate = new Date();
        if (curDate.getTime() > toDate.getTime()) {
            diffTime = curDate.getTime() - toDate.getTime();
            fix = "前";
        } else {
            diffTime = toDate.getTime() - curDate.getTime();
            fix = "后";
        }

        //换算成分钟数，防止Int溢出。
        diffTime = diffTime / 1000 / 60;

        Long year = diffTime / (60 * 24 * 30 * 12);
        diffTime = diffTime % (60 * 24 * 30 * 12);
        if (year > 0) {
            diffDesc = diffDesc + year + "年";
            if (!isFull) {
                return diffDesc + fix;
            }
        }

        Long month = diffTime / (60 * 24 * 30);
        diffTime = diffTime % (60 * 24 * 30);
        if (month > 0) {
            diffDesc = diffDesc + month + "月";
            if (!isFull) {
                return diffDesc + fix;
            }
        }

        Long day = diffTime / 60 / 24;
        diffTime = diffTime % (60 * 24);
        if (day > 0) {
            diffDesc = diffDesc + day + "天";
            if (!isFull) {
                return diffDesc + fix;
            }
        }

        Long hour = diffTime / (60);
        diffTime = diffTime % (60);
        if (hour > 0) {
            diffDesc = diffDesc + hour + "时";
            if (!isFull) {
                return diffDesc + fix;
            }
        }

        Long minitue = diffTime;
        if (minitue > 0) {
            diffDesc = diffDesc + minitue + "分";
            if (!isFull) {
                return diffDesc + fix;
            }
        }

        return diffDesc + fix;
    }


    /**
     * 返回两个距离某个值得时间差
     *
     * @param nowTime 毫秒值
     * @param startTIme1 毫秒值
     */
    public static String timeDifference(long nowTime, String startTIme1) {
        if (null == formatBuilder) {
            formatBuilder = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        String startTime = formatBuilder.format(nowTime);
        String endTime = formatBuilder
                .format(new Date(Long.parseLong(startTIme1)));//如果服务器返回的时间是unix时间，单位是秒，而java中获取得单位是毫秒，需要注意
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            return day + "天" + hour + "时" + min + "分" + sec + "秒";
        } catch (Exception e) {
            return null;
        }

    }

    public static long getMilliseconds(String date){
        formatBuilder = new SimpleDateFormat(FORMAT_YMD);
        ParsePosition pos = new ParsePosition(0);
        Date date1 = formatBuilder.parse(date,pos);
        long time = date1.getTime();
        return time;
    }

}
