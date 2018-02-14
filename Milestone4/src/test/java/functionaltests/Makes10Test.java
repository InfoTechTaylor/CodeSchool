package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class Makes10Test {

    Makes10 m10 = new Makes10();

    @Test
    public void testMakes10_OneNumIs10() {
        // makes10(9, 10) -> true
        assertEquals(true, m10.makes10(9, 10));
    }

    @Test
    public void testMakes10_false() {
        // makes10(9, 9) -> false
        assertEquals(false, m10.makes10(9,9));
    }

    @Test
    public void testMakes10_SumIs10() {
        // makes10(1, 9) -> true
        assertEquals(true, m10.makes10(1,9));
    }
}