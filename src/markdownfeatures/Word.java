package markdownfeatures;

import conversion.ConversionVisitor;

public class Word implements Feature{

    private String input = "";

    @Override
    public boolean detect(String input) {
        return false;
    }

    @Override
    public void setInput(String input) {this.input = input;}


    @Override
    public String getInput() {
        return input;
    }

    @Override
    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
        return conversionVisitor.translate(this, out);
    }

}
