package tests;

import markdownfeatures.BulletedList;
import org.junit.Test;

import static org.junit.Assert.*;

public class BulletedListTest {
    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new BulletedList().detect("*", "* item one"));
    }

    @Test
    public void test02_string_detection_valid_sublist() throws Exception {
        BulletedList bulletedList = new BulletedList();
        assertTrue(bulletedList.detect("*", "* item one"));
        assertFalse(bulletedList.isSublist());
        assertTrue(bulletedList.detect("*", "   * sublist"));
        assertTrue(bulletedList.isSublist());
        assertTrue(bulletedList.detect("*", "   * sublist"));
        assertTrue(bulletedList.isSublist());
        assertTrue(bulletedList.detect("*", "* item two"));
        assertFalse(bulletedList.isSublist());
    }

    @Test
    public void test03_string_detection_invalid() throws Exception {
        assertFalse(new BulletedList().detect("*.foobar","*.foobar"));
        assertFalse(new BulletedList().detect("1. foobar", "1. foobar"));
    }

    @Test
    public void test04_spacing_invalid() throws Exception {
        BulletedList bulletedList = new BulletedList();
        assertFalse(bulletedList.detect("*", "      * item one"));
        assertTrue(bulletedList.detect("*", "* item one"));
        assertFalse(bulletedList.detect("*", "       * sublist"));
    }

    @Test
    public void test05_spacing_valid() throws Exception {
        BulletedList bulletedList = new BulletedList();
        assertTrue(bulletedList.detect("*", "  * item one"));
        assertTrue(bulletedList.detect("*", "* item one"));
        assertTrue(bulletedList.detect("*", "      * sublist"));
    }

    @Test
    public void test06_list_then_sublist() throws Exception {
        BulletedList bulletedList = new BulletedList();
        assertFalse(bulletedList.detect("*", "   * sublist"));
        assertTrue(bulletedList.detect("*", "* item one"));
        assertTrue(bulletedList.detect("*", "   * sublist"));
    }

    @Test
    public void test07_convertible() throws Exception {
        assertTrue(new BulletedList().convertible());
    }

    @Test
    public void test08_separation_creation() throws Exception {
        BulletedList bulletedList = new BulletedList();
        assertTrue(bulletedList.detect("*", "* item one"));
        assertTrue(bulletedList.getSeparation().isSeparation());
    }

}
