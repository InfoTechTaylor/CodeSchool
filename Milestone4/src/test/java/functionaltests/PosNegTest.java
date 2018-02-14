package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class PosNegTest {

    PosNeg posNeg = new PosNeg();

    @Test
    public void testPosNegOneIsPosOneIsNegAndFalse() {
        // posNeg(1, -1, false) -> true

        assertEquals(true, posNeg.posNeg(1,-1,false));
    }

    @Test
    public void testPosNegFirstIsPosSecondIsNegAndFalse(){
        // posNeg(-1, 1, false) -> true
        assertEquals(true, posNeg.posNeg(-1,1, false));
    }

    @Test
    public void testPosNegBothAreNegativeTrue(){
        // posNeg(-4, -5, true) -> true
        assertEquals(true, posNeg.posNeg(-4,-5, true));
    }
}