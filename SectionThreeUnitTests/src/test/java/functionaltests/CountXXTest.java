package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountXXTest {

    private CountXX xx = new CountXX();

    public CountXXTest(){

    }

    // Count the number of "xx" in the given String. We'll say
    // that overlapping is allowed, so "xxx" contains 2 "xx".
    //

    @Test
    public void testCountABCXX1() {
        // countXX("abcxx") -> 1
        int number = xx.countXX("abcxx");
        assertEquals(number, 1);
    }

    @Test
    public void testCountXXX2() {
        // countXX("xxx") -> 2
        assertEquals(xx.countXX("xxx"), 2);
    }

    @Test
    public void testCountXXXX3() {
        // countXX("xxxx") -> 3
        assertEquals(xx.countXX("xxxx"), 3);
    }
}