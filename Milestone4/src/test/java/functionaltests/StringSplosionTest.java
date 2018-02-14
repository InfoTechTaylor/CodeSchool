package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringSplosionTest {

    StringSplosion ss = new StringSplosion();

    @Test
    public void testStringSplosionCode() {
        //stringSplosion("Code") -> "CCoCodCode"
        assertEquals("CCoCodCode", ss.stringSplosion("Code"));
    }

    @Test
    public void testStringSplosionabc() {
        // stringSplosion("abc") -> "aababc"
        assertEquals("aababc", ss.stringSplosion("abc"));
    }

    @Test
    public void testStringSplosionaab() {
        // stringSplosion("ab") -> "aab"
        assertEquals("aab", ss.stringSplosion("ab"));
    }
}