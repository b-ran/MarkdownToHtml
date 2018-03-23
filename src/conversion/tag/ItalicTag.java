package conversion.tag;

public class ItalicTag implements Tag{
    public static final String startHtmlTag = "<em>";
    public static final String endHtmlTag = "</em>";

    public static Integer nextHtmlLocation() {
        return startHtmlTag.length()+1;
    }
}
