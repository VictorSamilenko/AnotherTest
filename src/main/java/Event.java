import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Event {
    private static Logger log = Logger.getLogger(Event.class);
    private final SimpleDateFormat dateFormat;
    private final Date morning, day, evening, night;
    private final ResourceBundle resourceBundle;


    public Event() throws ParseException {
        try {
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            morning = dateFormat.parse("06:00:00");
            day = dateFormat.parse("09:00:00");
            evening = dateFormat.parse("19:00:00");
            night = dateFormat.parse("23:00:00");
            resourceBundle = ResourceBundle.getBundle("bundle", Locale.getDefault(), new UTF8Control());
        } catch (ParseException e) {
            log.error("Ошибка парсинга даты");
            throw e;
        }
    }

    public String getPart(Date currentDate) throws ParseException {
        Date currentTime = null;
        try {
            currentTime = dateFormat.parse(dateFormat.format(currentDate));
        } catch (ParseException e) {
            log.error("Ошибка парсинга даты");
            e.printStackTrace();
            throw new ParseException(e.getMessage(), e.getErrorOffset());
        }

        log.info("Определение времени суток");

        if (currentTime.equals(morning) || (currentTime.after(morning) && currentTime.before(day)))
            return "morning";

        if (currentTime.equals(day) || (currentTime.after(day) && currentTime.before(evening)))
            return "day";

        if (currentTime.equals(evening) || (currentTime.after(evening) && currentTime.before(night)))
            return "evening";

        return "night";
    }

    public String getMessage(Date date) {
        String part = null;
        try {
            part = getPart(date);
        } catch (ParseException e) {
            if (part == null)
                log.error("Ошибка парсинга, невозможно определить время суток.");
            return null;
        }
        String message = resourceBundle.getString("message_" + part);
        log.info("Создано новое сообщение!");
        return message;
    }
}
