package markdownfeatures;

import conversion.ConversionVisitor;

public interface Feature {

    boolean detect(String input);

    void setInput(String input);

    String getInput();

    StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out);

}
