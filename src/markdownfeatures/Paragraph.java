package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.Separation;

public class Paragraph implements Feature {

    private static final String input = "";

    @Override
    public boolean detect(String next, String line) {
        return line.trim().isEmpty();
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
        return new SeparateNull();
    }

    @Override
    public boolean convertible() {
        return true;
    }

}
