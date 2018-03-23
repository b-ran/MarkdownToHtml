package conversion.tag;

public class InlineTag implements Tag{
    public static final String startHtmlTag = "<code>";
    public static final String endHtmlTag = "</code>";

    public static Integer nextHtmlLocation() {
        return startHtmlTag.length()+1;
    }
}
