package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class Diff21Test {

    private Diff21 diff21 = new Diff21();

    @Test
    public void testDiff21Return4() {
        // diff21(23) -> 4
        assertEquals(diff21.diff21(23), 4);

    }

    @Test
    public void testDiff21Return11() {
        // diff21(10) -> 11
        assertEquals(diff21.diff21(10), 11);
    }

    @Test
    public void testDiff21Return0() {
        // diff21(21) -> 0
        assertEquals(diff21.diff21(21), 0);
    }
}