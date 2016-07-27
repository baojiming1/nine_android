package com.tp.venus.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-8-24
 */
public class TimeUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DATE    = "yyyy-MM-dd";

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     * 
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    public static String dtFormat(Date date, String dateFormat){
        return getFormat(dateFormat).format(date);
    }

    private static  SimpleDateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     * 
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, getDefaultDateFormat());
    }

    private static SimpleDateFormat getDefaultDateFormat(){
        return getFormat(DEFAULT_DATE_FORMAT);
    }


    /**
     * 获取友好的时间格式提示
     * @param timeInMillis
     * @return
     */
    public static String getFriendlyTime(long timeInMillis){
        //TODO 没有做
        /**
         *  if (是今年) {
         if (是今天) {
         if (大于1小时) {
         3小时以前
         }else if (大于1分钟){
         3分钟以前
         }else{
         刚刚
         }
         }else if (是昨天){
         昨天 18:51
         }else{  是前天以前
         12-07 18:51
         }


         } else {
         2015-12-07
         }
         *
         *
         */

        return getTime(timeInMillis);
    }

    /**
     * get current time in milliseconds
     * 
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     * 
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     * 
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }
}
