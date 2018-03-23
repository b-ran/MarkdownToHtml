package conversion.tag;

public class BlockTag implements Tag {
    public static final String startHtmlTag = "\n<code>\n";
    public static final String endHtmlTag = "</code>\n";

    public static Integer nextHtmlLocation() {
        return startHtmlTag.length();
    }
}
