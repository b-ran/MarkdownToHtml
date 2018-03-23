package conversion;


import conversion.tag.*;
import markdownfeatures.*;
import markdownfeatures.separation.Separation;

import java.lang.*;
import java.util.ArrayList;

public class ConversionHtml implements ConversionVisitor {

    private int nextLocation = 0;
    private boolean newParagraph = true;
    private boolean endParagraph = false;

    private boolean startOfList = true;
    private boolean startOfSubList = true;

    @Override
    public StringBuilder translate(Heading heading, StringBuilder out) {
        HeadingTag headingTag = new HeadingTag(heading);
        StringBuilder output = combineWithStringBuilder(headingTag.startHtmlTag+headingTag.endHtmlTag, out);
        nextLocation += headingTag.nextHtmlLocation();
        return output;
    }

    @Override
    public StringBuilder translate(Italic italic, StringBuilder out) {
        String inner = chooseCurrentSeparationTag(italic, ItalicTag.startHtmlTag, ItalicTag.endHtmlTag);
        StringBuilder output = combineWithStringBuilder(inner, out);
        nextLocation += ItalicTag.nextHtmlLocation();
        return output;
    }

    @Override
    public StringBuilder translate(Bold bold, StringBuilder out) {
        String inner = chooseCurrentSeparationTag(bold, BoldTag.startHtmlTag, BoldTag.endHtmlTag);
        StringBuilder output = combineWithStringBuilder(inner, out);
        nextLocation += BoldTag.nextHtmlLocation();
        return output;
    }

    @Override
    public StringBuilder translate(Paragraph paragraph, StringBuilder out) {
        if (newParagraph) {
            out.append(ParagraphTag.startHtmlTag);
            newParagraph = !newParagraph;
        } else if (endParagraph) {
            out.append(ParagraphTag.endHtmlTag);
        }
        endParagraph = false;
        nextLocation = out.length();
        return out;
    }

    @Override
    public StringBuilder translate(Word word, StringBuilder out) {
        String text = word.getInput();
        StringBuilder output = combineWithStringBuilder(text, out);
        nextLocation += text.length()+1;
        return output;
    }

    @Override
    public StringBuilder translate(Separator separator, StringBuilder out) {
        StringBuilder output = endFile(out);
        output.append(SeparatorTag.HtmlTag);
        nextLocation = output.length();
        translate(new Paragraph(), out);
        return output;
    }

    @Override
    public StringBuilder translate(Blockquote blockquote, StringBuilder out) {
        out.append(BlockquoteTag.startHtmlTag+BlockquoteTag.endHtmlTag);
        nextLocation += BlockquoteTag.nextHtmlLocation();
        return out;
    }

    @Override
    public StringBuilder translate(Inline inline, StringBuilder out) {
        String inner = chooseCurrentSeparationTag(inline, InlineTag.startHtmlTag, InlineTag.endHtmlTag);
        StringBuilder output = combineWithStringBuilder(inner, out);
        nextLocation += InlineTag.nextHtmlLocation();
        return output;
    }

    @Override
    public StringBuilder translate(Block block, StringBuilder out) {
        String inner = chooseCurrentSeparationTag(block, BlockTag.startHtmlTag, BoldTag.endHtmlTag);
        out.append(inner);
        nextLocation = BoldTag.nextHtmlLocation();
        return out;
    }

    @Override
    public StringBuilder translate(NumberedList numberedList, StringBuilder out) {
        return convertList(numberedList, new NumberedListTag(), out);
    }

    @Override
    public StringBuilder translate(BulletedList bulletedList, StringBuilder out) {
        return convertList(bulletedList, new BulletedListTag(), out);
    }

    @Override
    public StringBuilder startFile(StringBuilder out) {
        return out;
    }

    @Override
    public StringBuilder endFile(StringBuilder out) {
        if (!newParagraph) {
            out.append(ParagraphTag.endFileHtmlTag);
        }
        return out;
    }

    private StringBuilder convertList(ListFeature list, ListTag listTag, StringBuilder out) {
        if (startOfList) {
            out.append(listTag.htmlTypeTag());
            nextLocation = listTag.nextLocationHtmlTypeTag();
            startOfList = false;
        } else {
            nextLocation = out.length() - listTag.nextLocationHtmlTypeTag();
        }
        if (!list.isSublist()) startOfSubList = true;

        if (startOfSubList && list.isSublist()) {
            nextLocation -= listTag.nextLocationHtmlNestedTag();
            startOfSubList = false;
            out.insert(nextLocation, listTag.htmlNestedTag());
            nextLocation += listTag.nextLocationHtmlNestedTag();
        }
        out.insert(nextLocation, listTag.htmlItemTag());
        nextLocation += listTag.nextLocationHtmlItemTag();
        return out;
    }

    private String chooseCurrentSeparationTag(Feature feature, String startTag, String endTag) {
        String inner = "";
        Separation separation = feature.getSeparation();
        if (separation.isSeparation()) {
            if (separation.left()) {
                inner = startTag + feature.getInput();
            } else if (separation.right()) {
                inner =  feature.getInput() + endTag;
            }
        }
        else inner = startTag + feature.getInput() + endTag;
        return inner;
    }

    private StringBuilder combineWithStringBuilder(String inner, StringBuilder out) {
        endParagraph = true;
        inner += " ";
        out.insert(nextLocation, inner);
        return out;
    }
}
