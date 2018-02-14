package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class FirstHalfTest {

    FirstHalf fh = new FirstHalf();

    @Test
    public void testFirstHalfWoo() {
        // firstHalf("WooHoo") -> "Woo"
        assertEquals("Woo", fh.firstHalf("WooHoo"));
    }

    @Test
    public void testfirstHalfHello() {
        // firstHalf("HelloThere") -> "Hello"
        assertEquals("Hello", fh.firstHalf("HelloThere"));
    }

    @Test
    public void testFirstHalfabc() {
        // firstHalf("abcdef") -> "abc"
        assertEquals("abc", fh.firstHalf("abcdef"));
    }
}