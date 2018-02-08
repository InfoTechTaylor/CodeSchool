package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class SumDoubleTest {

    private SumDouble sumDouble = new SumDouble();

    @Test
    public void sumDouble3() {
        // sumDouble(1, 2) -> 3
        assertEquals(sumDouble.sumDouble(1,2), 3);
    }

    @Test
    public void sumDouble5() {
        // sumDouble(3, 2) -> 5
        assertEquals(sumDouble.sumDouble(3,2), 5);

    }

    @Test
    public void sumDouble8() {
        // sumDouble(2, 2) -> 8
        assertEquals(sumDouble.sumDouble(2,2), 8);
    }
}