package com.dfsoft.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @description: 鏃堕棿宸ュ叿绫�
 */
public class DateUtil {

    private static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static String DATETIME_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
    private static String DATETIME_FORMAT_SECOND_NOSPACE = "yyyy/MM/dd-HH:mm:ss";
    private static String DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    private static String DATETIMEFORMAT = "yyyyMMdd";
    private static String DATETIMEFORMAT_HH = "yyMMddHH";
    private static String DATE_FORMAT = "yyyy-MM-dd";
    private static String DATE_FORMAT_MONTH = "yyyy-MM";
    private static String DATETIME_FORMAT_T_SECOND = "yyyy-MM-dd'T'HH:mm:ss";
    private static String DATETIME_FORMAT_PULL_REFRSH = "MM鏈坉d鏃� HH:mm";
    private static String DATETIME_GMT_FORMAT = "EEE, d MMM yyyy HH:mm:ss 'GMT'";
    private static String DATETIME_FORMAT_SEC_OBLIQUE = "yyyy/MM/dd HH:mm:ss";
    private static String DATETIME_FORMAT_MIN_OBLIQUE = "yyyy/MM/dd HH:mm";
    public static String DATETIME_FORMAT_TIME = "HH:mm:ss";

    private static ZoneId zone = ZoneId.systemDefault();

    /**
     * 鑾峰彇褰撳墠鏃堕棿
     */
    public static Date getNow() {
        LocalDateTime now = LocalDateTime.now();
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * date + time 杞瓧绗︿覆
     */
    public static String date2LongString(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT_SECOND);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * date + time 杞瓧绗︿覆
     */
    public static String date2FormatLongString(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT_T_SECOND);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * date 杞瓧绗︿覆
     */
    public static String date2ShortString(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 瀛楃涓� 杞� date
     */
    public static Date convertToDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 鏃堕棿鎴宠浆鏃ユ湡
     *
     * @param timestamp
     * @return
     */
    public static String timestamp2Date_YYYYMMDD(long timestamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date(timestamp));
    }

    /**
     * 寰楀埌鏃堕棿鍖洪棿鐨勫ぉ鏁�
     *
     * @param starttime
     * @param endtime
     * @return
     */
    public static int getRangeDayNum(long starttime, long endtime) {
        return (int) ((endtime - starttime) / (1000 * 3600 * 24));
    }

    /**
     * 鎸囧畾鏃ユ湡鍔犱笂澶╂暟鍚庣殑鏃ユ湡
     *
     * @param num       涓哄鍔犵殑澶╂暟
     * @param timestamp 鎸囧畾鏃堕棿
     * @return
     */
    public static long plusDay(int num, long timestamp) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date currdate = formatter.parse(formatter.format(new Date(timestamp)));
            System.out.println("鐜板湪鐨勬棩鏈熸槸锛�" + currdate);
            Calendar ca = Calendar.getInstance();
            ca.setTime(currdate);
            ca.add(Calendar.DATE, num);// num涓哄鍔犵殑澶╂暟锛屽彲浠ユ敼鍙樼殑
            currdate = ca.getTime();
            String enddate = formatter.format(currdate);
            System.out.println("澧炲姞澶╂暟浠ュ悗鐨勬棩鏈燂細" + enddate);
            return formatter.parse(enddate).getTime();
        } catch (ParseException e) {
            return 0L;
        }
    }

    public static long getOneDayStartTimeStamp(String targetTime) {
        try {
            long result = -1L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.parse(targetTime + " 00:00:00").getTime();
        } catch (ParseException e) {
            return 0L;
        }
    }

    public static long getOneDayEndTimeStamp(String targetTime) {
        try {
            long result = -1L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.parse(targetTime + " 23:59:59").getTime();
        } catch (ParseException e) {
            return 0L;
        }
    }

    public static Long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * Get the previous time, from how many days to now.
     *
     * @param days How many days.
     * @return The new previous time.
     */
    public static Date previous(int days) {
        return new Date(System.currentTimeMillis() - days * 3600000L * 24L);
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm:ss".
     */
    public static String formatDateTime(Date d) {
        return formatDate(d, DATETIME_FORMAT_SECOND);
    }

    public static String formatDateTimeNoSpace(Date d) {
        return formatDate(d, DATETIME_FORMAT_SECOND_NOSPACE);
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm".
     */
    public static String formatDateTimeNoSec(Date d) {
        return formatDate(d, DATETIME_FORMAT);
    }

    /**
     * Convert date and time to string like "yyyyMMddHH".
     *
     * @param d
     * @return
     */
    public static String formatDateTimeHH(Date d) {
        return formatDate(d, DATETIMEFORMAT_HH);
    }

    /**
     * Convert date and time to string like "yyyyMMdd".
     *
     * @param d
     * @return
     */
    public static String formatDateTimeDD(Date d) {
        return formatDate(d, DATETIMEFORMAT);
    }

    /**
     * Convert date and time to string like "yyyyMMddHHmmss".
     */
    public static String formatDateToString(Date d) {
        return formatDate(d, DATE_TIME_FORMAT);
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm".
     */
    public static String formatDateTime(long d) {
        return formatDate(d, DATETIME_FORMAT);
    }

    /**
     * Convert date to String like "yyyy-MM-dd".
     */
    public static String formatDate(Date d) {
        return formatDate(d, DATE_FORMAT);
    }

    /**
     * Convert date to String like "yyyy-MM".
     */
    public static String formatDateMonth(Date d) {
        return formatDate(d, DATE_FORMAT_MONTH);
    }

    /**
     * convert date to string like "yyyy/mm/dd hh:mm:ss"
     *
     * @param d
     * @return
     */
    public static String formateDateOblique(Date d) {
        return formatDate(d, DATETIME_FORMAT_SEC_OBLIQUE);
    }

    public static String formatDate(Date d, String dataFormat) {
        return new SimpleDateFormat(dataFormat).format(d);
    }

    public static String formatDate(long d, String dataFormat) {
        return new SimpleDateFormat(dataFormat).format(d);
    }

    /**
     * Parse date like "yyyy-MM-dd".
     */
    public static Date parseDate(String d) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(d);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Parse date like "yyyy-MM-dd" to "yyyy-MM-dd 00:00:00".
     */
    public static Date parseDateBegin(Date d) {
        try {
            String str_date = formatDate(d) + " 00:00:00";
            // return parseDateTime(str_date);
            return parseStringToDateHMS(str_date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Parse date like "yyyy-MM-dd" to "yyyy-MM-dd 23:59:59".
     */
    public static Date parseDateEnd(Date d) {
        try {
            String str_date = formatDate(d) + " 23:59:59";
            // return parseDateTime(str_date);
            return parseStringToDateHMS(str_date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm:ss".
     */
    public static String formatDateToStringHMS(Date d) {
        return new SimpleDateFormat(DATETIME_FORMAT_SECOND).format(d);
    }

    /**
     * Parse date like "yyyy-MM-dd" .
     *
     * @throws ParseException
     */
    public static Date paraseStringToDate(String d) throws ParseException {
        return parse(DATE_FORMAT, d);
    }

    /**
     * Parse date and time like "yyyy-MM-dd hh:mm"
     *
     * @throws ParseException
     */
    public static Date parseDateTime(String d) throws ParseException {
        return parse(DATETIME_FORMAT, d);
    }

    /**
     * Parse date like "yyyy-MM-dd HH:mm:ss" .
     *
     * @throws ParseException
     */
    public static Date parseStringToDateHMS(String d) throws ParseException {
        return parse(DATETIME_FORMAT_SECOND, d);
    }

    public static Date parseStringToDateTHMS(String d) throws ParseException {
        return parse(DATETIME_FORMAT_T_SECOND, d);
    }

    /**
     * parse date like "2015/10/10 15:05"
     *
     * @param d
     * @return
     * @throws ParseException
     */
    public static Date parseStringToOblique(String d) throws ParseException {
        return parse(DATETIME_FORMAT_MIN_OBLIQUE, d);
    }

    public static Date parse(String f, String d) throws ParseException {
        return new SimpleDateFormat(f).parse(d);
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm".
     */
    public static String formatDateTimePullRefresh(Date d) {
        return formatDate(d, DATETIME_FORMAT_PULL_REFRSH);
    }

    public static Date parseGMT(String d) throws ParseException {
        DateFormat format = new SimpleDateFormat(DATETIME_GMT_FORMAT, Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format.parse(d);
    }

    /**
     * 杩斿洖鎸囧畾鏃ュ織瀵瑰簲鐨勬槦鏈熶竴鏃堕棿 xxx-xx-xx 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getSpecMondayDate(Date date) {
        int mondayPlus = getMondayPlus();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = gc.getTime();
        return parseDateBegin(monday);
    }

    /**
     * 杩斿洖鏈懆鏄熸湡涓�鏃ユ湡鏃堕棿 xxx-xx-xx 00:00:00
     *
     * @return
     */
    public static Date getCurMondayDate() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar cur = new GregorianCalendar();
        cur.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = cur.getTime();
        return parseDateBegin(monday);
    }

    /**
     * 鎸夌収涓庡綋鍓嶆椂闂存湀宸弬鏁帮紝杩斿洖鏃堕棿锛屾牸寮� xxx-xx-xx 00:00:00
     *
     * @return
     */
    public static String getTimeByMonth(int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, months);
        return formatDate(calendar.getTime());
    }

    /**
     * 鎸夌収涓庡綋鍓嶆椂闂存湀宸弬鏁帮紝杩斿洖鏃堕棿锛屾牸寮� xxx-xx-xx 00:00:00
     *
     * @return
     */
    public static String getTimeByMonth(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return formatDate(calendar.getTime());
    }

    /**
     * 鎸夌収涓庡綋鍓嶆椂闂存湀宸弬鏁帮紝杩斿洖鏃堕棿锛屾牸寮� xxx-xx-xx 00:00:00
     *
     * @return
     */
    public static String getTimeByDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, days);
        return formatDate(calendar.getTime());
    }

    /**
     * 鑾峰彇鏈懆鏄熸湡涓�鐨勬棩鏈� yyyymmdd
     *
     * @return
     */
    public static String getCurMonday() {
        return formatDateTimeDD(getCurMondayDate());
    }

    /**
     * 鑾峰彇鎸囧畾鏃堕棿瀵瑰簲鎵�鍦ㄥ懆鍛ㄤ竴鐨勬棩鏈�
     *
     * @param date
     * @return 鍛ㄤ竴鏃ユ湡锛屾牸寮忎负yyyymmdd
     */
    public static String getMonday(Date date) {
        return formatDateTimeDD(getSpecMondayDate(date));
    }

    /**
     * 浼犲叆date鏄惁鏄槦鏈熶竴
     *
     * @param date
     * @return
     */
    public static boolean isMonday(Date date) {
        return getMondayPlus(date) == 0 ? true : false;
    }

    /**
     * 鑾峰彇褰撳墠骞�
     *
     * @param
     * @return
     */
    public static int getCurYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    /**
     * 鑾峰彇褰撳墠鏈�
     *
     * @param
     * @return
     */
    public static String getCurMonth() {
        Calendar now = Calendar.getInstance();
        String monthStr = now.get(Calendar.MONTH) + 1 + "";
        return monthStr.length() == 1 ? "0" + monthStr : monthStr;
    }

    /**
     * 褰撳墠鏃ヤ笌鏈懆鏄熸湡涓�鐩稿樊鐨勫ぉ鏁�
     *
     * @return
     */
    private static int getMondayPlus() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * 杩斿洖鎸囧畾鏃ユ湡涓庡叾鎵�鍦ㄥ懆鏄熸湡涓�鐩稿樊鐨勫ぉ鏁�
     *
     * @param date
     * @return
     */
    private static int getMondayPlus(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    public static Date yearAdd(Date time, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.YEAR, years);
        Date newTime = calendar.getTime();
        return newTime;
    }

    public static Date monthAdd(Date time, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MONTH, months);
        Date newTime = calendar.getTime();
        return newTime;
    }

    /**
     * 寰楀埌褰撳墠鏃堕棿鐨勫墠N涓湀鐨勬椂闂存埑
     *
     * @param count
     * @return
     */
    public static long getPreMonthTimestamp(int count) {
        Date date = new Date();
        // System.out.println((new
        // SimpleDateFormat("yyyy-MM-dd")).format(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -count * 30);
        // 鏍煎紡鍖栬緭鍑哄勾鏈堟棩
        // SimpleDateFormat sdf = new SimpleDateFormat("dyyyy-MM-dd 00:00:00");
        // System.out.println(sdf.format(cal.getTime()));
        cal.add(Calendar.DATE, -count * 30);
        // 鏍煎紡鍖栬緭鍑哄勾鏈堟棩
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        // System.out.println(sdf.format(cal.getTime()));
        cal.add(Calendar.DATE, -count * 30);
        // 鏍煎紡鍖栬緭鍑哄勾鏈堟棩
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        // System.out.println(sdf.format(cal.getTime()));
        return cal.getTimeInMillis();
    }

    /*
     * 灏嗘椂闂存埑杞崲涓烘椂闂�
     */
    public static String stampToDate(long time) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 灏嗘椂闂存埑杞崲涓烘椂闀�
     */
    public static String stampToHours(long time) {
        String result;
        float num = (float) time / (1000 * 3600);
        DecimalFormat df = new DecimalFormat("0.0");
        result = df.format(num);
        return result;
    }


    /**
     * 涓や釜鏃ユ湡闂寸殑澶╂暟
     *
     * @param start
     * @param end
     * @return
     */
    public static long getBetweenDays(Date start, Date end) {
        return (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 鎻愬墠鎴栨帹杩熸暟鏈堝悗鐨勬椂闂�
     *
     * @param date
     * @param step
     * @return
     */
    public static Date getFutureDate(Date date, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, step);
        Date futureDate = calendar.getTime();
        return futureDate;
    }

    /**
     * @Description:鎶婄杞负 灏忔椂锛氬垎閽燂細绉掔殑鏍煎紡
     * @Param锛歔time]
     * @return: java.lang.String
     **/
    public static String secToTime(int time) {


        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00:00";
        } else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99) {
                    return "99:59:59";
                }
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + Integer.toString(i);
        } else {
            retStr = "" + i;
        }
        return retStr;

    }
}
