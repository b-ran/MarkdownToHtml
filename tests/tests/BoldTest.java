package tests;

import markdownfeatures.Bold;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoldTest {

    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new Bold().detect("**bold**", "**bold**"));
    }

    @Test
    public void test02_separate_tag_detection_valid() throws Exception {
        assertTrue(new Bold().detect("**jim", "**jim my**"));
    }

    @Test
    public void test03_string_detection_invalid() throws Exception {
        assertFalse(new Bold().detect("*hello**", "*hello** bob"));
    }

    @Test
    public void test04_string_detection_invalid() throws Exception {
        assertFalse(new Bold().detect("*tim*", "*tim* y"));
    }

    @Test
    public void test05_convertible() throws Exception {
        assertTrue(new Bold().convertible());
    }

    @Test
    public void test06_input_formatting() throws Exception {
        Bold bold = new Bold();
        bold.setInput("**formatting**");
        assertEquals(bold.getInput(), "formatting");
    }

    @Test
    public void test07_separation_creation() throws Exception {
        Bold bold = new Bold();
        assertTrue(bold.detect("**sepa", "**sepa ration**"));
        assertTrue(bold.getSeparation().isSeparation());
    }

}