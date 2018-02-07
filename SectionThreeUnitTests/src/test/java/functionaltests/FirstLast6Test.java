package functionaltests;

import org.junit.Test;

//import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class FirstLast6Test {
    private FirstLast6 fl6 = new FirstLast6();

    public FirstLast6Test(){

    }

    @Test
    public void testFirstLast6Last() {
        // firstLast6({1, 2, 6}) -> true
        int[] array = {1,2,6};
        assertEquals(fl6.firstLast6(array), true);
    }

    @Test
    public void testFirstLast6First() {
        // firstLast6({6, 1, 2, 3}) -> true
        int[] array = {6,1,2,3};
        assertEquals(fl6.firstLast6(array), true);
    }

    @Test
    public void testFirstLast6Middle() {
        // firstLast6({13, 6, 1, 2, 3}) -> false
        int[] array = {13,6,1,2,3};
        assertEquals(fl6.firstLast6(array), false);
    }
}