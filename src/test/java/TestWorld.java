import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestWorld {
    private static SimpleDateFormat dateFormat  = new SimpleDateFormat("HH:mm:ss");

    private static Event event;

    private void testEvent(String formatDate,String partDay) throws ParseException {
        Date date = dateFormat.parse(formatDate);
        String part = event.getPart(date);
        Assert.assertEquals(part, partDay);
    }

    @BeforeClass
    public static void beforeTest() throws ParseException {
        event = new Event();
    }


    @Test
    public void testCreateEvent() throws ParseException {
        event = new Event();
        try {
            event.getPart(new Date());
        } catch (ParseException e) {
            Assert.fail();
        }
    }


    @Test
    public void testMorning1() throws ParseException {
        testEvent("06:00:00","morning");
    }

    @Test
    public void testMorning2() throws ParseException {
        testEvent("08:59:59","morning");
    }

    @Test
    public void testMorning() throws ParseException {
        testEvent("07:35:35","morning");
    }

    @Test
    public void testDay1() throws ParseException {
        testEvent("09:00:00","day");
    }

    @Test
    public void testDay2() throws ParseException {
        testEvent("12:34:56","day");
    }

    @Test
    public void testDay3() throws ParseException {
        testEvent("18:59:59","day");
    }

    @Test
    public void testEvening1() throws ParseException {
        testEvent("19:00:00","evening");
    }

    @Test
    public void testEvening2() throws ParseException {
        testEvent("20:21:22","evening");
    }

    @Test
    public void testEvening3() throws ParseException {
        testEvent("22:59:59","evening");
    }

    @Test
    public void testNight1() throws ParseException {
        testEvent("23:00:00","night");
    }

    @Test
    public void testNight2() throws ParseException {
        testEvent("04:00:01","night");
    }

    @Test
    public void testNight3() throws ParseException {
        testEvent("05:59:59","night");
    }


}
