package markdownfeatures;

import conversion.ConversionVisitor;

public class Paragraph implements Feature {

    private String input = "";

    @Override
    public boolean detect(String input) {
        return input.isEmpty();
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
