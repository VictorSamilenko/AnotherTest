import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Date;

public class Main {
    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws ParseException {
        Event event = new Event();
        System.out.println(event.getMessage(new Date()));
        log.info("Вывод нового уведомления");
    }
}
