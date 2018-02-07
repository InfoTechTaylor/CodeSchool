package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class FrontTimesTest {

    private FrontTimes ft = new FrontTimes();

    public FrontTimesTest(){

    }

    // Given a String and a non-negative int n, we'll say that the
    // front of the String is the first 3 chars, or whatever is there
    // if the String is less than length 3. Return n copies of the front;
    //
    // frontTimes("Chocolate", 2) -> "ChoCho"
    // frontTimes("Chocolate", 3) -> "ChoChoCho"
    // frontTimes("Abc", 3) -> "AbcAbcAbc"

    @Test
    public void testFrontTimesChoCho() {
        // frontTimes("Chocolate", 2) -> "ChoCho"
        assertEquals(ft.frontTimes("Chocoloate", 2), "ChoCho");
    }

    @Test
    public void testFrontTimesChoChoCho() {
        // frontTimes("Chocolate", 3) -> "ChoChoCho"
        assertEquals(ft.frontTimes("Chocoloate", 3), "ChoChoCho");
    }

    @Test
    public void testFrontTimesAbcAbcAbc() {
        // frontTimes("Abc", 3) -> "AbcAbcAbc"
        assertEquals(ft.frontTimes("Abc", 3), "AbcAbcAbc");
    }

    @Test
    public void testFrontTimesAbAb() {
        // frontTimes("Ab", 2) -> "AbAb"
        assertEquals(ft.frontTimes("Ab", 2), "AbAb");
    }
}