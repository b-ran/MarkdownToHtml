package conversion;


import markdownfeatures.*;
import markdownfeatures.separation.Separation;

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
        String inner = getText(italic, "<em>", "</em>");
        StringBuilder output = combine(inner, out);
        nextLocation += inner.length()+1;
        return output;
    }

    @Override
    public StringBuilder translate(Bold bold, StringBuilder out) {
        String inner = getText(bold, "<strong>", "</strong>");
        StringBuilder output = combine(inner, out);
        nextLocation += inner.length()+1;
        return output;
    }

    @Override
    public StringBuilder translate(Paragraph paragraph, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(Word word, StringBuilder out) {
        String inner = word.getInput();
        StringBuilder output = combine(inner, out);
        nextLocation += inner.length()+1;
        return output;
    }

    private String getText(Feature feature, String startTag, String exitTag) {
        String inner = "";
        Separation separation = feature.getSeparation();
        if (separation.isSeparation()) {
            if (separation.left()) {
                inner = startTag + feature.getInput();
            } else if (separation.right()) {
                inner =  feature.getInput() + exitTag;
            }
        }
        else inner = startTag + feature.getInput() + exitTag;
        return inner;
    }

    private StringBuilder combine (String inner, StringBuilder out) {
        inner += " ";
        out.insert(nextLocation, inner);
        return out;
    }
}
