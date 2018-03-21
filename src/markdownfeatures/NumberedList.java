package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.SeparateTags;
import markdownfeatures.separation.Separation;

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
            return (line.charAt(maxSpacesList) != ' ');
        } else if (startLetterOfLine == sublistFormat && line.length() > maxSpacesSubList-1) {
            return (line.charAt(maxSpacesSubList) != ' ');
        }

        return true;
    }

     boolean checkValidListFormat(String line) {
        String in = line.trim();
        if (in.length() > 2) {
            return Character.isDigit(in.charAt(0)) && in.charAt(1) == listFormat && in.charAt(2) == ' ';
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
