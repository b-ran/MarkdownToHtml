package markdownfeatures;

import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.SeparateTags;
import markdownfeatures.separation.Separation;

public abstract class ListFeature implements Feature {
    protected Separation separation = new SeparateNull();
    private Boolean sublist = false;

    protected Integer maxSpacesList;
    protected Integer maxSpacesSubList;
    protected Character listFormat;
    protected Character sublistFormat;

    protected Integer oldStartWordIndex = 0;


    public ListFeature(Integer maxSpacesList, Integer maxSpacesSubList, Character listFormat, Character sublistFormat) {
        this.maxSpacesList = maxSpacesList;
        this.maxSpacesSubList = maxSpacesSubList;
        this.listFormat = listFormat;
        this.sublistFormat = sublistFormat;
    }


    @Override
    public boolean detect(String next, String line) {
        if (!checkValidLineSpacing(line)) return false;
        if (checkValidListFormat(next, line)) {
            oldStartWordIndex = getStartWordIndex(line);
            sublist = false;
            if (!separation.isSeparation()) separation = new SeparateTags("");
            return true;
        }
        if (checkValidSubListFormat(next, line)) {
            sublist = true;
            return true;
        }
        return false;
    }

    Integer getStartWordIndex(String line) {
        Integer index = 0;
        for (Character character: line.toCharArray()) {
            if (!Character.isSpaceChar(character)) return index;
            index++;
        }
        return index;
    }

    Integer getTotalStartingSpace(String line) {
       return line.substring(0,getStartWordIndex(line)).length();
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

    abstract boolean checkValidLineSpacing(String line);

    abstract boolean checkValidListFormat(String next, String line);

    abstract boolean checkValidSubListFormat(String next, String line);

    public boolean isSublist() {
        return sublist;
    }
}
