import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Event {
    private static Logger log = Logger.getLogger(Event.class);
    private static int MORNING = 6, DAY = 9, EVENING = 19, NIGHT = 23;
    private final ResourceBundle resourceBundle;
    private final Calendar calendar;

    public Event() {
        resourceBundle = ResourceBundle.getBundle("bundle", Locale.getDefault(), new UTF8Control());
        calendar = Calendar.getInstance();
    }

    public String getPart(Date currentDate) {
        log.info("Определение времени суток");
        calendar.setTime(currentDate);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= MORNING && hour < DAY)
            return "morning";
        if (hour >= DAY && hour < EVENING)
            return "day";
        if (hour >= EVENING && hour < NIGHT)
            return "evening";
        return "night";
    }

    public String getMessage(Date date) {
        String part = getPart(date);
        String message = resourceBundle.getString("message_" + part);
        log.info("Создано новое сообщение!");
        return message;
    }
}
