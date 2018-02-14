package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class RotateLeftTest {

    RotateLeft rlt = new RotateLeft();

    @Test
    public void testRotateLeft_2_3_1() {
        // rotateLeft({1, 2, 3}) -> {2, 3, 1}
        int[] originalArray = {1,2,3};
        int[] rotatedLeftArray = rlt.rotateLeft(originalArray);
        int[] expectedArray = {2,3,1};
        assertEquals(expectedArray[0], rotatedLeftArray[0]);
        assertEquals(expectedArray[1], rotatedLeftArray[1]);
        assertEquals(expectedArray[2], rotatedLeftArray[2]);
    }

    @Test
    public void testRotateLeft_11_9_5() {
        // rotateLeft({5, 11, 9}) -> {11, 9, 5}
        int[] originalArray = {5,11,9};
        int[] rotatedLeftArray = rlt.rotateLeft(originalArray);
        int[] expectedArray = {11,9,5};
        assertEquals(expectedArray[0], rotatedLeftArray[0]);
        assertEquals(expectedArray[1], rotatedLeftArray[1]);
        assertEquals(expectedArray[2], rotatedLeftArray[2]);
    }

    @Test
    public void testRotateLeft_0_0_7() {
        // rotateLeft({7, 0, 0}) -> {0, 0, 7}
        int[] originalArray = {7,0,0};
        int[] rotatedLeftArray = rlt.rotateLeft(originalArray);
        int[] expectedArray = {0,0,7};
        assertEquals(expectedArray[0], rotatedLeftArray[0]);
        assertEquals(expectedArray[1], rotatedLeftArray[1]);
        assertEquals(expectedArray[2], rotatedLeftArray[2]);
    }
}