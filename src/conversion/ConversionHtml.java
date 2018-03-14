package conversion;


import markdownfeatures.*;
import java.lang.*;

public class ConversionHtml implements ConversionVisitor {


    @Override
    public StringBuilder translate(Heading heading, StringBuilder out) {
        //return "<h" + heading.getLevel() + "> " + input + " " + "</h" + heading.getLevel() + ">";
        return null;
    }

    @Override
    public StringBuilder translate(Italic italic, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(Bold bold, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(Paragraph paragraph, StringBuilder out) {
        return null;
    }
}
