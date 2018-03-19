package tests;

import markdownfeatures.Blockquote;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockquoteTest {

    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new Blockquote().detect(">", "> this is a > quote"));
    }

    @Test
    public void test02_string_detection_invalid() throws Exception {
        assertFalse(new Blockquote().detect(">", ">this is a quote"));
    }

    @Test
    public void test03_string_detection_invalid() throws Exception {
        assertFalse(new Blockquote().detect(">", "> "));
    }

    @Test
    public void test04_convertible() throws Exception {
        assertTrue(new Blockquote().convertible());
    }

    @Test
    public void test05_no_separation() throws Exception {
        Blockquote blockquote = new Blockquote();
        assertTrue(blockquote.detect("> foo", "> foo boo > loo"));
        assertFalse(blockquote.getSeparation().isSeparation());
    }

    @Test
    public void test06_input_unchangeable() throws Exception {
        Blockquote blockquote = new Blockquote();
        blockquote.setInput("> hello");
        assertEquals(blockquote.getInput(), "");
    }
}
