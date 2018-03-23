package markdownfeatures;

import conversion.ConversionVisitor;

public class BulletedList extends ListFeature {

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
        String nextTrimmed = next.trim();
        String lineTrimmed = line.trim();
        if (lineTrimmed.length() > 2 && maxSpacesList >=  getTotalStartingSpace(line)) {
            return nextTrimmed.charAt(0) == listFormat && lineTrimmed.charAt(1) == ' ';
        }
        return false;
    }

    boolean checkValidSubListFormat(String next, String line) {
        String nextTrimmed = next.trim();
        String lineTrimmed = line.trim();
        if (lineTrimmed.length() > 2 && separation.isSeparation()) {
            return nextTrimmed.charAt(0) == sublistFormat && lineTrimmed.charAt(1) == ' ';
        }
        return false;
    }
}
