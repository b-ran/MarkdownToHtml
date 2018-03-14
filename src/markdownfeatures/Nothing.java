package markdownfeatures;

import conversion.ConversionVisitor;

public class Nothing implements Feature{

    @Override
    public boolean detect(String input) {
        return false;
    }

    @Override
    public void set(String input) {}

    @Override
    public String convert(ConversionVisitor conversionVisitor) {return "";}
}
