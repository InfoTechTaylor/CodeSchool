package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class SameFirstLastTest {
    private SameFirstLast sfl = new SameFirstLast();

    public SameFirstLastTest(){

    }


    // Given an array of ints, return true if the array is length
    // 1 or more, and the first element and the last element are equal.
    //
    // sameFirstLast({1, 2, 3}) -> false
    // sameFirstLast({1, 2, 3, 1}) -> true
    // sameFirstLast({1, 2, 1}) -> true
    @Test
    public void testSameFirstLastLength3NotEqual() {
        // sameFirstLast({1, 2, 3}) -> false
        int[] numbers = {1,2,3};
        assertEquals(sfl.sameFirstLast(numbers), false);
    }

    @Test
    public void testSameFirstLastLength4Equal() {
        // sameFirstLast({1, 2, 3, 1}) -> true
        int[] numbers = {1,2,3, 1};
        assertEquals(sfl.sameFirstLast(numbers), true);
    }

    @Test
    public void testSameFirstLastLength3Equal() {
        // sameFirstLast({1, 2, 1}) -> true
        int[] numbers = {1,2,1};
        assertEquals(sfl.sameFirstLast(numbers), true);
    }
}