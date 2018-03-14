package markdownfeatures;

import conversion.ConversionVisitor;

public class Nothing implements Feature{

    @Override
    public boolean detect(String input) {
        return false;
    }

    @Override
    public void setInput(String input) {}


    @Override
    public String getInput() {
        return "";
    }

    @Override
    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
        return out;
    }

}
