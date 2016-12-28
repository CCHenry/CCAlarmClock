package com.cc.zzf.ccalarmclock.utils;

import android.util.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class DateUtil {

   public static final String SIMPLE_FORMAT_STR = "yyyy-MM-dd";

   public static final String FULL_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

   public static final String FULL_FORMAT_TIME = "yyyy-MM-dd HH:mm";

   public static final String FORMAT_1 = "yyyy-MM-dd";

   public static final String FORMAT_2 = "MM-dd-yy";

   public static final String FORMAT_3 = "MM/dd/yy";

   public static final String FORMAT_4 = "MM/dd/yyyy";

   public static final String FORMAT_5 = "dd-MM-yy";

   public static final String FORMAT_6 = "dd/MM/yy";

   public static final String FORMAT_7 = "dd/MM/yyyy";

   public static final String FORMAT_8 = "HH:mm";

   public static final String SQL_FORMATE = "yyyy-MM-dd";

   public static final String SQL_FULL_FORMATE = "yyyy-MM-dd HH:mm:ss.SSS";

   public static final String SQL_MINI_FORMATE = "yyyy-MM-dd HH:mm";

   public static final int MICROSECONDS_OF_HOUR = 3600000;

   public static final int MICROSECONDS_OF_DAY = MICROSECONDS_OF_HOUR * 24;

   public static final int DURATION_DAY = 24 * 60;

   public static final int DURATION_HOUR = 60;

   private static final SimpleDateFormat SDF = new SimpleDateFormat(SIMPLE_FORMAT_STR);

   private static final SimpleDateFormat FSDF = new SimpleDateFormat(FULL_FORMAT_STR);

   private static final SimpleDateFormat SQLFSDF = new SimpleDateFormat(SQL_FULL_FORMATE);


   public static String fullFormat(Date date) {
      return FSDF.format(date);
   }

   public static String sqlFullFormat(Date date) {
      return SQLFSDF.format(date);
   }

   private static final List<String> weekStrs_en = new ArrayList<String>();

   private static final List<String> weekStrs_cn = new ArrayList<String>();

   static {

      weekStrs_en.add("Su");
      weekStrs_en.add("M");
      weekStrs_en.add("Tu");
      weekStrs_en.add("W");
      weekStrs_en.add("Th");
      weekStrs_en.add("F");
      weekStrs_en.add("Sa");

      weekStrs_cn.add("日");
      weekStrs_cn.add("一");
      weekStrs_cn.add("二");
      weekStrs_cn.add("三");
      weekStrs_cn.add("四");
      weekStrs_cn.add("五");
      weekStrs_cn.add("六");
   }

   /**
    * DOCUMENT ME!
    * 
    * @param weekNumber
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static int getWeekDay(int weekNumber) {
      switch (weekNumber) {
      case Calendar.MONDAY:
         return 1;

      case Calendar.TUESDAY:
         return 2;

      case Calendar.WEDNESDAY:
         return 3;

      case Calendar.THURSDAY:
         return 4;

      case Calendar.FRIDAY:
         return 5;

      case Calendar.SATURDAY:
         return 6;

      case Calendar.SUNDAY:
         return 0;

      default:
         return -1;
      }
   }

   /**
    * DOCUMENT ME!
    * 
    * @param date
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static int getWeekDay(Date date) {
      if (date == null) {
         return -1;
      }

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);

      return getWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
   }

   public static String getWeekDayStr(Date date, String weekLanguage) {
      if (date == null || weekLanguage == null || "".equals(weekLanguage)) {
         return "";
      }

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);

      return getWeekDayStr(calendar.get(Calendar.DAY_OF_WEEK), weekLanguage);
   }

   public static String getWeekDayStr(int weekNumber, String weekLanguage) {

      if (weekNumber < 1) {
         return "";
      }


      if ("EN".contains(weekLanguage)) {
         return weekStrs_en.get(weekNumber - 1); // 由于星期天是为1，所以要-1
      } else {
         return weekStrs_cn.get(weekNumber - 1);
      }
   }
   


   /**
    * DOCUMENT ME!
    * 
    * @param date
    *           DOCUMENT ME!
    * @param field
    *           DOCUMENT ME!
    * @param amount
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date add(Date date, int field, int amount) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(field, amount);
      return cal.getTime();
   }

   /**
    * 获取以fromDay为起始时间的一周时间 如：当前日期为2008年11月30日，fromDay为1 则返回时间段为： 2008年11月24日 -
    * 2008年11月30日
    * 
    * @param currentDate
    * @param fromDay
    *           周的起始时间为，0 表示周日 1 表示周一 ...
    * @return
    */
   public static Date[] getWeekPeriod(Date currentDate, int fromDay) {
      Date fromDate = currentDate;
      Date toDate = currentDate;

      int dayOfWeek = getWeekDay(currentDate);
      dayOfWeek = dayOfWeek - fromDay;

      if (dayOfWeek < 0) {
         dayOfWeek = 7 - fromDay;
      }

      int seg = -1 * dayOfWeek;

      fromDate = add(currentDate, Calendar.DATE, seg);
      toDate = add(fromDate, Calendar.DATE, 6);

      Date[] period = new Date[2];
      period[0] = fromDate;
      period[1] = toDate;
      return period;
   }

   public static Date parse(String dateStr, String format) {
      if (StringUtil.isEmpty1(dateStr)) {
         return null;
      }
      return stringToDate(dateStr, format);
   }
   
   public static Date stringToDate(String dateStr, String formatStr) {
      formatStr = StringUtil.isEmpty(formatStr) ? SIMPLE_FORMAT_STR : formatStr;
      if (formatStr.indexOf("-") != -1) {
         //dateStr = dateStr.replaceAll("[ -]", "");
      }
      if (formatStr.indexOf("/") != -1) {
         //dateStr = dateStr.replaceAll("[ /]", "");
      }
      // if (dateStr != '' && isDate(dateStr)) {
      String[] dataArray = getDateNumber(dateStr, formatStr); // dateStr.substr(0,
      // 10).split("-");
      if (dataArray != null) {
         Calendar cal = Calendar.getInstance();
         cal.set(StringUtil.convertStrToInteger(dataArray[0]), StringUtil.convertStrToInteger(dataArray[1]) - 1,
            StringUtil.convertStrToInteger(dataArray[2]), StringUtil.convertStrToInteger(dataArray[3]), StringUtil
               .convertStrToInteger(dataArray[4]), StringUtil.convertStrToInteger(dataArray[5]));
         cal.set(Calendar.MILLISECOND, 0);
         return cal.getTime();
      }
      return null;
   }

   public static String[] getDateNumber(String inPara, String formatStr) {
      String[] dateList = getDateNumberList(inPara, formatStr);
      if (StringUtil.isEmpty(dateList)) {
         return null;
      }

      int year = DateUtil.getYear(new Date(System.currentTimeMillis()));
      if (dateList[0].length() < 4) {
         dateList[0] = "" + StringUtil.toString(year).substring(0, 4 - dateList[0].length()) + dateList[0];
      }

      if (checkDateNumber(dateList)) {
         return dateList;
      } else {
         if (dateList[0] != "" && dateList[1] != "" && dateList[2] != "") {
            return null;
         }
      }
      dateList = getDateNumberList(inPara, formatStr.replaceAll("yyyy", "yy"));
      if (dateList == null) {
         return null;
      }
      if (dateList[0].length() < 4) {
         dateList[0] = "" + StringUtil.toString(year).substring(0, 4 - dateList[0].length()) + dateList[0];
      }
      // alert(dateList.join("-"))
      if (checkDateNumber(dateList)) {
         return dateList;
      } else {
         if (dateList[0] != "" && dateList[1] != "" && dateList[2] != "") {
            return null;
         }
      }
      // alert(dateList.join("-"))
      dateList = getDateNumberList(inPara, formatStr.replace("yy", ""));
      if (dateList == null) {
         return null;
      }
      if (dateList[0].length() < 4) {
         dateList[0] = "" + StringUtil.toString(year).substring(0, 4 - dateList[0].length()) + dateList[0];
      }
      // alert(dateList.join("-"))
      if (checkDateNumber(dateList)) {
         return dateList;
      } else {
         if (dateList[0] != "" && dateList[1] != "" && dateList[2] != "") {
            return null;
         }
      }
      return null;
   }

   private static String[] getDateNumberList(String inPara, String currDateFormatStr) {
      String[] dateList = new String[]{"", "", "", "", "", ""};
      int numIndex = 0;
      String currChar = null;
      String dateSplitStr = FULL_FORMAT_STR.replaceAll("[yMd ]", "");
      for (int i = 0; i < currDateFormatStr.length(); i++) {
         if (currDateFormatStr.charAt(i) == 'y') {
            for (; numIndex < inPara.length();) {
               currChar = "" + inPara.charAt(numIndex++);
               if (StringUtil.toInt(currChar, -1) >= 0 && StringUtil.toInt(currChar, -1) <= 9) {
                  dateList[0] += currChar;
                  break;
               } else if (dateSplitStr.indexOf(currChar) > -1) {
                  if (i > 0 && currDateFormatStr.charAt(i - 1) == currDateFormatStr.charAt(i)) {
                     numIndex--;
                     break;
                  } else {
                     continue;
                  }
               } else {
                  continue;
               }
            }
         } else if (currDateFormatStr.charAt(i) == 'M') {
            for (; numIndex < inPara.length();) {
               currChar = "" + inPara.charAt(numIndex++);

               if (StringUtil.toInt(currChar, -1) >= 0 && StringUtil.toInt(currChar, -1) <= 9) {
                  dateList[1] += currChar;
                  break;
               } else if (dateSplitStr.indexOf(currChar) > -1) {
                  if (i > 0 && currDateFormatStr.charAt(i - 1) == currDateFormatStr.charAt(i)) {
                     numIndex--;
                     break;
                  } else {
                     continue;
                  }
               } else {
                  continue;
               }
            }
         } else if (currDateFormatStr.charAt(i) == 'd') {
            for (; numIndex < inPara.length();) {
               currChar = "" + inPara.charAt(numIndex++);

               if (StringUtil.toInt(currChar, -1) >= 0 && StringUtil.toInt(currChar, -1) <= 9) {
                  dateList[2] += currChar;
                  break;
               } else if (dateSplitStr.indexOf(currChar) > -1) {
                  if (i > 0 && currDateFormatStr.charAt(i - 1) == currDateFormatStr.charAt(i)) {
                     numIndex--;
                     break;
                  } else {
                     continue;
                  }
               } else {
                  continue;
               }
            }
         } else if (currDateFormatStr.charAt(i) == 'H') {
            for (; numIndex < inPara.length();) {
               currChar = "" + inPara.charAt(numIndex++);

               if (StringUtil.toInt(currChar, -1) >= 0 && StringUtil.toInt(currChar, -1) <= 9) {
                  dateList[3] += currChar;
                  break;
               } else if (dateSplitStr.indexOf(currChar) > -1) {
                  if (i > 0 && currDateFormatStr.charAt(i - 1) == currDateFormatStr.charAt(i)) {
                     numIndex--;
                     break;
                  } else {
                     continue;
                  }
               } else {
                  continue;
               }
            }
         } else if (currDateFormatStr.charAt(i) == 'm') {
            for (; numIndex < inPara.length();) {
               currChar = "" + inPara.charAt(numIndex++);

               if (StringUtil.toInt(currChar, -1) >= 0 && StringUtil.toInt(currChar, -1) <= 9) {
                  dateList[4] += currChar;
                  break;
               } else if (dateSplitStr.indexOf(currChar) > -1) {
                  if (i > 0 && currDateFormatStr.charAt(i - 1) == currDateFormatStr.charAt(i)) {
                     numIndex--;
                     break;
                  } else {
                     continue;
                  }
               } else {
                  continue;
               }
            }
         } else if (currDateFormatStr.charAt(i) == 's') {
            for (; numIndex < inPara.length();) {
               currChar = "" + inPara.charAt(numIndex++);

               if (StringUtil.toInt(currChar, -1) >= 0 && StringUtil.toInt(currChar, -1) <= 9) {
                  dateList[5] += currChar;
                  break;
               } else if (dateSplitStr.indexOf(currChar) > -1) {
                  if (i > 0 && currDateFormatStr.charAt(i - 1) == currDateFormatStr.charAt(i)) {
                     numIndex--;
                     break;
                  } else {
                     continue;
                  }
               } else {
                  continue;
               }
            }
         }
      }
      return dateList;
   }

   private static boolean checkDateNumber(String[] dateList) {
      if (dateList != null && dateList.length < 3) {
         return false;
      }

      Integer year = StringUtil.toInteger(dateList[0], null);
      Integer month = StringUtil.toInteger(dateList[1], null);
      Integer day = StringUtil.toInteger(dateList[2], null);
      int hour = StringUtil.convertStrToInteger(dateList[3]);
      int minute = StringUtil.convertStrToInteger(dateList[4]);
      int second = StringUtil.convertStrToInteger(dateList[5]);
      
      if (year == null || month == null || day == null) {
         return false;
      }
      
      if (!isYear(year) || !isMonth(month) || !isDay(year, month, day) || !isHour(hour) || !isMinute(minute) ||
         !isSecond(second)) {
         return false;
      }
      return true;
   }

   public static boolean isYear(int year) {
      // return false if year not between 1753 and 3000
      if (year < 1753 || year > 3000) {
         return false;
      }
      return true;
   }

   public static boolean isMonth(int month) {
      if (month < 1 || month > 12) {
         return false;
      }
      return true;
   }

   public static boolean isDay(int year, int month, int day) {
      switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
         // return false if the day is illegality in the month
         if (day < 1 || day > 31) {
            return false;
         }
         break;
      case 4:
      case 6:
      case 9:
      case 11:
         // return false if the day is illegality in the month
         if (day < 1 || day > 30) {
            return false;
         }
         break;
      case 2:
         // test if leap year
         if (isLeapYear(year)) {
            if (day > 29 || day < 1) {
               return false;
            }
         } else {
            if (day > 28 || day < 1) {
               return false;
            }
         }
         break;
      default:
         return false;
      }
      return true;
   }

   public static boolean isHour(int hour) {
      if (hour < 0 || hour > 24) {
         return false;
      }
      return true;
   }

   public static boolean isMinute(int minute) {
      if (minute < 0 || minute > 59) {
         return false;
      }
      return true;
   }

   public static boolean isSecond(int second) {
      if (second < 0 || second > 59) {
         return false;
      }
      return true;
   }

   public static boolean isLeapYear(int year) {
      if (year % 100 == 0) {
         if (year % 400 == 0) {
            return true;
         }
      } else {
         if ((year % 4) == 0) {
            return true;
         }
      }

      return false;
   }

   public static int getYear(Date date) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      return cal.get(Calendar.YEAR);
   }

   public static int getMonth(Date date) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      return cal.get(Calendar.MONTH);
   }

   public static int getDate(Date date) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      return cal.get(Calendar.DATE);
   }

   public static int getHour(Date date) {
      if (date == null) {
         return 0;
      }
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      return cal.get(Calendar.HOUR_OF_DAY);
   }

   public static int getMinute(Date date) {
      if (date == null) {
         return 0;
      }
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      return cal.get(Calendar.MINUTE);
   }

   public static int getSecond(Date date) {
      if (date == null) {
         return 0;
      }
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      return cal.get(Calendar.SECOND);
   }


   
   public static Date getCurrentTimestamp() {
      Calendar cal = Calendar.getInstance();
      return cal.getTime();
   }
   
   public static String getCurrentTimeStr() {
      Calendar cal = Calendar.getInstance();
      return sqlFullFormat(cal.getTime());
   }

   public static String getCurrentTimeStr2() {
      Calendar cal = Calendar.getInstance();
      return fullFormat(cal.getTime());
   }

   public static int getDateFieldValue(Date date, int field) {
      Calendar cl = Calendar.getInstance();
      cl.setTime(date);
      return cl.get(field);
   }

   public static Date getDateTimeFromString(String dateStr) {
      Date strDate = null;
      if (!StringUtil.isEmpty1(dateStr)) {
         try {
            strDate = DateUtil.parse(dateStr, DateUtil.FULL_FORMAT_TIME);
         } catch (Exception e) {
            Log.e("DateUtil", e.getMessage());
         }
      }
      return strDate;
   }

   public static Date getDateFromString(String dateStr) {
      return getDateFromString(dateStr, DateUtil.SIMPLE_FORMAT_STR);
   }

   public static Date getDateFromString(String dateStr, String format) {
      Date strDate = null;
      if (!StringUtil.isEmpty1(dateStr)) {
         try {
            strDate = DateUtil.parse(dateStr, format);
         } catch (Exception e) {
            Log.e("DateUtil", e.getMessage());
         }
      }
      return strDate;
   }
   

   
   /**
    * 返回客户端日期显示格式
    * @return
    */
   public static String getSystemDateFormat(boolean isShowFullDate) {
      String systemDateFormat = "";
      if ((!StringUtil.isEmpty1(systemDateFormat)) && isShowFullDate) {
         systemDateFormat = systemDateFormat.concat(" ").concat(FORMAT_8);
      }
      return systemDateFormat;
   }
   
   /**
    * 判断时分秒是不是0
    * @param date
    * @return
    */
   public static boolean isEmptyTime(Date date) {
      if (getHour(date) == 0 && getMinute(date) == 0 && getSecond(date) == 0) {
         return true;
      }
      return false;

   }


   
  

   

   



   



   public static Date getTimeFromString(String dateStr) {
      Date strDate = null;
      if (!StringUtil.isEmpty1(dateStr)) {

         try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_8);
            strDate = sdf.parse(dateStr);
         } catch (Exception e) {
            strDate = null;
            e.printStackTrace();
         }
         try {
            if (strDate == null) {
               strDate = DateUtil.parse(dateStr, DateUtil.SQL_MINI_FORMATE);
            }

         } catch (Exception e) {
            Log.e("DateUtil", e.getMessage());
         }
      }
      return strDate;
   }



   /**
    * 删除微秒
    * 
    * @param string
    * @return
    */
   public static String deleteMicrosecond(String string) {
      if (string == null) {
         return null;
      }
      if (string.indexOf(".") > 0) {
         return string.substring(0, string.indexOf("."));
      }
      return null;
   }

   // public static boolean isFirstDateBiggerThansecondDate(String string) {
   // if (string == null || "".equals(string)) {
   // return false;
   // }
   // if (string.indexOf("~") > 0) {
   // }

   // 时间对比 if startDate > endDate? return true;
   public static boolean compareDate(Date startDate, Date endDate) {
      if (startDate != null && endDate != null)
         return startDate.after(endDate);
      return false;
   }

   /**
    * 计算服务器时间与本地时间的差值 差值 = 服务器时间 - 本地时间
    * 
    * @param serverTimeStr
    * @return
    */
   public static long differByServerAndLocalTime(String serverTimeStr) {
      Date serverTime = getDateFromString(serverTimeStr, FULL_FORMAT_STR);
      return serverTime.getTime() - Calendar.getInstance().getTimeInMillis();
   }
   
   public static long compareTo(Date date1, Date date2) {
      return compareTo(date1, date2, Calendar.DATE);
   }

   public static long compareTo(Date date1, Date date2, String durationUnit) {
      return compareTo(date1, date2, "m".equals(durationUnit) ? Calendar.MINUTE : Calendar.DATE);
   }

   public static long compareTo(Date date1, Date date2, int field) {
      if (date1 == null) {
         return Integer.MIN_VALUE;
      } else if (date2 == null) {
         return Integer.MAX_VALUE;
      } else if (date1 == date2 || date1.equals(date2)) {
         return 0;
      } else {
         if (field == Calendar.HOUR || field == Calendar.HOUR_OF_DAY) {
            // return date1.getTime() / MICROSECONDS_OF_HOUR - date2.getTime() /
            // MICROSECONDS_OF_HOUR;
            return getTimeDelta(trimDate(date1, Calendar.HOUR_OF_DAY), trimDate(date2, Calendar.HOUR_OF_DAY)) /
               MICROSECONDS_OF_HOUR;
         } else if (field == Calendar.DATE) {
            // Calendar cal1 = Calendar.getInstance();
            // cal1.setTimeInMillis(date1.getTime());
            // Calendar cal2 = Calendar.getInstance();
            // cal2.setTimeInMillis(date2.getTime());
            // return cal1.getTimeInMillis() / MICROSECONDS_OF_DAY -
            // cal2.getTimeInMillis() / MICROSECONDS_OF_DAY;
            // return date1.getTime() / MICROSECONDS_OF_DAY - date2.getTime() /
            // MICROSECONDS_OF_DAY;
            return getTimeDelta(trimDate(date1), trimDate(date2)) / MICROSECONDS_OF_DAY;
         } else if (field == Calendar.MONTH) {
            Calendar fromCal = Calendar.getInstance();
            Calendar toCal = Calendar.getInstance();
            if (date1.before(date2)) {
               fromCal.setTime(date1);
               toCal.setTime(date2);
            } else {
               fromCal.setTime(date2);
               toCal.setTime(date1);
            }
            int fromYear = fromCal.get(Calendar.YEAR);
            int fromMonth = fromCal.get(Calendar.MONTH);
            int toYear = toCal.get(Calendar.YEAR);
            int toMonth = toCal.get(Calendar.MONTH);

            long months = (toYear - fromYear + 1) * 12 - fromMonth - (12 - toMonth);
            if (date1.before(date2)) {
               return -months;
            } else {
               return months;
            }
         } else if (field == Calendar.YEAR) {
            return getDateFieldValue(date1, Calendar.YEAR) - getDateFieldValue(date2, Calendar.YEAR);
            // return (trimDate(date1, Calendar.YEAR).getTime() -
            // trimDate(date2, Calendar.YEAR).getTime()) / MICROSECONDS_OF_YEAR;
         } else if (field == Calendar.MINUTE) {
            return getTimeDelta(date1, date2) / (1000 * 60);
         } else if (field == Calendar.DAY_OF_WEEK) {
            date1 = DateUtil.getNextDate(date1, Calendar.DATE,
               1 - DateUtil.getDateFieldValue(date1, Calendar.DAY_OF_WEEK));
            date2 = DateUtil.getNextDate(date2, Calendar.DATE,
               1 - DateUtil.getDateFieldValue(date2, Calendar.DAY_OF_WEEK));
            return getTimeDelta(trimDate(date1), trimDate(date2)) / (MICROSECONDS_OF_DAY * 7);
         } else if (field == Calendar.DAY_OF_YEAR) {
            // date1 = DateUtil.getNextDate(date1, Calendar.DATE, 1 -
            // DateUtil.getDateFieldValue(date1, Calendar.DAY_OF_MONTH));
            // date2 = DateUtil.getNextDate(date2, Calendar.DATE, 1 -
            // DateUtil.getDateFieldValue(date2, Calendar.DAY_OF_MONTH));
            return date1.getYear() - date2.getYear();
         } else {
            return date1.compareTo(date2);
         }
      }
   }

   public static Date trimDate(Date date) {
      return trimDate(date, Calendar.DATE);
   }

   public static Date trimDate(Date date, int type) {
      if (date == null) {
         return null;
      }
      Calendar cal = Calendar.getInstance();
      cal.setTimeInMillis(date.getTime());
      switch (type) {
      case Calendar.YEAR:
         cal.set(Calendar.MONTH, 0);
      case Calendar.MONTH:
         cal.set(Calendar.DATE, 0);
      case Calendar.DATE:
         cal.set(Calendar.HOUR_OF_DAY, 0);
      case Calendar.HOUR_OF_DAY:
         break;
      case Calendar.HOUR:
         break;
      case Calendar.MINUTE:
         break;
      default:
         cal.set(Calendar.HOUR_OF_DAY, 0);
      }

      if (Calendar.MINUTE != type) {
         cal.set(Calendar.MINUTE, 0);
      }
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);

      return new Timestamp(cal.getTimeInMillis());
   }

   public static Date getNextDate(Date date, int type, int duration) {
      if (date == null) {
         return null;
      }

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(type, duration);

      return new Timestamp(calendar.getTimeInMillis());
   }

   public static long getTimeDelta(Date date1, Date date2) {
      // Fix dst date diff problem.
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date1);

      Calendar calendar2 = Calendar.getInstance();
      calendar2.setTime(date2);

      long date1Time = calendar.getTimeInMillis() + calendar.getTimeZone().getOffset(calendar.getTimeInMillis());
      long date2Time = calendar2.getTimeInMillis() + calendar2.getTimeZone().getOffset(calendar2.getTimeInMillis());

      long offset = date1Time - date2Time;
      return offset;
   }
   
   /**
    * 将日期显示成 ：YYYY-MM-DD的格式
    * @param year
    * @param month 若要显示2月，该参数传1
    * @param day
    * @param defaultStr 默认值
    * @return
    */
   public static String parseToDateStr(int year, int month, int day, String defaultStr) {
	   StringBuilder resultStr = new StringBuilder();
	   if (year != -1) {
		   resultStr.append(year).append("-").append((month + 1) < 10 ? "0" + (month + 1) : (month + 1)).append("-")
	         .append((day < 10) ? "0" + day : day);
	   } else {
		   resultStr.append(defaultStr);
	   }
	   return resultStr.toString();
   }
   
   /**
    * 将时间显示成：HH:MM格式
    * @param hour
    * @param minute
    * @param defaultStr 默认值
    * @return
    */
   public static String parseToTimeStr(int hour, int minute, String defaultStr) {
	   StringBuilder resultStr = new StringBuilder();
	   if (hour != -1) {
		   resultStr.append(hour < 10 ? "0" + hour : hour).append(":").append((minute < 10) ? "0" + minute : minute);
	   } else {
		   resultStr.append(defaultStr);
	   }
	   return resultStr.toString();
   }
   
   public static int getYear(int year) {
      Calendar cal = Calendar.getInstance();
      return year == -1 ? cal.get(Calendar.YEAR) : year;
   }
   
   public static int getMonth(int month) {
      Calendar cal = Calendar.getInstance();
      return month == -1 ? cal.get(Calendar.MONTH) : month;
   }
   
   public static int getDay(int day) {
      Calendar cal = Calendar.getInstance();
      return day == -1 ? cal.get(Calendar.DAY_OF_MONTH) : day;
   }
   
   public static int getHour(int hour) {
      Calendar cal = Calendar.getInstance();
      return hour == -1 ? cal.get(Calendar.HOUR_OF_DAY) : hour;
   }
   
   public static int getMinute(int minute) {
      Calendar cal = Calendar.getInstance();
      return minute == -1 ? cal.get(Calendar.MINUTE) : minute;
   }
   
   /**
    * 比较日期大小，只到天
    * @param date1
    * @param date2
    * @return
    */
   public static boolean isDateBefore(Date date1, Date date2) {
      if (date1 == null || date2 == null) {
         return false;
      }
      Calendar cal1 = Calendar.getInstance();
      cal1.setTime(date1);

      Calendar cal2 = Calendar.getInstance();
      cal2.setTime(date2);
      int year1 = cal1.get(Calendar.YEAR);
      int month1 = cal1.get(Calendar.MONTH);
      int day1 = cal1.get(Calendar.DAY_OF_MONTH);

      int year2 = cal2.get(Calendar.YEAR);
      int month2 = cal2.get(Calendar.MONTH);
      int day2 = cal2.get(Calendar.DAY_OF_MONTH);
      if (year1 < year2 || (year1 == year2 && month1 < month2) || (year1 == year2 && month1 == month2 && day1 < day2)) {
         return true;
      }
      return false;
   }
   
   /**
    * 比较日期是否是同一天
    * @param date1
    * @param date2
    * @return
    */
   public static boolean isDateEquals(Date date1, Date date2) {
      if (date1 == null || date2 == null) {
         return false;
      }
      Calendar cal1 = Calendar.getInstance();
      cal1.setTime(date1);

      Calendar cal2 = Calendar.getInstance();
      cal2.setTime(date2);

      return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
         cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
         cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
   }
   
   /**
    * 比较日期所在日期区间之间
    * @param date
    * @param fromDate
    * @param toDate
    * @return
    */
   public static boolean isDateIn(Date date, Date fromDate, Date toDate) {
      return compareTo(date, fromDate) >= 0 && compareTo(date, toDate) <= 0;
   }
   
   /**
    * 将格式为SQL_FORMATE的字符串转成日期
    * @param sqlDate
    * @return
    */
   public static Date parseSqlDate(String sqlDate) {
      return parseSqlDate(sqlDate, false);
   }

   /**
    * 将格式为SQL_FULL_FORMATE的字符串转成日期
    * @param sqlDate 
    * @return
    */
   public static Date parseSqlDate(String sqlDate, boolean fullFormat) {
      if (fullFormat) {
         return parse(sqlDate, SQL_FULL_FORMATE);
      } else {
         return parse(sqlDate, SQL_FORMATE);
      }
   }

}
