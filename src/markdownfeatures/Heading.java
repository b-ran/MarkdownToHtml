package markdownfeatures;

import conversion.ConversionVisitor;

public class Heading implements Feature {

    @Override
    public boolean detect(String input) {
        return false;
    }

    @Override
    public void set(String input) {

    }

    @Override
    public void convert(ConversionVisitor conversionVisitor) {
        conversionVisitor.translate(this);
    }
}
