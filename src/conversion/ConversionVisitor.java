package conversion;

import java.lang.*;
import markdownfeatures.*;

public interface ConversionVisitor {

    String translate(Heading heading, String input);

    String translate(Italic italic, String input);

    String translate(Bold bold, String input);

    String translate(Paragraph paragraph, String input);

}
