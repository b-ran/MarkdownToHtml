package tests;

import markdownfeatures.Separator;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeparatorTest {

    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new Separator().detect("-------", "-------"));
    }

    @Test
    public void test02_string_detection_valid_min_level() throws Exception {
        assertTrue(new Separator().detect("---", "---"));
    }

    @Test
    public void test03_string_detection_invalid_level() throws Exception {
        assertFalse(new Separator().detect("--", "--"));
        assertFalse(new Separator().detect("-", "-"));
    }

    @Test
    public void test04_string_detection_invalid() throws Exception {
        assertFalse(new Separator().detect("---asdasd---asd", "---asdasd---asd"));
    }

    @Test
    public void test05_string_detection_invalid() throws Exception {
        assertFalse(new Separator().detect("-*-asd-asdfghw*", "-*-asd-asdfghw*"));
    }

    @Test
    public void test06_convertible() throws Exception {
        assertTrue(new Separator().convertible());
    }

    @Test
    public void test07_no_separation() throws Exception {
        Separator separator = new Separator();
        assertFalse(separator.detect("----", "---- ---- ------"));
        assertFalse(separator.getSeparation().isSeparation());
    }

    @Test
    public void test04_input_unchangeable() throws Exception {
        Separator separator = new Separator();
        separator.setInput("--------");
        assertEquals(separator.getInput(), "");
    }
}
