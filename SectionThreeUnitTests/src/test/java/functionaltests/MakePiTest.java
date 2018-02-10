package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class MakePiTest {
    private MakePi pi = new MakePi();

    public MakePiTest(){

    }

    @Test
    public void makePi() {
        // Return an int array length n containing the first n digits of pi.
        //
        // makePi(3) -> {3, 1, 4}

        int[] piArray = {3,1,4};
        assertEquals(pi.makePi(3)[0], piArray[0]);
        assertEquals(pi.makePi(3)[1], piArray[1]);
        assertEquals(pi.makePi(3)[2], piArray[2]);
    }
}