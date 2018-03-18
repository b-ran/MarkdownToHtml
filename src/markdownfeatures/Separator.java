package markdownfeatures;

import com.sun.xml.internal.ws.util.StringUtils;
import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.Separation;

public class Separator implements Feature {

    private final static String input = "";

    private final static String format = "-";

    @Override
    public boolean detect(String next, String line) {
        return line.matches("[" + format + "]*") && line.length() > 2;
    }

    @Override
    public void setInput(String input) {
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
        return conversionVisitor.translate(this, out);
    }

    @Override
    public Separation getSeparation() {
        return new SeparateNull();
    }

    @Override
    public boolean convertible() {
        return true;
    }
}
