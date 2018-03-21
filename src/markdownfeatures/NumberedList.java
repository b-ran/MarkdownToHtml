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

     boolean checkValidListFormat(String line) {
        String in = line.trim();
        Scanner scanner = new Scanner(in);
        String tag = scanner.next();
        if (in.length() > 2 && scanner.hasNext()) {
            return Character.isDigit(tag.charAt(0)) && tag.endsWith(listFormat.toString()) && in.charAt(tag.length()) == ' ';
        }
        return false;
    }

     boolean checkValidSubListFormat(String line) {
        String in = line.trim();
        if (in.length() > 1 && separation.isSeparation()) {
            return in.charAt(0) == sublistFormat && in.charAt(1) == ' ';
        }
        return false;
    }

}
