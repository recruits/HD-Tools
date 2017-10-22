package com.chilicool.hdtools.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Description: 日期格式化工具类</p>
 * <p>Company:</p>
 *
 * @author zhangzechen
 * @date 2015年10月9日 下午1:54:34
 */
public class DateUtil {

    /**
     * 日期操作，增加月份
     *
     * @param dateStr
     * @param dfPattern
     * @param months
     * @return
     * @throws ParseException
     */
    public static String addMonths(String dateStr, DFPattern dfPattern, int months) throws ParseException {
        Date currDate = parse(dateStr, dfPattern);
        Date returnDate = addMonths(currDate, months);
        return format(returnDate, dfPattern);
    }

    /**
     * 日期操作，增加月份
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date, int months){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * <p>Description: 日期->字符串格式化</p>
     *
     * @param date
     * @param dfPpattern
     * @return
     * @author zhangzechen
     * @date 2015年12月11日 上午10:07:47
     */
    public static String format(Date date, DFPattern dfPpattern) {
        SimpleDateFormat dateFormat = getFormatFromMap(dfPpattern);
        return dateFormat.format(date);
    }

    /**
     * <p>Description:字符串->日期格式化</p>
     *
     * @param date
     * @param dfPpattern
     * @return
     * @throws ParseException
     * @author zhangzechen
     * @date 2015年12月11日 上午10:08:14
     */
    public static Date parse(String date, DFPattern dfPpattern) throws ParseException {
        SimpleDateFormat dateFormat = getFormatFromMap(dfPpattern);
        return dateFormat.parse(date);
    }

    /**
     * <p>Description: 取两个日期时间间隔(s)</p>
     *
     * @param bigDate
     * @param littleDate
     * @return
     * @author zhangzechen
     * @date 2015年12月11日 上午10:08:29
     */
    public static Long cacuDateInterval(Date bigDate, Date littleDate) {
        return (bigDate.getTime() - littleDate.getTime()) / 1000;
    }

    /**
     * <p>Description: 取两个日期时间间隔(ms)</p>
     *
     * @param bigDate
     * @param littleDate
     * @return
     */
    public static Long cacuDateIntervalMs(Date bigDate, Date littleDate) {
        return (bigDate.getTime() - littleDate.getTime());
    }

    /**
     * <p>Description: 取两个日期时间间隔</p>
     *
     * @param bigDate
     * @param bigDatePattern
     * @param littleDate
     * @param littleDatePattern
     * @return
     * @author zhangzechen
     * @date 2015年12月11日 上午10:14:01
     */
    public static Long cacuDateInterval(String bigDate, DFPattern bigDatePattern, String littleDate, DFPattern littleDatePattern) {
        if (null == bigDate || null == littleDate) {
            return 0L;
        }
        if (null == bigDatePattern || null == littleDatePattern) {
            return 0L;
        }
        Long interval = 0L;
        try {
            interval = cacuDateInterval(parse(bigDate, bigDatePattern), parse(littleDate, littleDatePattern));
        } catch (ParseException e) {
            interval = 0L;
        }
        return interval;
    }

    /**
     * <p>Description: 取两个日期时间间隔</p>
     *
     * @param bigDate
     * @param bigDatePattern
     * @param littleDate
     * @param littleDatePattern
     * @return
     * @author zhangzechen
     * @date 2015年12月11日 上午10:22:07
     */
    public static Long cacuDateInterval(Long bigDate, DFPattern bigDatePattern, Long littleDate, DFPattern littleDatePattern) {
        return cacuDateInterval(bigDate.toString(), bigDatePattern, littleDate.toString(), littleDatePattern);
    }

    /**
     * 毫秒值格式化为 XX天XX小时XX分XX秒
     *
     * @param milisecond
     * @return
     */
    public static String formatTimeWithMilisecond(final long milisecond) {
        return formatTimeWithSecond(milisecond / 1000);
    }

    /**
     * 秒值格式化为 XX天XX小时XX分XX秒
     *
     * @param second
     * @return
     */
    public static String formatTimeWithSecond(final long second) {
        StringBuilder sBuilder = new StringBuilder();
        formatTimeWithSecond(second, sBuilder);
        return sBuilder.toString();
    }

    /**
     * 秒值格式化为 XX天XX小时XX分XX秒
     *
     * @param second
     * @param sBuilder
     * @return
     */
    public static void formatTimeWithSecond(final long second, StringBuilder sBuilder) {
        if (null == sBuilder) {
            sBuilder = new StringBuilder();
        }
        long unit_minus_time = UNIT_MINUS_TIME / 1000;
        long unit_hour_time = UNIT_HOUR_TIME / 1000;
        long unit_day_time = UNIT_DAY_TIME / 1000;
        // 一分钟以内
        if( second == 0){
            return;
        } else if (second < unit_minus_time) {
            long unit = second / unit_minus_time;
            sBuilder.append((unit == 0 ? 1 : unit) + UNIT_SECOND_NAME);
            return;
        }
        // 一个小时以内
        else if (second < unit_hour_time) {
            sBuilder.append((second / unit_minus_time) + UNIT_MINUS_NAME);
            formatTimeWithSecond(second % unit_minus_time, sBuilder);
        }
        // 未超过一天
        else if (second < unit_day_time) {
            sBuilder.append((second / unit_hour_time) + UNIT_HOUR_NAME);
            formatTimeWithSecond(second % unit_hour_time, sBuilder);
        }
        // 超过一天
        else {
            sBuilder.append((second / unit_day_time) + UNIT_DAY_NAME);
            formatTimeWithSecond(second % unit_day_time, sBuilder);
        }
    }

    private static SimpleDateFormat getFormatFromMap(DFPattern dfPpattern) {
        String pattern = dfPpattern.getKey();
        if (null == formatMap.get(pattern)) {
            formatMap.put(pattern, getDateFormat(pattern));
        }
        return formatMap.get(pattern);
    }

    private static SimpleDateFormat getDateFormat(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);    // 格式强校验
        return dateFormat;
    }

    private static Map<String, SimpleDateFormat> formatMap = new HashMap<String, SimpleDateFormat>();

    public enum DFPattern {
        YYYYMM("yyyyMM"),
        YYYY_MM("yyyy-MM"),
        YYYY_MM_DD("yyyy-MM-dd"),
        HH_MM_SS("HH:mm:ss"),
        YYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
        YYY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS"),
        YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
        YYYYMMDD("yyyyMMdd");

        private DFPattern(String key) {
            this.key = key;
        }

        private String key;

        public String getKey() {
            return key;
        }
    }


    /**
     * 其他时间值转换为毫秒单位：秒
     */
    public static final long UNIT_SECOND_TIME = 1000;
    /**
     * 其他时间值转换为毫秒单位：分钟
     */
    public static final long UNIT_MINUS_TIME = 60 * UNIT_SECOND_TIME;
    /**
     * 其他时间值转换为毫秒单位：小时
     */
    public static final long UNIT_HOUR_TIME = 60 * UNIT_MINUS_TIME;
    /**
     * 其他时间值转换为毫秒单位：天
     */
    public static final long UNIT_DAY_TIME = 24 * UNIT_HOUR_TIME;
    /**
     * 时间单位名称：秒
     */
    public static final String UNIT_SECOND_NAME = "秒";
    /**
     * 时间单位名称：分钟
     */
    public static final String UNIT_MINUS_NAME = "分钟";
    /**
     * 时间单位名称：小时
     */
    public static final String UNIT_HOUR_NAME = "小时";
    /**
     * 时间单位名称：天
     */
    public static final String UNIT_DAY_NAME = "天";
}
