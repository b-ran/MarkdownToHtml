package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.Separation;

public class NullFeature implements Feature {

    @Override
    public boolean detect(String next, String line) {
        return false;
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
        return out;
    }

    @Override
    public Separation getSeparation() {
        return new SeparateNull();
    }

    @Override
    public boolean convertible() {
        return false;
    }
}
