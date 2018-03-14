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
    public void set(String input) {
        this.input = input;
    }

    @Override
    public String convert(ConversionVisitor conversionVisitor) {
        return conversionVisitor.translate(this, input);
    }
}
