package com.fangao.lib_common.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by qyf on 2016/3/28.
 */
public class TimeUtil {

    private static final String TAG = "TimeUtil";
    public static final int YMD = 0;
    public static final int HMS = 1;
    public static final int HM = 2;
    public static final int YMDHMS = 3;
    public static final int Y = 4;
    public static final int MDHM = 5;
    public static final String MD1 = "MM/dd";
    public static final String HMS1 = "HH:mm:ss";
    public static final String YMDS = "yyyy-MM-dd";
    public static final String YMDHM = "yyyy-MM-dd HH:mm";
    public static final String YMDHMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static Date sDate;


    /**
     * 10位时间戳转换为年月日，时分秒
     */
    public static String getYMD_HMS(String time, int State) {
        if (time.toCharArray().length == 10) {
            time = time + "000";
        }
        Date date = new Date(Long.parseLong(time));
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTime(date);
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) == 12 ? 1 : instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int minute = instance.get(Calendar.MINUTE);
        int second = instance.get(Calendar.SECOND);
        String return_time = null;
        switch (State) {
            case TimeUtil.YMD:
                return_time = year + "-" + month + "-" + day;
                break;
            case TimeUtil.HMS:
                return_time = hour + "-" + minute + "-" + second;
                break;
            case TimeUtil.HM:
                return_time = hour + ":" + minute;
                break;
            case TimeUtil.YMDHMS:
                break;
            case TimeUtil.Y:
                return_time = year + "";
                break;
            case TimeUtil.MDHM:
                return_time = month + "-" + day + " " + hour + ":" + minute;
                break;
        }
        return return_time;
    }

    /**
     * 根据日期获取周几
     *
     * @param date 4-27
     * @return 周
     */
    public static String getWeekByDate(String date) {

        String[] split = date.split("-");
        String monthStr = split[1].replace("'", "").trim();
        String month;
        if (monthStr.startsWith("0")) {
            month = monthStr.substring(1, monthStr.length());
        } else {
            month = monthStr;
        }
        String dayStr = split[2].replace("'", "").trim();
        String day;
        if (dayStr.startsWith("0")) {
            day = dayStr.substring(1, dayStr.length());
        } else {
            day = dayStr;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(Integer.parseInt(getYMD_HMS(System.currentTimeMillis() + "", TimeUtil.Y)), Integer.parseInt(month) - 1, Integer.parseInt(day));
        return weekDays[gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 判断是否超过指定分钟
     *
     * @param afterDateTime
     * @param beginDateTime
     * @return 0, 超过 1,大于之前的时间 但没有超过指定分钟 2，没有大于之前时间 3，时间格式不正确
     */
    public static int compareToDateTime(String afterDateTime,
                                        String beginDateTime, long durtion, String config) {
        SimpleDateFormat fmt = new SimpleDateFormat(config);
        try {
            Date date1 = fmt.parse(afterDateTime);
            Date date2 = fmt.parse(beginDateTime);
            if (date1.getTime() > date2.getTime()) {
                if (date1.getTime() - date2.getTime() >= durtion) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 3;
    }

    public static Boolean compareToDateTime(String start,
                                            String end, String config) {
        SimpleDateFormat fmt = new SimpleDateFormat(config, Locale.CHINA);
        try {
            Date date1 = fmt.parse(start);
            Date date2 = fmt.parse(end);
            return date1.getTime() >= date2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String getDateByMills(long millons) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd", Locale.CHINESE);
        return simpleDateFormat.format(new Date(millons));
    }

    public static long getTimeMillions(String time, String timeConfig) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(timeConfig);
        Date date;
        try {
            date = format.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //将星期几转化为周几
    public static String changeWeekFromXQtoZ(String week) {
        String result;
        switch (week) {
            case "星期一":
                result = weekDays[1];
                break;
            case "星期二":
                result = weekDays[2];
                break;
            case "星期三":
                result = weekDays[3];
                break;
            case "星期四":
                result = weekDays[4];
                break;
            case "星期五":
                result = weekDays[5];
                break;
            case "星期六":
                result = weekDays[6];
                break;
            case "星期日":
                result = weekDays[0];
                break;
            default:
                result = weekDays[1];
                break;
        }
        return result;
    }

    /**
     * 获取当天的0点的ms值
     */
    public static long getTodayZeroMillis() {
        Date date = new Date();
//        - 8 * 60 * 60 * 1000L
        long l = date.getTime() - date.getTime() % (24 * 60 * 60 * 1000L) - 8 * 60 * 60 * 1000L;
        return l;
    }


    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String parseDateMD(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtil.MD1);
        Date date = new Date(time);
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String parseDateHMS(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtil.HMS1);
        Date date = new Date(time);
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * 格式化时间  年月日
     *
     * @param time
     * @return
     */
    public static String parseDateYMD(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtil.YMDHMSS);
        Date date = new Date(time);
        String format = simpleDateFormat.format(date);
        return format;
    }


    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String parseDateYMDHMS(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        String format = simpleDateFormat.format(date);
        return format;
    }


    /**
     * 格式化时间
     *
     * @param time
     * @param config
     * @return
     */
    public static String parseDate(String time, String config) {
        if (time.length() == 10) {
            time = time + "000";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(config);
        Date date = new Date(Long.parseLong(time));
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static String parseDate(long time, String config) {
        if (String.valueOf(time).length() == 10) {
            time = Long.parseLong(String.valueOf(time) + "000");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(config);
        Date date = new Date(time);
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * 获取当前小时
     *
     * @return
     */
    public static int getNowHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hour = sdf.format(new Date());
        return Integer.parseInt(hour);
    }

    public static String hh(String timeMillis) {
        return Format("HH", getTimeMillis(timeMillis));
    }

    public static String md(String timeMillis) {
        return Format("MM月dd日", getTimeMillis(timeMillis));
    }

    private static String Format(String format, Date date) {
        if (date != null) {
            return new SimpleDateFormat(format).format(date);
        }
        return "";
    }


    private static Date getTimeMillis(String timeMillis) {
        try {
            return getTimeMillis(Long.parseLong(timeMillis));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Date getTimeMillis(long timeMillis) {
        try {
            switch (String.valueOf(timeMillis).length()) {
                case 13:
                    break;
                case 10:
                    timeMillis = timeMillis * 1000;
                    break;
                default:
                    timeMillis = System.currentTimeMillis();
                    break;
            }
            return new Date(timeMillis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getWeekList(String works) {
        List<String> weekList = new ArrayList<>();
        String[] workArray = works.split(",");
        for (String s : workArray) {
            switch (s) {
                case "0":
                    weekList.add("周日");
                    break;
                case "1":
                    weekList.add("周一");
                    break;
                case "2":
                    weekList.add("周二");
                    break;
                case "3":
                    weekList.add("周三");
                    break;
                case "4":
                    weekList.add("周四");
                    break;
                case "5":
                    weekList.add("周五");
                    break;
                case "6":
                    weekList.add("周六");
                    break;
                default:
                    weekList.add("");
                    break;
            }
        }
        return weekList;
    }
}
