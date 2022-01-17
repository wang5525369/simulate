package com.itrus.contract.tools;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @description: 时间方法
 * @author: guohongbao
 * @Date: 2019/1/8 14:15
 */
public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String DATE_FORMAT = "yyyy-MM-dd";

    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String TIME_FORMAT = "HH:mm:ss";

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 将字符串日期转换为日期格式
     *
     * @param datestr
     * @return
     */
    public static Date stringToDate(String datestr) {

        if (datestr == null || datestr.equals("")) {
            return null;
        }
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            date = DateUtils.stringToDate(datestr, "yyyy-MM-dd");
        }
        return date;
    }

    /**
     * 将字符串日期转换为日期格式
     * 自定義格式
     *
     * @param datestr
     * @return
     */
    public static Date stringToDate(String datestr, String dateformat) {
        Date date = new Date();
        SimpleDateFormat df;
        if (StringUtils.isNotEmpty(dateformat)){
            df = new SimpleDateFormat(dateformat);
        } else{
            df = new SimpleDateFormat(DATE_TIME_FORMAT);
        }
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 将日期格式日期转换为字符串格式
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
        datestr = df.format(date);
        return datestr;
    }

    /**
     * 将日期时间转换为字符串格式
     *
     * @param date
     * @return
     */
    public static String timeString(Date date) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.TIME_FORMAT);
        datestr = df.format(date);
        return datestr;
    }

    /**
     * 将日期时间转换为字符串格式
     *
     * @param date
     * @return
     */
    public static String dateString(Date date) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
        datestr = df.format(date);
        return datestr;
    }



    /**
     * 获取日期的YEAR值
     *
     * @param date 输入日期
     * @return
     */
    public static int getYearOfDate(Date date) {
        int y = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        y = cd.get(Calendar.YEAR);
        return y;
    }

    /**
     * 获取星期几
     *
     * @param date 输入日期
     * @return
     */
    public static int getWeekOfDate(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int result;
        int wd = cd.get(Calendar.DAY_OF_WEEK);
        switch (wd) {
            case Calendar.MONDAY:
                result=1;
                break;
            case Calendar.TUESDAY:
                result=2;
                break;
            case Calendar.WEDNESDAY:
                result=3;
                break;
            case Calendar.THURSDAY:
                result=4;
                break;
            case Calendar.FRIDAY:
                result=5;
                break;
            case Calendar.SATURDAY:
                result=6;
                break;
            case Calendar.SUNDAY:
                result=7;
                break;
            default:
                throw new RuntimeException("遇到未知的星期天数");
        }
        return result;
    }

    /**
     * 获取输入日期的当月第一天
     *
     * @param date 输入日期
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_MONTH, 1);
        return cd.getTime();
    }

    /**
     * 获得输入日期的当月最后一天
     *
     * @param date
     */
    public static Date getLastDayOfMonth(Date date) {
        return DateUtils.addDay(DateUtils.getFirstDayOfMonth(DateUtils.addMonth(date, 1)), -1);
    }

    /**
     * 获取年周期对应日
     *
     * @param date  输入日期
     * @param iyear 年数  負數表示之前
     * @return
     */
    public static Date getYearCycleOfDate(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }


    /**
     * 在输入日期上增加（+）或减去（-）天数
     *
     * @param date 输入日期
     * @param iday 要增加或减少的天数
     */
    public static Date addDay(Date date, int iday) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.DAY_OF_MONTH, iday);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）月份
     *
     * @param date   输入日期
     * @param imonth 要增加或减少的月分数
     */
    public static Date addMonth(Date date, int imonth) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.MONTH, imonth);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）年份
     *
     * @param date  输入日期
     * @param iyear 要增加或减少的年数
     */
    public static Date addYear(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）月份
     *
     * @param date 输入日期
     * @param hour 要增加或减少的月分数
     */
    public static Date addHour(Long date, int hour) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(new Date(date));

        cd.add(Calendar.HOUR_OF_DAY, hour);

        return cd.getTime();
    }

    /**
     * 获取前一天开始时间
     *
     * @return
     */
    public static long getStartTime(int time) {
        try {
            Calendar cd = Calendar.getInstance();
            cd.setTime(new Date());
            cd.add(Calendar.MINUTE, -time);
            SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
            return df.parse(df.format(cd.getTime())).getTime();
        } catch (ParseException e) {
            logger.error("获取开始时间异常", e);
        }
        return System.currentTimeMillis();
    }

    /**
     * 获取今天开始时间
     *
     * @return
     */
    public static long getTodayStartTime() {
        try {
            Calendar cd = Calendar.getInstance();
            cd.setTime(new Date());
            SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
            return df.parse(df.format(cd.getTime())).getTime();
        } catch (ParseException e) {
            logger.error("获取前一天开始时间异常", e);
        }
        return System.currentTimeMillis();
    }


    public static Date getNextDay(String time) {
        try {
            Calendar cd = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
            Date date = df.parse(time);
            cd.setTime(date);
            cd.add(Calendar.DAY_OF_MONTH, 1);
            return df.parse(df.format(cd.getTime()));
        } catch (ParseException e) {
            logger.error("获取下一天开始时间异常", e);
        }
        return new Date();
    }

    /**
     * 获取上周开始时间
     *
     * @return
     */
    public static Date getLastWeekStartTime() {
        try {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
            if (dayofweek == 1) {
                dayofweek += 7;
            }
            cal.add(Calendar.DATE, 2 - dayofweek - 7);
            SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
            return df.parse(df.format(cal.getTime()));
        } catch (Exception e) {
            logger.error("获取上一周开始时间异常", e);
        }
        return new Date();
    }

    /**
     * 获取上周结束时间
     *
     * @return
     */
    public static Date getLastWeekEndTime() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getLastWeekStartTime());
            cal.add(Calendar.DAY_OF_WEEK, 7);
            Date weekEndSta = cal.getTime();
            return weekEndSta;
        } catch (Exception e) {
            logger.error("获取上一周结束时间异常", e);
        }
        return new Date();
    }

    /**
     * 计算时间差
     *
     * @param start
     * @param end
     * @return
     */
    public static double caculateTimeDifferent(long start, long end) {
        long diffMin = (end - start) / 1000 / 60;
        // 转成小时
        BigDecimal time = new BigDecimal(new Double(diffMin) / new Double(60)).setScale(1, RoundingMode.UP);
        return time.doubleValue();
    }

    /**
     * 转换为时间（天,时:分:秒）
     *
     * @param second
     * @return
     */
    public static String formatDateTimeFormSecend(long second) {
        long day = second / (24 * 60 * 60);
        long hour = (second / (60 * 60) - day * 24);
        long min = ((second / (60)) - day * 24 * 60 - hour * 60);
        long s = (second - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return (day > 0 ? day + "," : "")
                + (hour > 0 ? String.format("%02d:", hour) : "")
                + (min > 0 ? String.format("%02d:", min) : "00:")
                + String.format("%02d", s);
    }

    /**
     * 转换为时间（天,时:分）
     *
     * @param second
     * @return
     */
    public static String formatDateTimeMiunteFormSecend(long second) {
        long day = second / (24 * 60 * 60);
        long hour = (second / (60 * 60) - day * 24);
        long min = ((second / (60)) - day * 24 * 60 - hour * 60);
        long s = (second - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return (day > 0 ? day + "," : "")
                + (hour > 0 ? String.format("%02d:", hour) : "00:")
                + (min > 0 ? String.format("%02d", min) : "00");

    }

    /**
     * 转换为时间（天,时:分）
     *
     * @param second
     * @return
     */
    public static String formatHourMiunteFormSecend(long second) {
        long day = 0;
        long hour = (second / (60 * 60) - day * 24);
        long min = ((second / (60)) - day * 24 * 60 - hour * 60);
        return (day > 0 ? day + "," : "")
                + (hour > 0 ? String.format("%02d:", hour) : "00:")
                + (min > 0 ? String.format("%02d", min) : "00");

    }


    /**
     * 获取两个时间的间隔天数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long getInterValDays(String startTime, String endTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startTime, formatter);
            LocalDate endDate = LocalDate.parse(endTime, formatter);
            long days = ChronoUnit.DAYS.between(startDate, endDate);
            return days;
        } catch (Exception e) {
            logger.error("获取时间间隔天数异常");
        }
        return 0;
    }

    /**
     * 获取两个时间段的所有日期
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getBetweenDates(String start, String end) {
        List<String> result = new ArrayList<String>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start_date = sdf.parse(start);
            Date end_date = sdf.parse(end);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start_date);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end_date);
            while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
                result.add(sdf.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * 获取给定月的所有天数
     *
     * @param date
     * @return
     */
    public static List<Date> getMonthDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;
        List<Date> list = Lists.newArrayList();
        Calendar cal = Calendar.getInstance();
        if (year != null && month != null) {
            cal.set(year, month - 1, 1, 0, 0, 0);// 所求月的第一天
        } else if (month != null) {
            cal.set(cal.get(Calendar.YEAR), month - 1, 1, 0, 0, 0);// 所求月的第一天
        } else {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);// 所求月的第一天
        }
        cal.set(Calendar.MILLISECOND, 0);
        int dayOfMonth = cal.getActualMaximum(Calendar.DATE);// 获取该月的天数
        for (int i = 1; i <= dayOfMonth; i++) {
            list.add(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;

    }

    public static List<Date> getWeekDays(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        List<Date> list = Lists.newArrayList();
        int offset = 0;
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                offset--;
            case Calendar.SATURDAY:
                offset--;
            case Calendar.FRIDAY:
                offset--;
            case Calendar.THURSDAY:
                offset--;
            case Calendar.WEDNESDAY:
                offset--;
            case Calendar.TUESDAY:
                offset--;
            case Calendar.MONDAY:
                break;
            default:
                break;
        }

        cal.add(Calendar.DAY_OF_WEEK, offset);
        for (int i = 1; i<=7; i++) {
            list.add(cal.getTime());
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }

        return list;
    }

    /**
     * 转换月天的格式
     *
     * @param second
     * @return
     */
    public static String getMonthAndDay(long second) {
        String value = "";
        Date date = new Date(second);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY) - 1;
        if (hour < 0) {
            value = month + "." + day;
        } else {
            value = month + "." + day + "." + hour;
        }
        return value;
    }

    /**
     * 填充DateTime数据的Time部分
     */
    public static Date changeTime(Date dDate, int nHour, int nMinutes, int nSeconds, int nMilliSeconds) {
        Date dTemp = org.apache.commons.lang.time.DateUtils.setHours(dDate, nHour);
        dTemp = org.apache.commons.lang.time.DateUtils.setMinutes(dTemp, nMinutes);
        dTemp = org.apache.commons.lang.time.DateUtils.setSeconds(dTemp, nSeconds);
        dTemp = org.apache.commons.lang.time.DateUtils.setMilliseconds(dTemp, nMilliSeconds);
        return dTemp;
    }
}