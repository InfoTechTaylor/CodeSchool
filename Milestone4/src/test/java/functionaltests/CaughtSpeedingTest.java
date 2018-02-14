package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class CaughtSpeedingTest {

    private CaughtSpeeding cs = new CaughtSpeeding();

    @Test
    public void testCaughtSpeeding60False() {
        // caughtSpeeding(60, false) → 0
        assertEquals(0, cs.caughtSpeeding(60, false));
    }

    @Test
    public void testCaughtSpeeding65False() {
        // caughtSpeeding(65, false) → 0
        assertEquals(1, cs.caughtSpeeding(65, false));

    }

    @Test
    public void testCaughtSpeeding65True() {
        // caughtSpeeding(65, true) → 0
        assertEquals(0, cs.caughtSpeeding(65, true));

    }

    @Test
    public void testCaughtSpeeding86True() {
        assertEquals(2, cs.caughtSpeeding(86, true));
    }
}