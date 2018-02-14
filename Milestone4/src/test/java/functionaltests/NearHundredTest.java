package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class NearHundredTest {

    NearHundred nh = new NearHundred();

    @Test
    public void testNearHundred103() {
        // nearHundred(103) -> true
        assertEquals(true, nh.nearHundred(103));
    }

    @Test
    public void testNearHundred90() {
        // nearHundred(90) -> true
        assertEquals(true, nh.nearHundred(90));
    }

    @Test
    public void testNearHundred89() {
        // nearHundred(89) -> false
        assertEquals(false, nh.nearHundred(89));
    }
}