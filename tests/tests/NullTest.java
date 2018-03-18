package tests;

import markdownfeatures.NullFeature;
import org.junit.Test;

import static org.junit.Assert.*;

public class NullTest {

    @Test
    public void test01_string_detection_invalid() throws Exception {
        assertFalse(new NullFeature().detect("", ""));
    }

    @Test
    public void test02_string_detection_invalid() throws Exception {
        assertFalse(new NullFeature().detect("shortcuts", "shortcuts around red"));
    }

    @Test
    public void test03_string_detection_invalid() throws Exception {
        assertFalse(new NullFeature().detect("$%\n", "$&\n asdas"));
    }

    @Test
    public void test04_input_unchangeable() throws Exception {
        NullFeature paragraph = new NullFeature();
        paragraph.setInput("&HSHD JS&^666");
        assertNotEquals(paragraph.getInput(), "&HSHD JS&^666");
    }

    @Test
    public void test05_convertible() throws Exception {
        assertFalse(new NullFeature().convertible());
    }

    @Test
    public void test06_separation() throws Exception {
        NullFeature nullFeature = new NullFeature();
        assertFalse(nullFeature.detect("\njdjidfs847", "\njdjidfs847 jjds&&$****   "));
        assertFalse(nullFeature.getSeparation().isSeparation());
    }
}
