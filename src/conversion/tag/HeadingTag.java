package conversion.tag;

import markdownfeatures.Heading;

public class HeadingTag implements Tag{

    public final String startHtmlTag;
    public final String endHtmlTag;

    public HeadingTag(Heading heading) {
        startHtmlTag =  "\n<h" + heading.getLevel() + ">";
        endHtmlTag =  "</h" + heading.getLevel() + ">";
    }

    public Integer nextHtmlLocation() {
        return startHtmlTag.length();
    }
}
