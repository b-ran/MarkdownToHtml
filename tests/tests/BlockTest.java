package tests;

import markdownfeatures.Block;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockTest {

    @Test
    public void test01_string_detection_valid() throws Exception {
        assertTrue(new Block().detect("```", "```"));
    }

    @Test
    public void test02_string_detection_invalid() throws Exception {
        assertFalse(new Block().detect("```", "``` hello this should not work"));
    }

    @Test
    public void test03_string_detection_invalid() throws Exception {
        assertFalse(new Block().detect("``", "``"));
    }

    @Test
    public void test04_convertible() throws Exception {
        assertTrue(new Block().convertible());
    }

    @Test
    public void test05_no_separation() throws Exception {
        Block block = new Block();
        assertFalse(block.detect("```", "``` ``"));
        assertTrue(block.getSeparation().isSeparation());
    }

    @Test
    public void test06_input_unchangeable() throws Exception {
        Block block = new Block();
        block.setInput("```");
        assertEquals(block.getInput(), "");
    }
}
