package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class SumTest {

    Sum sum = new Sum();

    @Test
    public void testSum6() {
        // sum({1, 2, 3}) -> 6
        int[] array = {1, 2, 3};
        assertEquals(6, sum.sum(array));
    }

    @Test
    public void testSum18() {
        // sum({5, 11, 2}) -> 18
        int[] array = {5, 11, 2};
        assertEquals(18, sum.sum(array));
    }

    @Test
    public void testSum7() {
        // sum({7, 0, 0}) -> 7
        int[] array = {7, 0, 0};
        assertEquals(7, sum.sum(array));
    }
}