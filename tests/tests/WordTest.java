package tests;

import markdownfeatures.Word;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {
    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new Word().detect("word", "word a feature that allows words!"));
    }

    @Test
    public void test02_string_detection_invalid() throws Exception {
        assertFalse(new Word().detect("   ", "    fd   "));
    }

    @Test
    public void test03_string_detection_invalid() throws Exception {
        assertFalse(new Word().detect("", ""));
    }

    @Test
    public void test04_input_changeable() throws Exception {
        Word paragraph = new Word();
        paragraph.setInput("hello words");
        assertEquals(paragraph.getInput(), "hello words");
    }

    @Test
    public void test05_convertible() throws Exception {
        assertTrue(new Word().convertible());
    }

    @Test
    public void test06_separation() throws Exception {
        Word word = new Word();
        assertTrue(word.detect("words", "words and more words!!! #words"));
        assertFalse(word.getSeparation().isSeparation());
    }
}
