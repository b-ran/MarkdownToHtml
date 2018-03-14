package conversion;


import markdownfeatures.*;
import java.lang.*;

public class ConversionHtml implements ConversionVisitor {


    @Override
    public String translate(Heading heading, String input) {
        return "<h" + heading.getLevel() + "> " + input + " " + "</h" + heading.getLevel() + ">";
    }

    @Override
    public String translate(Italic italic, String input) {
        return null;
    }

    @Override
    public String translate(Bold bold, String input) {
        return null;
    }

    @Override
    public String translate(Paragraph paragraph, String input) {
        return null;
    }
}
