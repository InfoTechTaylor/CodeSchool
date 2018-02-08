package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParrotTroubleTest {

    ParrotTrouble parrotTrouble = new ParrotTrouble();

    @Test
    public void testParrotTroubleTrue6() {
        // parrotTrouble(true, 6) -> true
        assertEquals(parrotTrouble.parrotTrouble(true, 6), true);
    }

    @Test
    public void testParrotTroubleTrue7() {
        // parrotTrouble(true, 7) -> false
        assertEquals(parrotTrouble.parrotTrouble(true, 7), false);
    }

    @Test
    public void testParrotTroubleFalse6() {
        // parrotTrouble(false, 6) -> false
        assertEquals(parrotTrouble.parrotTrouble(false, 6), false);
    }
}