package tests;


import markdownfeatures.Heading;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeadingTest {

    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new Heading().detect("#", "# heading"));
    }

    @Test
    public void test02_string_detection_invalid() throws Exception {
        assertFalse(new Heading().detect("#SD#", "#SD# heading"));
    }

    @Test
    public void test03_string_detection_invalid_level() throws Exception {
        assertFalse(new Heading().detect("#######", "####### heading"));
    }

    @Test
    public void test04_string_detection_valid_level() throws Exception {
        assertTrue(new Heading().detect("######", "###### heading"));
    }

    @Test
    public void test05_string_detection_invalid_place() throws Exception {
        assertFalse(new Heading().detect("##", "hello this shouldn't work ## heading"));
    }

    @Test
    public void test06_convertible() throws Exception {
        assertTrue(new Heading().convertible());
    }

    @Test
    public void test07_no_separation() throws Exception {
        Heading heading = new Heading();
        assertFalse(heading.detect("# sepa", "# sepa ration#"));
        assertFalse(heading.getSeparation().isSeparation());
    }

    @Test
    public void test08_formatting() throws Exception {
        Heading heading = new Heading();
        heading.setInput("##");
        assertEquals(heading.getInput(), "");
    }

    @Test
    public void test09_count_level() throws Exception {
        String headingTags = "";
        String line = "";
        String addTag = "#";
        for(int i = 0; i < 6; i++) {
            Heading heading = new Heading();
            headingTags += addTag;
            line = headingTags + " heading";
            heading.detect(headingTags, headingTags);
            assertTrue(heading.getLevel() == headingTags.length());
        }
    }

    @Test
    public void test10_string_detection_empty_line_valid() throws Exception {
        assertTrue(new Heading().detect("###", "###"));
    }

}