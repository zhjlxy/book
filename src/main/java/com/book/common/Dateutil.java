package com.book.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lixuy on 2016/5/13.
 */
public class Dateutil {
    private final static String DEFAULT_FORMAT_STR="yyyy-MM-dd HH:ss:mm";

    /**
     *  将时间转为默认格式的字符串
     * @param date
     * @return
     */
    public static String DateToStr(Date date){
        return DateToStr(date, DEFAULT_FORMAT_STR);
    }

    /**
     * 根据给定的格式，格式化时间
     * @param date 要格式化的时间
     * @param formatStr 格式化的字符串
     * @return 格式化后的时间字符串
     */
    public static String DateToStr(Date date, String formatStr){
        DateFormat formate = new SimpleDateFormat(formatStr);
        return formate.format(date);
    }

}
