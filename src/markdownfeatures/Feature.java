package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.Separation;

public interface Feature {

    boolean detect(String next, String line);

    void setInput(String input);

    String getInput();

    StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out);

    Separation getSeparation();
}
