package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.SeparateTags;
import markdownfeatures.separation.Separation;

public abstract class List implements Feature {
    private Separation separation = new SeparateNull();
    private Boolean sublist = false;

    private Integer maxSpacesList;
    private Integer maxSpacesSubList;
    private Character listFormat;
    private Character sublistFormat;


    public List(Integer maxSpacesList, Integer maxSpacesSubList, Character listFormat, Character sublistFormat) {
        this.maxSpacesList = maxSpacesList;
        this.maxSpacesSubList = maxSpacesSubList;
        this.listFormat = listFormat;
        this.sublistFormat = sublistFormat;
    }


    @Override
    public boolean detect(String next, String line) {
        if (!checkValidLineSpacing(line)) return false;
        if (checkValidListFormat(line)) {
            sublist = false;
            if (!separation.isSeparation()) separation = new SeparateTags("");
            return true;
        }
        if (checkValidSubListFormat(line)) {
            sublist = true;
            return true;
        }
        return false;
    }

    @Override
    public void setInput(String input) {
    }

    @Override
    public String getInput() {
        return "";
    }

    @Override
    public Separation getSeparation() {
        return separation;
    }

    @Override
    public boolean convertible() {
        return true;
    }

    private boolean checkValidLineSpacing(String line) {
        if (!line.startsWith(" ")) return true;
        Character startLetterOfLine = line.trim().charAt(0);

        if (Character.isDigit(startLetterOfLine) && line.length() > maxSpacesList-1) {
            return (line.charAt(maxSpacesList) != ' ');
        } else if (startLetterOfLine == sublistFormat && line.length() > maxSpacesSubList-1) {
            return (line.charAt(maxSpacesSubList) != ' ');
        }

        return true;
    }

    private boolean checkValidListFormat(String line) {
        String in = line.trim();
        if (in.length() > 2) {
            return Character.isDigit(in.charAt(0)) && in.charAt(1) == listFormat && in.charAt(2) == ' ';
        }
        return false;
    }

    private boolean checkValidSubListFormat(String line) {
        String in = line.trim();
        if (in.length() > 1 && separation.isSeparation()) {
            return in.charAt(0) == sublistFormat && in.charAt(1) == ' ';
        }
        return false;
    }

    public boolean isSublist() {
        return sublist;
    }
}
