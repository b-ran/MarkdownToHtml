package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.Separation;

public class Blockquote implements Feature {

    private final static String input = "";

    private final static String format = "> ";

    @Override
    public boolean detect(String next, String line) {
        if (line.length() <= format.length()) return false;
        return line.charAt(0) == format.charAt(0) && line.charAt(1) == format.charAt(1);
    }

    @Override
    public void setInput(String input) {
    }

    @Override
    public String getInput() {
        return input;
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
