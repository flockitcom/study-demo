package date.LocalDateTime;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtil {

    /**
     * 输出指定日期的格式(null为默认格式)
     *
     * @param time 时间戳
     * @param type 格式,默认格式yyyy-MM-dd HH:mm:ss
     */
    public static String getStringByLong(Long time, String type) {
        if (StringUtils.isBlank(type)) {
            type = "yyyy-MM-dd HH:mm:ss";
        }
        LocalDateTime localDateTime = Instant.ofEpochMilli(time).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern(type));
    }

    /**
     * 将时间的字符串转为date (null为默认格式)
     *
     * @param date 时间字符串
     * @param type 转换格式 默认格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date getDateByString(String date, String type) {
        if (StringUtils.isBlank(type)) {
            type = "yyyy-MM-dd HH:mm:ss";
        }
        LocalDateTime curTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(type));
        return Date.from(curTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将Date 按格式 转为字符串 (null使用默认格式)
     * @param date
     * @param type 默认格式为 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getStringByDate(Date date, String type) {
        if (StringUtils.isBlank(type)) {
            type = "yyyy-MM-dd HH:mm:ss";
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(type));
    }

    /**
     * 获取指定日期之前或之后的日期,并按格式返回
     *
     * @param date
     * @param dateNum 时间数量  之前为负数,之后为正数
     * @param unit    时间单位 请使用java.util.Calendar
     *                ,Calendar.MONTH表示月
     *                ,Calendar.DAY_OF_MONTH表示日
     *                ,如Calendar.HOUR_OF_DAY表示小时
     *                ,如Calendar.MINUTE表示分
     *                ,如Calendar.SECOND表示秒
     * @param type    返回的格式,null为默认格式
     */
    public static Date getAroundDateByUint(Date date, int dateNum, ChronoUnit unit, String type) {
        LocalDateTime time = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        time = time.plus(dateNum, unit);
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());

    }

    /**
     * 获取所传时间的指定日期(不传为null)
     *
     * @param date   时间
     * @param month  月(0-11)
     * @param day    日(0-30)
     * @param hour   时(0-23)
     * @param minute 分(0-59)
     * @param second 秒(0-59)
     */
    public static long getDateLongByTime(Date date, Integer month, Integer day, Integer hour, Integer minute, Integer second){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (month != null && month >= 0 && month <= 11) {
                calendar.set(Calendar.MONTH, month);
            }
            if (day != null && day >= 0 && day <= 30) {
                calendar.set(Calendar.DAY_OF_MONTH, day);
            }
            if (hour != null && hour >= 0 && hour <= 23) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
            }
            if (minute != null && minute >= 0 && minute <= 59) {
                calendar.set(Calendar.MINUTE, minute);
            }
            if (second != null && second >= 0 && second < 59) {
                calendar.set(Calendar.SECOND, second);
            }
            return calendar.getTime().getTime();
        } catch (Exception e) {
            log.warn("DateUtil.getDateLongByTime error" + e);
            return 0;
        }
    }


    public static void main(String[] args){
        Date date = new Date();
        Date expTime = DateUtil.getAroundDateByUint(date, 1, ChronoUnit.MONTHS, "yyyy-MM-dd");
        System.out.println(getStringByDate(expTime, null));
        expTime = DateUtil.getAroundDateByUint(expTime, 3, ChronoUnit.HOURS, null);
        System.out.println(getStringByDate(expTime, null));
    }

}
