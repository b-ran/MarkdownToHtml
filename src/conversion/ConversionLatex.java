package conversion;

import markdownfeatures.*;

public class ConversionLatex implements ConversionVisitor {

    private String startOfLatex = "\\documentclass{article} \n \\usepackage{listings} \n \\begin{document}";
    private String endOfLatex = "\\end{document}";

    @Override
    public StringBuilder translate(Heading heading, StringBuilder out) {
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

    @Override
    public StringBuilder translate(Word word, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(Separator separator, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(Blockquote blockquote, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(Inline inline, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(Block block, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(NumberedList numberedList, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder translate(BulletedList bulletedList, StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder nextLine(StringBuilder out) {
        return null;
    }

    @Override
    public StringBuilder startFile(StringBuilder out) {
        return out.append(startOfLatex);
    }

    @Override
    public StringBuilder endFile(StringBuilder out) {
        return out.append(endOfLatex);
    }
}
