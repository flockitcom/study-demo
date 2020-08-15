package utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * 当前时间周围时间
 */
public class UtilsTest01 {
    public static void main(String[] args) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, +4);
        date = calendar.getTime();
        System.out.println(date);;
    }
}
