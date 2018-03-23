package conversion.tag;

public class BlockquoteTag implements Tag {

    public static final String startHtmlTag = "\n<blockquote>";
    public static final String endHtmlTag = "</blockquote>";

    public static Integer nextHtmlLocation() {
        return startHtmlTag.length();
    }
}
