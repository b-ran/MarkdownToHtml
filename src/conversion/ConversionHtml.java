package conversion;


import markdownfeatures.*;
import java.lang.*;

public class ConversionHtml implements ConversionVisitor {


    private int nextLocation = 0;

    @Override
    public StringBuilder translate(Heading heading, StringBuilder out) {
        StringBuilder output = combine("<h" + heading.getLevel() + "></h" + heading.getLevel() + ">", out);
        nextLocation += 4;
        return output;
    }

    @Override
    public StringBuilder translate(Italic italic, StringBuilder out) {
        String inner = "<em>" + italic.getInput() + "</em>";
        StringBuilder output = combine(inner, out);
        nextLocation += inner.length();
        return output;
    }

    @Override
    public StringBuilder translate(Bold bold, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(Paragraph paragraph, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(Word word, StringBuilder out) {
        String inner = word.getInput();
        StringBuilder output = combine(inner, out);
        nextLocation += inner.length();
        return output;
    }

    private StringBuilder combine (String inner, StringBuilder out) {
        out.insert(nextLocation, inner);
        return out;
    }
}
