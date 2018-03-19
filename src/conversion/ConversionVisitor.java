package conversion;

import java.lang.*;
import markdownfeatures.*;

public interface ConversionVisitor {

    StringBuilder translate(Heading heading, StringBuilder out);

    StringBuilder translate(Italic italic, StringBuilder out);

    StringBuilder translate(Bold bold, StringBuilder out);

    StringBuilder translate(Paragraph paragraph, StringBuilder out);

    StringBuilder translate(Word word, StringBuilder out);

    StringBuilder translate(Separator separator, StringBuilder out);

    StringBuilder translate(Blockquote blockquote, StringBuilder out);

    StringBuilder translate(Inline inline, StringBuilder out);

    StringBuilder translate(Block block, StringBuilder out);

    StringBuilder newline(StringBuilder out);

    StringBuilder end(StringBuilder out);
}
