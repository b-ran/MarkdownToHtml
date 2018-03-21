package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.SeparateTags;
import markdownfeatures.separation.Separation;

public abstract class List implements Feature {
    protected Separation separation = new SeparateNull();
    private Boolean sublist = false;

    protected Integer maxSpacesList;
    protected Integer maxSpacesSubList;
    protected Character listFormat;
    protected Character sublistFormat;


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

    abstract boolean checkValidLineSpacing(String line);

    abstract boolean checkValidListFormat(String line);

    abstract boolean checkValidSubListFormat(String line);

    public boolean isSublist() {
        return sublist;
    }
}
