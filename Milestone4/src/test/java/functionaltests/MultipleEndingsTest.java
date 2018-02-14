package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class MultipleEndingsTest {

    MultipleEndings me = new MultipleEndings();

    @Test
    public void testMultipleEndingslo() {
        // multipleEndings("Hello") -> "lololo"
        assertEquals("lololo", me.multipleEndings("Hello"));
    }

    @Test
    public void testMultipleEndingsab() {
        // multipleEndings("ab") -> "ababab"
        assertEquals("ababab", me.multipleEndings("ab"));

    }

    @Test
    public void testMultipleEndingsHi() {
        // multipleEndings("Hi") -> "HiHiHi"
        assertEquals("HiHiHi", me.multipleEndings("Hi"));
    }


}