package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.SeparateTags;
import markdownfeatures.separation.Separation;

public class Block implements Feature {

    private final static String format = "```";
    private Separation separation = new SeparateTags(format);

    @Override
    public boolean detect(String next, String line) {
        return line.equals(format);
    }

    @Override
    public void setInput(String input) {
    }

    @Override
    public String getInput() {
        return "";
    }

    @Override
    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
        return conversionVisitor.translate(this, out);
    }

    @Override
    public Separation getSeparation() {
        return separation;
    }

    @Override
    public boolean convertible() {
        return true;
    }
}
