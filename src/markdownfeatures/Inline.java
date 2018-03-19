package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.SeparateTags;
import markdownfeatures.separation.Separation;

public class Inline implements Feature {

    private String input = "";
    private final static String format = "`";
    private Separation separation = new SeparateNull();

    @Override
    public boolean detect(String next, String line) {
        String in = next.trim();
        if (in.length() == 0) return false;
        if (separation.detect(next)) return true;
        if (!in.startsWith(format)) return false;
        if (in.length() > 1) {
            if (in.charAt(1) == format.charAt(0)) return false;
        }
        if(SeparateTags.detectSeparation(in, line, format)) {
            separation = new SeparateTags(format);
            return true;
        }
        if (in.length() > 1) {
            if (in.charAt(in.length()-2) == format.charAt(0) || !in.endsWith(format)) return false;
        }
        return true;
    }

    public Separation getSeparation() {
        return separation;
    }

    @Override
    public boolean convertible() {
        return true;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
        format();
    }

    private void format() {
        if (separation.isSeparation()) input = separation.format(input);
        else {
            input = input.substring(format.length());
            input = input.substring(0, input.length()-format.length());
        }
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
        return conversionVisitor.translate(this, out);
    }
}
