package conversion;

import java.lang.*;
import markdownfeatures.*;

public interface ConversionVisitor {

    StringBuilder translate(Heading heading, StringBuilder out);

    StringBuilder translate(Italic italic, StringBuilder out);

    StringBuilder translate(Bold bold, StringBuilder out);

    StringBuilder translate(Paragraph paragraph, StringBuilder out);

    StringBuilder translate(Word word, StringBuilder out);

    StringBuilder endFile(StringBuilder out);
}
