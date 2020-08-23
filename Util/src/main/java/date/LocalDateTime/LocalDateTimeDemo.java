package date.LocalDateTime;

import org.junit.Test;
import sun.util.calendar.LocalGregorianCalendar;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTimeDemo {
    public static void main(String[] args) {
        LocalDate curDay = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        curDay = curDay.withDayOfMonth(1);
        LocalDateTime startTime = LocalDateTime.of(curDay, LocalTime.of(0, 0, 0));
        System.out.println(startTime);

        curDay = curDay.plusMonths(1).minusDays(1);
        startTime = LocalDateTime.of(curDay, LocalTime.of(23, 59, 59));
        System.out.println(startTime);

        String time = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String timeStr = "2020-08-21 23:59:59";
        LocalDateTime curTime = LocalDateTime.parse(timeStr,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Date date = Date.from(curTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("date="+date);

        date = new Date();
        System.out.println("date2="+date);

        System.out.println(curTime);
        LocalDateTime dateTime = LocalDateTime.of(2019, 7, 21, 16, 41, 30);
        System.out.println(dateTime);
    }

    @Test
    public void test1(){
        LocalDateTime localDateTime = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();

        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
