package com.framgia.android.common.util;
/**
 Copyright 2016 Framgia, Inc.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateTimeUtil
 * @author CONGNT
 */
public class DateTimeUtil {

    /**
     *
     * Date and Time Pattern Result "yyyy.MM.dd G 'at' HH:mm:ss z" 2001.07.04 AD
     * at 12:08:56 PDT "EEE, MMM d, ''yy" Wed, Jul 4, '01 "h:mm a" 12:08 PM
     * "hh 'o''clock' a, zzzz" 12 o'clock PM, Pacific Daylight Time "K:mm a, z"
     * 0:08 PM, PDT "yyyyy.MMMMM.dd GGG hh:mm aaa" 02001.July.04 AD 12:08 PM
     * "EEE, d MMM yyyy HH:mm:ss Z" Wed, 4 Jul 2001 12:08:56 -0700
     * "yyMMddHHmmssZ" 010704120856-0700 "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
     * 2001-07-04T12:08:56.235-0700
     */
    public static final String COMMON_DATE_FORMAT = "MM/dd/yyyy";
    public static final String COMMON_DATETIME_FORMAT = "MM/dd/yyyy hh:mm:ss a";
    public static final String VN_DATE_FORMAT = "dd/MM/yyyy";
    public static final String VN_DATETIME_FORMAT = "dd/MM/yyyy hh:mm:ss a";
    public static final String SHORT_DATE_FORMAT = "yyyyMMdd";
    public static final String ISO_8601_FORMAT= "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_FILE_SUFFIX = "yyyy-MM-dd_HH-mm-ss";
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_NOW = "HH:mm:ss";
    public static int SECOND = 1000;
    public static int MINUTE = 60 * SECOND;
    public static int HOUR = 60 * MINUTE;
    public static int DAY = 24 * HOUR;

    /***
     * @desc Convert String DateTime to Object java.util.Date
     * @param source
     * @param format - Format of String.
     * @return
    */
    public static java.util.Date stringToDate(String source, String format) {

        try {
            source = source.trim();
            if (format == null)
                format = VN_DATE_FORMAT;
            SimpleDateFormat ts = new SimpleDateFormat(format);
            return ts.parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @desc Get Next Day From Date.
     * @param date
     * @return - Next Date.
     */
    public static Date getNextDay(Date date) {
        if (date == null)
            date = new Date();
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(date.getTime() + 24 * 60 * 60 * 1000);
        return c1.getTime();

    }

    /**
     * @desc Convert Object java.util.Date to String follow Format
     * @param date
     * @param format - want to follow format
     * @return String Date Follow Format
     */
    public static String dateToString(Date date, String format) {
        if (format == null)
            format = VN_DATE_FORMAT;
        SimpleDateFormat ts = new SimpleDateFormat(format);
        return ts.format(date);
    }

    /**
     * @desc Change format of String date
     * @param source
     * @param currentFormat - Current format of String Date
     * @param newFormat - New format want to change
     * @return String Date follow new format
     */
    public static String convertFormat(String source, String currentFormat, String newFormat) {
        Date date = stringToDate(source, currentFormat);
        return dateToString(date, newFormat);
    }

    /**
     * @desc Compare 2 date same or not
     * @param date1
     * @param date2
     * @return return 0 if same, and other number if not same
     */
    public static int compare(Date date1, Date date2) {
        String d1 = dateToString(date1, SHORT_DATE_FORMAT);
        String d2 = dateToString(date2, SHORT_DATE_FORMAT);
        return d1.compareTo(d2);
    }

    /**
     * @desc Create Sample File name have date time follow format yyyy-MM-dd_HH-mm-ss
     * @return File name
     */
    public static String getTimeFileSuffix() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FILE_SUFFIX);
        return sdf.format(cal.getTime());
    }
    /**
     * @desc Get Date time at the moment follow format yyyy-MM-dd HH:mm:ss
     * @return String Date as format yyyy-MM-dd HH:mm:ss
     */
    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    /**
     * @desc Get Time at the moment follow format HH:mm:ss
     * @return String Time as format HH:mm:ss
     */
    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_NOW);
        return sdf.format(cal.getTime());

    }

    /**
     * return linux epoch (in seconds)
     *
     * @return
     */
    public static int getEpoch() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    /**
     *
     * @param duration
     *            - duration in seconds
     * @return formated duration
     */
    public static String durationToString(int duration) {
        String s = "";
        int hour = duration / 3600;
        int min = (duration % 3600) / 60;
        int second = duration % 60;

        if (hour > 0) {
            s = Integer.toString(hour) + ":";
            if (hour < 10)
                s = "0" + s;
        }

        s += (min > 9) ? Integer.toString(min) + ":" : "0"
            + Integer.toString(min) + ":";
        s += (second > 9) ? Integer.toString(second) : "0"
            + Integer.toString(second);

        return s;
    }
}
