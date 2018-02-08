package functionaltests;

import org.junit.Test;

import static org.junit.Assert.*;

public class MakeTagsTest {

    MakeTags tags = new MakeTags();

    // makeTags("i", "Yay") -> "<i>Yay</i>"
    // makeTags("i", "Hello") -> "<i>Hello</i>"
    // makeTags("cite", "Yay") -> "<cite>Yay</cite>"
    @Test
    public void testMakeTagsYayItalic() {
        // makeTags("i", "Yay") -> "<i>Yay</i>"
        assertEquals(tags.makeTags("i", "Yay"), "<i>Yay</i>");
    }


    @Test
    public void testMakeTagsHelloItalic() {
        // makeTags("i", "Hello") -> "<i>Hello</i>"
        assertEquals(tags.makeTags("i", "Hello"), "<i>Hello</i>");
    }


    @Test
    public void testMakeTagsHelloCite() {
        // makeTags("cite", "Yay") -> "<cite>Yay</cite>"
        assertEquals(tags.makeTags("cite", "Yay"), "<cite>Yay</cite>");
    }
}