package functionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MischeviousChildrenTest {

    private MischeviousChildren children = new MischeviousChildren();

    public MischeviousChildrenTest() {

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void areWeInTrouble2True() {
        // areWeInTrouble(true, true) -> true
        assertTrue(children.areWeInTrouble(true, true));
    }

    @Test
    public void areWeInTrouble2False() {
        // areWeInTrouble(false, false) -> true
        assertTrue(children.areWeInTrouble(false, false));
    }

    @Test
    public void areWeInTrouble1True1False() {
        // areWeInTrouble(true, false) -> false
        assertFalse(children.areWeInTrouble(true, false));
    }
}