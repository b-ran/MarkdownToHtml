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

    private boolean codeBlock = false;
    private boolean inlineBlock = false;

    @Override
    public StringBuilder translate(Heading heading, StringBuilder out) {
        if (skipping()) return out;
        nextLocation = out.length();
        HeadingTag headingTag = new HeadingTag(heading);
        StringBuilder output = combineWithStringBuilder(headingTag.startHtmlTag+headingTag.endHtmlTag, out);
        nextLocation += headingTag.nextHtmlLocation();
        return output;
    }

    @Override
    public StringBuilder translate(Italic italic, StringBuilder out) {
        if (skipping()) return out;
        if (newParagraph) out = translate(new Paragraph(), out);
        String inner = chooseCurrentSeparationTag(italic, ItalicTag.startHtmlTag, ItalicTag.endHtmlTag);
        StringBuilder output = combineWithStringBuilder(inner, out);
        nextLocation += inner.length();
        return output;
    }

    @Override
    public StringBuilder translate(Bold bold, StringBuilder out) {
        if (skipping()) return out;
        if (newParagraph) out = translate(new Paragraph(), out);
        String inner = chooseCurrentSeparationTag(bold, BoldTag.startHtmlTag, BoldTag.endHtmlTag);
        StringBuilder output = combineWithStringBuilder(inner, out);
        nextLocation += inner.length();
        return output;
    }

    @Override
    public StringBuilder translate(Paragraph paragraph, StringBuilder out) {
        if (skipping()) return out;
        if (!startOfList) startOfList = true;
        if (newParagraph) {
            out.append(ParagraphTag.startHtmlTag);
            newParagraph = false;
        } else if (endParagraph) {
            out.append(ParagraphTag.endHtmlTag);
            newParagraph = true;
            endParagraph = false;
        }

        nextLocation = out.length();
        return out;
    }

    @Override
    public StringBuilder translate(Word word, StringBuilder out) {
        String text = word.getInput();
        if (newParagraph && nextLocation == out.length()) out = translate(new Paragraph(), out);
        StringBuilder output = combineWithStringBuilder(text, out);
        nextLocation += text.length();
        output.insert(nextLocation, " ");
        nextLocation++;
        return output;
    }

    @Override
    public StringBuilder translate(Separator separator, StringBuilder out) {
        if (skipping()) return out;
        out.append(SeparatorTag.HtmlTag);
        nextLocation = out.length();
        return out;
    }

    @Override
    public StringBuilder translate(Blockquote blockquote, StringBuilder out) {
        if (skipping()) return out;
        nextLocation = out.length();
        out.append(BlockquoteTag.startHtmlTag+BlockquoteTag.endHtmlTag);
        nextLocation += BlockquoteTag.nextHtmlLocation();
        return out;
    }

    @Override
    public StringBuilder translate(Inline inline, StringBuilder out) {
        if (codeBlock) return out;
        inlineBlock = true;
        String inner = chooseCurrentSeparationTag(inline, InlineTag.startHtmlTag, InlineTag.endHtmlTag);
        if (inner.equals(InlineTag.endHtmlTag)) inlineBlock = false;
        StringBuilder output = combineWithStringBuilder(inner, out);
        nextLocation += inner.length();
        return output;
    }

    @Override
    public StringBuilder translate(Block block, StringBuilder out) {
        if (inlineBlock) return out;
        codeBlock = true;
        nextLocation = out.length();
        String inner = chooseCurrentSeparationTag(block, BlockTag.startHtmlTag, BlockTag.endHtmlTag);
        if (inner.equals(BlockTag.endHtmlTag)) codeBlock = false;
        out.append(inner);
        nextLocation = out.length();
        return out;
    }

    @Override
    public StringBuilder translate(NumberedList numberedList, StringBuilder out) {
        if (skipping()) return out;
        return convertList(numberedList, new NumberedListTag(), out);
    }

    @Override
    public StringBuilder translate(BulletedList bulletedList, StringBuilder out) {
        if (skipping()) return out;
        return convertList(bulletedList, new BulletedListTag(), out);
    }

    @Override
    public StringBuilder nextLine(StringBuilder out) {
        if (codeBlock)  {
            out.append("\n");
            nextLocation++;
        }
        return out;
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
            nextLocation += listTag.nextLocationHtmlTypeTag();
            startOfList = false;
        } else if (!list.isSublist()) {
            startOfSubList = true;
        }
        if (startOfSubList) {
            nextLocation = out.length() - listTag.nextLocationHtmlTypeTag();
        }
        if (startOfSubList && list.isSublist()) {
            nextLocation -= listTag.nextLocationHtmlNestedTag();
            startOfSubList = false;
            out.insert(nextLocation, listTag.htmlNestedTag());
            nextLocation += listTag.nextLocationHtmlNestedTag();
        } else if (!startOfSubList && list.isSublist()) {
            nextLocation += listTag.nextLocationHtmlItemTag()+2;
        }
        out.insert(nextLocation, listTag.htmlItemTag());
        nextLocation += listTag.nextLocationHtmlItemTag();
        return out;
    }


    private boolean skipping() {
        if (codeBlock || inlineBlock) return true;
        return false;
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
        out.insert(nextLocation, inner);
        return out;
    }
}
