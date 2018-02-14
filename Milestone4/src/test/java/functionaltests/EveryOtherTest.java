package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class EveryOtherTest {

    EveryOther eo = new EveryOther();

    @Test
    public void testEveryOtherHlo() {
        // everyOther("Hello") -> "Hlo"
        assertEquals("Hlo", eo.everyOther("Hello"));
    }

    @Test
    public void testEveryOtherH() {
        // everyOther("Hi") -> "H"
        assertEquals("H", eo.everyOther("Hi"));
    }

    @Test
    public void testEveryOtherHello() {
        // everyOther("Heeololeo") -> "Hello"
        assertEquals("Hello", eo.everyOther("Heeololeo"));
    }
}