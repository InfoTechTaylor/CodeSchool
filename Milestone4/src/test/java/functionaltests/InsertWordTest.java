package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class InsertWordTest {

    InsertWord iw = new InsertWord();

    @Test
    public void testInsertWordYay() {
        //insertWord("<<>>", "Yay") -> "<<Yay>>"
        assertEquals("<<Yay>>", iw.insertWord("<<>>", "Yay"));
    }

    @Test
    public void testInsertWordWooHoo() {
        // insertWord("<<>>", "WooHoo") -> "<<WooHoo>>"
        assertEquals("<<WooHoo>>", iw.insertWord("<<>>", "WooHoo"));
    }

    @Test
    public void testInsertWordword() {
        // insertWord("[[]]", "word") -> "[[word]]"
        assertEquals("[[word]]", iw.insertWord("[[]]", "word"));
    }
}