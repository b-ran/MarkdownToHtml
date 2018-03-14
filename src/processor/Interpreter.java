package processor;

import conversion.ConversionHtml;
import markdownfeatures.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interpreter {

    private Scanner scanner;
    private ConversionHtml conversionFormat = new ConversionHtml();

    private static final List<Feature> features = new ArrayList<>(Arrays.asList(
            new Heading(),
            new Italic(),
            new Bold(),
            new Paragraph()
    ));

    public Interpreter(String path) {
        try {
            this.scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder convert() {
        String next = "";
        String line = "";
        StringBuilder out = new StringBuilder();
        while (scanner.hasNextLine()) {

            line = scanner.nextLine();
            findFeature(line).convert(conversionFormat);
            Scanner lineScanner = new Scanner(line);

            while (lineScanner.hasNext()) {
                next = lineScanner.next();
                findFeature(next).convert(conversionFormat);;
            }
        }
        return null;
    }

    private Feature findFeature(String current) {
        for (Feature feature : features) {
            if (feature.detect(current)) {
                feature.set(current);
                return feature;
            }
        }
        return new Nothing();
    }
}
