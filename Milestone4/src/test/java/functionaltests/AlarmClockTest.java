package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlarmClockTest {

    AlarmClock ac = new AlarmClock();

    @Test
    public void testAlarmClock1() {
        // alarmClock(1, false) → "7:00"
        assertEquals("7:00", ac.alarmClock(1, false));
    }

    @Test
    public void testAlarmClock5() {
        // alarmClock(5, false) → "7:00"
        assertEquals("7:00", ac.alarmClock(5, false));
    }

    @Test
    public void testAlarmClock0() {
        // alarmClock(0, false) → "10:00"
        assertEquals("10:00", ac.alarmClock(0, false));
    }
}