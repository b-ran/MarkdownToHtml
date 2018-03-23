package conversion.tag;

public class BoldTag implements Tag{
    public static final String startHtmlTag = "<strong>";
    public static final String endHtmlTag = "</strong>";

    public static Integer nextHtmlLocation() {
        return startHtmlTag.length()+1;
    }
}
