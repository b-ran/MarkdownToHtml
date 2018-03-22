package conversion;


import markdownfeatures.*;
import markdownfeatures.separation.Separation;

import java.lang.*;

public class ConversionHtml implements ConversionVisitor {

    //TODO: fix code smell better system of defining tags
    //TODO: move numberList translation to able to used for all types of lists

    private int nextLocation = 0;
    private boolean newParagraph = true;
    private boolean endParagraph = false;

    private boolean startOfList = true;
    private boolean startOfSubList = true;

    @Override
    public StringBuilder translate(Heading heading, StringBuilder out) {
        String startTag = "\n<h" + heading.getLevel() + ">";
        String outTag = "</h" + heading.getLevel() + ">";
        StringBuilder output = combine(startTag+outTag, out);
        nextLocation += startTag.length();
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
            out.append("\n<p>\n");
            newParagraph = !newParagraph;
        } else if (endParagraph) {
            out.append("\n</p>\n<p>");
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
        StringBuilder output = end(out);
        output.append("\n<hr>");
        nextLocation = output.length();
        translate(new Paragraph(), out);
        return output;
    }

    @Override
    public StringBuilder translate(Blockquote blockquote, StringBuilder out) {
        String startTag = "\n<blockquote>";
        String endTag = "</blockquote>";
        out.append(startTag+endTag);
        nextLocation += startTag.length();
        return out;
    }

    @Override
    public StringBuilder translate(Inline inline, StringBuilder out) {
        String inner = getText(inline, "<code>", "</code>");
        StringBuilder output = combine(inner, out);
        nextLocation += inner.length()+1;
        return output;
    }

    @Override
    public StringBuilder translate(Block block, StringBuilder out) {
        String inner = getText(block, "<code>", "</code>");
        out.append(inner);
        nextLocation = out.length();
        return out;
    }

    @Override
    public StringBuilder translate(NumberedList numberedList, StringBuilder out) {
        Integer tagLength = 4;
        if (startOfList) {
            out.append("<ol>\n</ol>");
            nextLocation+=5;
            startOfList = false;
        } else {
            nextLocation = out.length()-5;
        }
        if (!numberedList.isSublist()) startOfSubList = true;


        if (startOfSubList && numberedList.isSublist()) {
            nextLocation -= 6;
            startOfSubList = false;
            out.insert(nextLocation,"\n<ul>\n</ul>\n");
            nextLocation+=6;
        }
        out.insert(nextLocation,"<li></li>\n");
        nextLocation+=4;
        return out;
    }

    @Override
    public StringBuilder translate(BulletedList bulletedList, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder end(StringBuilder out) {
        if (!newParagraph) {
            out.append("\n</p>");
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
