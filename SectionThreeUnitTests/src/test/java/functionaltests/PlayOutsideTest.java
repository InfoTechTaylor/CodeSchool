package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayOutsideTest {

    private PlayOutside play = new PlayOutside();

    // playOutside(70, false) → true
    // playOutside(95, false) → false
    // playOutside(95, true) → true

    @Test
    public void testPlayOutside70True() {
        // playOutside(70, false) → true
        assertEquals(play.playOutside(70, false), true);
    }

    @Test
    public void testPlayOutside95False() {
        // playOutside(95, false) → false
        assertEquals(play.playOutside(95, false), false);
    }

    @Test
    public void testPlayOutside95True() {
        // playOutside(95, true) → true
        assertEquals(play.playOutside(95, true), true);
    }
}