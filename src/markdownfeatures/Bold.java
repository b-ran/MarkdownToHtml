package markdownfeatures;

import conversion.ConversionVisitor;

public class Bold implements Feature {

    @Override
    public boolean detect(String input) {
        String in = input.trim();
        return in.startsWith("**") && in.endsWith("**") && input.length() > 2;
    }

    @Override
    public void set(String input) {

    }

    @Override
    public void convert(ConversionVisitor conversionVisitor) {

    }
}
