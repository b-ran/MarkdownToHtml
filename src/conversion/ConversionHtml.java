package conversion;


import markdownfeatures.*;
import markdownfeatures.separation.Separation;

import java.lang.*;

public class ConversionHtml implements ConversionVisitor {


    private int nextLocation = 0;
    private boolean newParagraph = true;
    private boolean endParagraph = false;

    private boolean continuousTag = false;
    private String continuousClosingTag = "";

    @Override
    public StringBuilder translate(Heading heading, StringBuilder out) {
        StringBuilder output = closeContinuousTag(out);
        output = combine("<h" + heading.getLevel() + "></h" + heading.getLevel() + ">", output);
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
        if (newParagraph) {
            out.append("<p>");
            newParagraph = !newParagraph;
        } else if (endParagraph) {
            out.append("</p><p>");
        }
        endParagraph = false;
        nextLocation = out.length();
        return out;
    }

    @Override
    public StringBuilder translate(Word word, StringBuilder out) {
        String inner = word.getInput();
        StringBuilder output = combine(inner, out);
        nextLocation += inner.length()+1;
        return output;
    }

    @Override
    public StringBuilder translate(Separator separator, StringBuilder out) {
        StringBuilder output = closeContinuousTag(out);
        output = end(output);
        output.append("<hr>");
        nextLocation = output.length();
        return output;
    }

    @Override
    public StringBuilder translate(Blockquote blockquote, StringBuilder out) {
        StringBuilder output = end(out);
        output.append("<blockquote>");
        continuousTag = true;
        continuousClosingTag = "</blockquote>";
        nextLocation = output.length();
        return output;
    }

    @Override
    public StringBuilder translate(Inline inline, StringBuilder out) {
        String inner = getText(inline, "<code>", "</code>");
        StringBuilder output = combine(inner, out);
        nextLocation += inner.length()+1;
        return output;
    }

    @Override
    public StringBuilder end(StringBuilder out) {
        if (!newParagraph) {
            out.append("</p>");
        }
        return out;
    }

    private StringBuilder closeContinuousTag(StringBuilder out) {
        if (continuousTag) {
            out.append(continuousClosingTag);
            continuousClosingTag = "";
            nextLocation = out.length();
        }
        return out;
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
        endParagraph = true;
        inner += " ";
        out.insert(nextLocation, inner);
        return out;
    }
}
