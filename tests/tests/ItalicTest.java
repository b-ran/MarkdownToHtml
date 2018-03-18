package tests;

import markdownfeatures.Italic;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItalicTest {

    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new Italic().detect("*italic*", "*italic*"));
    }

    @Test
    public void test02_separate_tag_detection_valid() throws Exception {
        assertTrue(new Italic().detect("*long", "*long line*"));
    }

    @Test
    public void test03_string_detection_invalid_format() throws Exception {
        assertFalse(new Italic().detect("**long", "**long line**"));
    }

    @Test
    public void test04_string_detection_valid() throws Exception {
        assertTrue(new Italic().detect("*ital*ic*", "*ita*lic*"));
    }

    @Test
    public void test05_format_spacing_invalid() throws Exception {
        assertFalse(new Italic().detect("*", "* words *"));
    }

    @Test
    public void test06_convertible() throws Exception {
        assertTrue(new Italic().convertible());
    }

    @Test
    public void test07_input_formatting() throws Exception {
        Italic italic = new Italic();
        italic.setInput("*stars*");
        assertEquals(italic.getInput(), "stars");
    }

    @Test
    public void test08_separation_creation() throws Exception {
        Italic italic = new Italic();
        assertTrue(italic.detect("*in", "*in between*"));
        assertTrue(italic.getSeparation().isSeparation());
    }
}