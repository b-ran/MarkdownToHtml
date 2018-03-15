package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.SeparateTags;
import markdownfeatures.separation.Separation;

public class Bold implements Feature {

    private String input = "";

    private final String format = "**";

    private Separation separation = new SeparateNull();

    @Override
    public boolean detect(String next, String line) {
        String in = next.trim();
        if (separation.detect(in)) return true;
        if (!in.startsWith(format)) return false;

        if (in.endsWith(format) && in.length() > 4) return true;

        if(SeparateTags.detectSeparation(in, line, format)) {
            separation = new SeparateTags(format);
            return true;
        }

        return false;
    }

    public Separation getSeparation() {
        return separation;
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
