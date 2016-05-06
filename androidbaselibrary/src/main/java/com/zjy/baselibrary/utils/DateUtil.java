package com.zjy.baselibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jiyoung.tsang on 16/5/6.
 */
public class DateUtil {
    /** 获得上周一的日期 ，格式为"yyyy_MM_dd"*/
    public static String getPreMondayOfWeek() {

        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, getMondayPlus() - 7);
        Date monday = currentDate.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
        return format.format(monday);

    }

    /** 获取当前日期字符串，格式为"yyyy_MM_dd" */
    public static String getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        return sdf.format(new Date());
    }

    /** 获得当前日期与本周日相差的天数 */
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    /** 获得本周一的日期，格式为"yyyy_MM_dd"*/
    public static String getMondayOfWeek() {

        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, getMondayPlus());
        Date monday = currentDate.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
        return format.format(monday);
    }

    /**
     * 计算间隔天数，endDate日期必须大于startDate,否则返回结果为负数
     *
     * @param startDate
     *            格式为"yyyy_MM_dd"
     * @param endDate
     *            格式为"yyyy_MM_dd"
     * @return 天数
     */
    public static long getTwoDayBetween(String startDate, String endDate) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy_MM_dd");
        long day = 0;
        try {
            Date mydate = myFormatter.parse(startDate);
            Date date = myFormatter.parse(endDate);

            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return 0;
        }
        return day;
    }



}
