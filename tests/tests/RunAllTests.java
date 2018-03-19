package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BoldTest.class,
        HeadingTest.class,
        ItalicTest.class,
        ParagraphTest.class,
        NullTest.class,
        WordTest.class,
        SeparatorTest.class,
        BlockquoteTest.class,
        InlineTest.class
})

public class RunAllTests {
}
