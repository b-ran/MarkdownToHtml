package tests;

import markdownfeatures.NumberedList;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberedListTest {

    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new NumberedList().detect("1.", "1. item one"));
    }

    @Test
    public void test02_string_detection_valid_sublist() throws Exception {
        NumberedList numberedList = new NumberedList();
        assertTrue(numberedList.detect("8.", "8. item eight"));
        assertFalse(numberedList.isSublist());
        assertTrue(numberedList.detect("-", "    - sublist"));
        assertTrue(numberedList.isSublist());
        assertTrue(numberedList.detect("-", "    - sublist"));
        assertTrue(numberedList.isSublist());
        assertTrue(numberedList.detect("9.", "9. item nine"));
        assertFalse(numberedList.isSublist());
    }

    @Test
    public void test03_string_detection_invalid() throws Exception {
        assertFalse(new NumberedList().detect("2.asd", "*2.asd not a list item"));
        assertFalse(new NumberedList().detect("#,hello", "#,hello"));
    }

    @Test
    public void test04_spacing_invalid() throws Exception {
        NumberedList numberedList = new NumberedList();
        assertFalse(numberedList.detect("     1.", "     1. item one"));
        assertTrue(numberedList.detect("1.", "1. item one"));
        assertFalse(numberedList.detect("      -", "       - sublist"));
    }

    @Test
    public void test05_spacing_valid() throws Exception {
        NumberedList numberedList = new NumberedList();
        assertTrue(numberedList.detect("    1.", "    1. item one"));
        assertTrue(numberedList.detect("1.", "1. item one"));
        assertTrue(numberedList.detect("     -", "      - sublist"));
    }

    @Test
    public void test06_list_then_sublist() throws Exception {
        NumberedList numberedList = new NumberedList();
        assertFalse(numberedList.detect("-", "   - sublist"));
        assertTrue(numberedList.detect("1.", "1. item one"));
        assertTrue(numberedList.detect("-", "   - sublist"));
    }

    @Test
    public void test07_convertible() throws Exception {
        assertTrue(new NumberedList().convertible());
    }

    @Test
    public void test08_separation_creation() throws Exception {
        NumberedList numberedList = new NumberedList();
        assertTrue(numberedList.detect("1.", "1. item one"));
        assertTrue(numberedList.getSeparation().isSeparation());
    }

    @Test
    public void test09_string_detection_valid_numbering() throws Exception {
        NumberedList numberedList = new NumberedList();
        assertTrue(numberedList.detect("899.", "899. item"));
        assertTrue(numberedList.detect("66.", "66. item"));
        assertTrue(numberedList.detect("4432.", "4432. item"));
    }
}
