package com.otto.borrow.web.util;
/**
 * Project Name：fomo
 * File Name：DateUtil
 * Package Name：com.fomo.util
 * Date：2018/9/4 00:23
 */

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.fomo.util
 * @ClassName DateUtil
 * @date 2018/9/4 00:23
 */
public class DateUtil {

    /**
     * 简单日期格式（年-月-日 ，yyyy-MM-dd）
     */
    public static final String DATE_FORMAT_SIMPLE = "yyyy-MM-dd";

    /**
     * 完整日期格式（年-月-日 时:分:秒，yyyy-MM-dd hh:mm:ss）
     */
    public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

    /**
     * 一秒钟的毫秒值（一秒钟 = 1000毫秒）
     */
    public static final long ONE_SECOND_MILLIS = 1000L;
    /**
     * 一分钟的毫秒值（一分钟 = 60000毫秒）
     */
    public static final long ONE_MINUTE_MILLIS = 60 * 1000L;
    /**
     * 一小时的毫秒值（一小时 = 3600000毫秒）
     */
    public static final long ONE_HOURS_MILLIS = 60 * 60 * 1000L;
    /**
     * 一天的毫秒值（一天 = 86400000毫秒）
     */
    public static final long ONE_DAY_MILLIS = 24 * 60 * 60 * 1000L;

    /**
     * 类型: 一天中的0点时刻
     */
    public static final int START_OF_DAY = 1;
    /**
     * 类型: 一天中的23.59.59时刻
     */
    public static final int END_OF_DAY = 2;

    private static final String MOMENT_0 = " 00:00:00";
    private static final String MOMENT_24 = " 23:59:59";

    public static final SimpleDateFormat SIMPLE_FORMATs = new SimpleDateFormat(DATE_FORMAT_SIMPLE);
    public static final SimpleDateFormat FULL_FORMARs = new SimpleDateFormat(DATE_FORMAT_FULL);

    /**
     * 通用日期格式化方法，将日期格式化为yyyy-MM-dd形式。
     * <p>例：1900-01-01
     *
     * @param date 日期
     * @return 格式化后的日期字符串。
     */
    public static String toSimpleFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SIMPLE);
        return sdf.format(date);
    }

    /**
     * 通用日期格式化方法，将给定时间毫秒值格式化为yyyy-MM-dd形式。
     * <p>例：1900-01-01
     *
     * @param timeMillis 时间毫秒值
     * @return 格式化后的日期字符串。
     */
    public static String toSimpleFormat(long timeMillis) {
        Timestamp ts = new Timestamp(timeMillis);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SIMPLE);
        return sdf.format(ts);
    }

    /**
     * 通用时间格式化方法，将日期格式化为yyyy-MM-dd hh:mm:ss形式。
     * <p>例：1900-01-01 13:00:00
     *
     * @param date 日期
     * @return 格式化后的时间字符串。
     */
    public static String toFullTimeFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL);
        if (date != null) {
            return sdf.format(date);
        } else {
            return null;
        }

    }

    /**
     * 通用时间格式化方法，将给定时间毫秒值格式化为yyyy-MM-dd hh:mm:ss形式。
     * <p>例：1900-01-01 13:00:00
     *
     * @param timeMillis 时间毫秒值
     * @return 格式化后的时间字符串。
     */
    public static String toFullTimeFormat(long timeMillis) {
        Timestamp ts = new Timestamp(timeMillis);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL);
        return sdf.format(ts);
    }

    public static String getDate() {
        return getDate(DATE_FORMAT_SIMPLE);
    }

    /**
     * 获取当前日期  yyyy-MM-dd
     *
     * @return
     */
    public static String getDate(String pattern) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }

    /**
     * 获取当前日期 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_FULL);
        return localDateTime.format(formatter);
    }

    public static Long getMilsecond(String str) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT_FULL);
        LocalDateTime dateTime = LocalDateTime.parse(str, df);
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 将Date按指定格式进行格式化处理。
     *
     * @param date   日期
     * @param format 格式
     * @return 格式化后的date字符串。
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL);
        System.out.println(getMilsecond("2018-09-22 11:16:11"));
        Calendar ca = Calendar.getInstance();
        ca.setTime(sdf.parse("2018-09-22 11:16:11"));
        System.out.println(ca.getTimeInMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(toFullTimeFormat(1537586171000L));
        System.out.println(toFullTimeFormat(1537767128673L));


        BigDecimal b = new BigDecimal(6.52314);
        System.out.println(b.intValue());
        System.out.println(b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
    }
}
