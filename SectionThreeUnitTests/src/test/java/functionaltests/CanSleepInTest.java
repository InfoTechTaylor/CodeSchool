package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class CanSleepInTest {
    private CanSleepIn sleepIn = new CanSleepIn();

    public CanSleepInTest(){

    }


    @Test
    public void testCanSleepInFalseFalse() {
        // canSleepIn(false, false) -> true
        assertEquals(sleepIn.canSleepIn(false, false), true);
    }

    @Test
    public void testCanSleepInTrueFalse() {
        // canSleepIn(true, false) -> false
        assertEquals(sleepIn.canSleepIn(true, false), false);
    }

    @Test
    public void testCanSleepInFalseTrue() {
        // canSleepIn(false, true) -> true
        assertEquals(sleepIn.canSleepIn(false, true), true);
    }
}