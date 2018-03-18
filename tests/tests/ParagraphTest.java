package tests;

import markdownfeatures.Paragraph;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParagraphTest {

    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new Paragraph().detect("", ""));
    }

    @Test
    public void test02_string_detection_invalid() throws Exception {
        assertFalse(new Paragraph().detect("stuff", "stuff that isn't a new line"));
    }

    @Test
    public void test03_string_detection_invalid() throws Exception {
        assertFalse(new Paragraph().detect("      ", "         \n     "));
    }

    @Test
    public void test04_input_unchangeable() throws Exception {
        Paragraph paragraph = new Paragraph();
        paragraph.setInput("changing input text");
        assertNotEquals(paragraph.getInput(), "changing input text");
    }

    @Test
    public void test05_convertible() throws Exception {
        assertTrue(new Paragraph().convertible());
    }

    @Test
    public void test06_separation() throws Exception {
        Paragraph paragraph = new Paragraph();
        assertFalse(paragraph.detect("\n", "\n \n   "));
        assertFalse(paragraph.getSeparation().isSeparation());
    }
}