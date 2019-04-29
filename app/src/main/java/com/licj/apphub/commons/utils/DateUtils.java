package com.licj.apphub.commons.utils;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final String TAG = "DateUtils";

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param millseconds 精确到毫秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String millseconds, String format) {
        if (millseconds == null || millseconds.isEmpty() || millseconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(millseconds)));
    }

    /**
     * 日期格式字符串转换成时间戳（精确到毫秒）
     *
     * @param dateStr 字符串日期
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime());
        } catch (ParseException e) {
            Log.e(TAG, "日期转换成时间戳出错!");
            return "";
        }
    }

    /**
     * 取得当前时间戳（精确到毫秒）
     *
     * @return
     */
    public static String timeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
     * 根据差值返回多长之间前或多长时间后
     *
     * @param time1
     * @param time2
     * @return
     */
    public static String getDistanceTime(long time1, long time2) {
        long day, hour, min, sec, diff;

        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day != 0) return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        if (hour != 0) return hour + "小时" + min + "分钟" + sec + "秒";
        if (min != 0) return min + "分钟" + sec + "秒";
        if (sec != 0) return sec + "秒";
        return "0秒";
    }

    /**
     * 增加年月日后转换成日期格式字符串
     *
     * @param year
     * @param month
     * @param day
     * @param dateStr
     * @param format
     * @return
     */
    public static String changeDate(int year, int month, int day, String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date data = null;
        try {
            data = sdf.parse(dateStr);
        } catch (ParseException e) {
            Log.e(TAG, "日期转换成时间戳出错!");
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(data);
        rightNow.add(Calendar.YEAR, year);
        rightNow.add(Calendar.MONTH, month);
        rightNow.add(Calendar.DAY_OF_YEAR, day);
        Date dt = rightNow.getTime();
        return sdf.format(dt);
    }

    /**
     * @param num
     * @return
     */
    public static String formatDecNum(int num) {
        String numStr;
        if (num <= 9 && num >= 0) {
            numStr = "0" + num;
        } else {
            numStr = String.valueOf(num);
        }
        return numStr;
    }

}
