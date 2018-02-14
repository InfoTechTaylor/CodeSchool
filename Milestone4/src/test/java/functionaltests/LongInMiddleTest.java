package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class LongInMiddleTest {

    LongInMiddle longInMiddle = new LongInMiddle();

    @Test
    public void testLongInMiddleHelloFirstParam() {
        // longInMiddle("Hello", "hi") -> "hiHellohi"
        assertEquals("hiHellohi", longInMiddle.longInMiddle("Hello", "hi"));
    }

    @Test
    public void testLongInMiddleHelloSecondParam() {
        // longInMiddle("hi", "Hello") -> "hiHellohi"
        assertEquals("hiHellohi", longInMiddle.longInMiddle("hi", "Hello"));
    }

    @Test
    public void testLongInMiddleaaa() {
        // longInMiddle("aaa", "b") -> "baaab"
        assertEquals("baaab", longInMiddle.longInMiddle("aaa", "b"));
    }
}