package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonEndTest {

    // commonEnd({1, 2, 3}, {7, 3}) -> true
    // commonEnd({1, 2, 3}, {7, 3, 2}) -> false
    // commonEnd({1, 2, 3}, {1, 3}) -> true
    CommonEnd commonEnd = new CommonEnd();

    @Test
    public void testCommonEndTrueSameLastElement() {
        // commonEnd({1, 2, 3}, {7, 3}) -> true
        //arrange
        int[] array1 = {1,2,3};
        int[] array2 = {7,3};

        //act
        //commonEnd.commonEnd(array1, array2);

        //assert
        assertEquals(true, commonEnd.commonEnd(array1, array2));
    }

    @Test
    public void testCommonEndFalseNotSame() {
        // commonEnd({1, 2, 3}, {7, 3, 2}) -> false
        int[] array1 = {1,2,3};
        int[] array2 = {7,3,2};

        assertEquals(false, commonEnd.commonEnd(array1, array2));
    }

    @Test
    public void testCommonEndTrueSameFirstSameLastElement() {
        // commonEnd({1, 2, 3}, {1, 3}) -> true
        int[] array1 = {1,2,3};
        int[] array2 = {1,3};

        assertEquals(true, commonEnd.commonEnd(array1, array2));
    }
}