package markdownfeatures;

import conversion.ConversionVisitor;

import java.util.Scanner;

public class NumberedList extends List {

    public NumberedList() {
        super(4, 6, '.', '-');
    }

    @Override
    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
        return conversionVisitor.translate(this, out);
    }

     boolean checkValidLineSpacing(String line) {
        if (!line.startsWith(" ")) return true;
        Character startLetterOfLine = line.trim().charAt(0);
        if (Character.isDigit(startLetterOfLine) && line.length() > maxSpacesList-1) {
            return (maxSpacesList >= getTotalStartingSpace(line));
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
        if (lineTrimmed.length() > 2) {
            return Character.isDigit(nextTrimmed.charAt(0)) && nextTrimmed.endsWith(listFormat.toString()) && lineTrimmed.charAt(nextTrimmed.length()) == ' ';
        }
        return false;

    }

     boolean checkValidSubListFormat(String next, String line) {
         String nextTrimmed = next.trim();
         String lineTrimmed = line.trim();
        if (lineTrimmed.length() > 1 && separation.isSeparation()) {
            return nextTrimmed.charAt(0) == sublistFormat && lineTrimmed.charAt(1) == ' ';
        }
        return false;
    }

}
