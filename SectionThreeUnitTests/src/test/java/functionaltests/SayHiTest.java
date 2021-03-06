package functionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SayHiTest {

    private SayHi sayHi = new SayHi();

    public SayHiTest() {

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of sayHi method of SayHi class
     */
    @Test
    public void testBob() {
        String expectedResult = "Hello Bob!";
        assertEquals(expectedResult, sayHi.sayHi("Bob"));
    }

    @Test
    public void testAlice() {
        String expectedResult = "Hello Alice!";
        assertEquals(expectedResult, sayHi.sayHi("Alice"));
    }

    @Test
    public void testX() {
        String expectedResult = "Hello X!";
        assertEquals(expectedResult, sayHi.sayHi("X"));
    }
}