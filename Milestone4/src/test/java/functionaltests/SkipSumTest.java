package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class SkipSumTest {

    SkipSum ss = new SkipSum();

    @Test
    public void testSkipSum7() {
        // skipSum(3, 4) → 7
        assertEquals(7, ss.skipSum(3,4));
    }

    @Test
    public void testSkipSum20() {
        // skipSum(9, 4) → 20
        assertEquals(20, ss.skipSum(9,4));
    }

    @Test
    public void testSkipSum21() {
        // skipSum(10, 11) → 21
        assertEquals(21, ss.skipSum(10,11));
    }
}