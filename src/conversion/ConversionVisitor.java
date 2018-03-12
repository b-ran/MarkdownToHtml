package conversion;

import com.sun.org.apache.xpath.internal.operations.String;
import markdownfeatures.Heading;

public interface ConversionVisitor {
    String translate(Heading heading);
}
