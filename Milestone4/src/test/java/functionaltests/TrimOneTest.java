package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrimOneTest {

    TrimOne trimOne = new TrimOne();

    @Test
    public void testTrimOne_HelloReturns_ell() {
        // trimOne("Hello") -> "ell"
        assertEquals("ell", trimOne.trimOne("Hello"));
    }

    @Test
    public void testTrimOne_javaReturns_av() {
        // trimOne("java") -> "av"
        assertEquals("av", trimOne.trimOne("java"));
    }

    @Test
    public void testTrimOne_codingReturns_odin() {
        // trimOne("coding") -> "odin"
        assertEquals("odin", trimOne.trimOne("coding"));
    }
}