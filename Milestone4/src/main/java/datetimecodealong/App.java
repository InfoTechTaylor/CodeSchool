package datetimecodealong;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now(); // gives us today's date in ISO format
        System.out.println(localDate);

        localDate = LocalDate.parse("2015-01-01");
        System.out.println(localDate);

        localDate = LocalDate.parse("02/07/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(localDate);

        String isoDate = localDate.toString();
        System.out.println(isoDate);
        localDate = LocalDate.parse(isoDate);
        System.out.println(localDate);

        String formatted = localDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(formatted);

        formatted = localDate.format(DateTimeFormatter.ofPattern("MM=dd=yyyy+=+=+="));
        System.out.println(formatted);

        formatted = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)); // FULL is an enum
        System.out.println(formatted);

        formatted = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)); // FULL is an enum
        System.out.println(formatted);

        LocalDate past = localDate.minusDays(8);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        past = localDate.minusMonths(3);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        Period diff = localDate.until(past);
        System.out.println(diff);
        System.out.println(diff.getMonths());
        diff = past.until(localDate);
        System.out.println(diff.getMonths());

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        formatted = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        System.out.println(formatted);

        Date legacyDate = new Date();
        System.out.println(legacyDate);

        GregorianCalendar legacyCalendar = new GregorianCalendar();
        System.out.println(legacyCalendar);

        // convert legacyDate Date to the new DateTime API
        ZonedDateTime zdt = ZonedDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());
        localDate = zdt.toLocalDate();
        System.out.println(localDate);

        // convert GregorianCalendar date to new DateTime method
        zdt = legacyCalendar.toZonedDateTime();
        localDate = zdt.toLocalDate();
        System.out.println(localDate);
    }
}
