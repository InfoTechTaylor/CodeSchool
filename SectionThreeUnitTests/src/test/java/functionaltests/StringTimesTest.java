package functionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringTimesTest {

    private StringTimes stringTimes = new StringTimes();

    public StringTimesTest(){

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    // Given a String and a non-negative int n, return a larger String
    // that is n copies of the original String.
    //
    // stringTimes("Hi", 2) -> "HiHi"
    // stringTimes("Hi", 3) -> "HiHiHi"
    // stringTimes("Hi", 1) -> "Hi"

    @Test
    public void testStringTimesHi2() {
        // stringTimes("Hi", 2) -> "HiHi"
        assertEquals(stringTimes.stringTimes("Hi", 2), "HiHi");
    }

    @Test
    public void testStringTimesHi3() {
        // stringTimes("Hi", 3) -> "HiHiHi"
        assertEquals(stringTimes.stringTimes("Hi", 3), "HiHiHi");
    }

    @Test
    public void testStringTimesHi1() {
        // stringTimes("Hi", 1) -> "Hi"
        assertEquals(stringTimes.stringTimes("Hi", 1), "Hi");
    }
}