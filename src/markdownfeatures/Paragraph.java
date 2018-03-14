package markdownfeatures;

import conversion.ConversionVisitor;

public class Paragraph implements Feature {

    private String input = "";

    @Override
    public boolean detect(String input) {
        return input.isEmpty();
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
