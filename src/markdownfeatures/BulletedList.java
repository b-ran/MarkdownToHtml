package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.Separation;

public class BulletedList extends List{

    public BulletedList() {
        super(4, 6, '*', '*');
    }

    @Override
    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
        return conversionVisitor.translate(this, out);
    }

    @Override
    boolean checkValidLineSpacing(String line) {
        return false;
    }

    @Override
    boolean checkValidListFormat(String line) {
        return false;
    }

    @Override
    boolean checkValidSubListFormat(String line) {
        return false;
    }
}
