package markdownfeatures;

import conversion.ConversionVisitor;

public class Bold implements Feature {

    private String input = "";

    @Override
    public boolean detect(String input) {
        String in = input.trim();
        return in.startsWith("**") && in.endsWith("**") && input.length() > 2;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
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
