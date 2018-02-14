package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleXTest {

    DoubleX x = new DoubleX();

    // doubleX("axxbb") -> true
    // doubleX("axaxxax") -> false
    // doubleX("xxxxx") -> true
    @Test
    public void testDoubleXaxxbbTrue() {
        // doubleX("axxbb") -> true
        assertEquals(true, x.doubleX("axxbb"));
    }

    @Test
    public void testDoubleXaxaxxaxFalse() {
        // doubleX("axaxxax") -> false
        assertEquals(false, x.doubleX("axaxxax"));
    }

    @Test
    public void testDoubleXxxxxxTrue() {
        // doubleX("xxxxx") -> true
        assertEquals(true, x.doubleX("xxxxx"));
    }
}