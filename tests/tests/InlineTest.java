package tests;

import markdownfeatures.Inline;
import org.junit.Test;

import static org.junit.Assert.*;

public class InlineTest {

    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new Inline().detect("`", "` inline ` is inline"));
    }

    @Test
    public void test02_string_detection_valid() throws Exception {
        assertTrue(new Inline().detect("`together`", "`together` in one word "));
    }

    @Test
    public void test03_string_detection_invalid() throws Exception {
        assertFalse(new Inline().detect("``", "`` `` ``"));
    }

    @Test
    public void test04_string_detection_invalid() throws Exception {
        assertFalse(new Inline().detect("`hello", "`hello i'm missing a tag"));
    }

    @Test
    public void test05_string_detection_invalid() throws Exception {
        assertFalse(new Inline().detect("```", "``` code block ```"));
    }

    @Test
    public void test06_string_detection_valid() throws Exception {
        assertTrue(new Inline().detect("`t", "`t oget`her` in one word "));
    }

    @Test
    public void test07_convertible() throws Exception {
        assertTrue(new Inline().convertible());
    }

    @Test
    public void test08_input_formatting() throws Exception {
        Inline inline = new Inline();
        inline.setInput("`dashes`");
        assertEquals(inline.getInput(), "dashes");
    }

    @Test
    public void test09_separation_creation() throws Exception {
        Inline inline = new Inline();
        assertTrue(inline.detect("`", "` line of code `"));
        assertTrue(inline.getSeparation().isSeparation());
    }
}
