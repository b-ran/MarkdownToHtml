package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.Separation;

public class BulletedList extends List{

    public BulletedList() {
        super(2, 6, '*', '*');
    }

    @Override
    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
        return conversionVisitor.translate(this, out);
    }

    @Override
    boolean checkValidLineSpacing(String line) {
        if (!line.startsWith(" ")) return true;
        String in = line.trim();
        if (in.charAt(0) == listFormat  && maxSpacesList >= getTotalStartingSpace(line)) {
            return true;
        }
        Integer startWordIndex = getStartWordIndex(line);
        if (startWordIndex >= oldStartWordIndex) {
            return (oldStartWordIndex + maxSpacesSubList >= getTotalStartingSpace(line));
        }

        return false;
    }

    boolean checkValidListFormat(String next, String line) {
        String in = line.trim();
        if (in.length() > 2 && maxSpacesList >=  getTotalStartingSpace(line)) {
            return in.charAt(0) == listFormat && in.charAt(1) == ' ';
        }
        return false;
    }

    boolean checkValidSubListFormat(String next, String line) {
        String in = line.trim();
        if (in.length() > 2 && separation.isSeparation()) {
            return in.charAt(0) == sublistFormat && in.charAt(1) == ' ';
        }
        return false;
    }
}
