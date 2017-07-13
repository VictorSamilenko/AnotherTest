import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestWorld {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static Event event;

    private void testEvent(String formatDate, String partDay) {
        Date date;
        try {
            date = dateFormat.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        String part = event.getPart(date);
        Assert.assertEquals(part, partDay);
    }

    @BeforeClass
    public static void beforeTest() {
        event = new Event();
    }


    @Test
    public void testCreateEvent() {
        event = new Event();
        event.getPart(new Date());
    }


    @Test
    public void testMorning1() {
        testEvent("06:00:00", "morning");
    }

    @Test
    public void testMorning2() {
        testEvent("08:59:59", "morning");
    }

    @Test
    public void testMorning() {
        testEvent("07:35:35", "morning");
    }

    @Test
    public void testDay1() {
        testEvent("09:00:00", "day");
    }

    @Test
    public void testDay2() {
        testEvent("12:34:56", "day");
    }

    @Test
    public void testDay3() {
        testEvent("18:59:59", "day");
    }

    @Test
    public void testEvening1() {
        testEvent("19:00:00", "evening");
    }

    @Test
    public void testEvening2() {
        testEvent("20:21:22", "evening");
    }

    @Test
    public void testEvening3() {
        testEvent("22:59:59", "evening");
    }

    @Test
    public void testNight1() {
        testEvent("23:00:00", "night");
    }

    @Test
    public void testNight2() {
        testEvent("04:00:01", "night");
    }

    @Test
    public void testNight3() {
        testEvent("05:59:59", "night");
    }
}
