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

    private static final List<Feature> wordFeatures = new ArrayList<>(Arrays.asList(
            new Italic(),
            new Heading(),
            new Bold(),
            new Blockquote(),
            new Inline()
    ));

    private static final List<Feature> lineFeatures = new ArrayList<>(Arrays.asList(
            new Heading(),
            new Paragraph(),
            new Separator(),
            new Block()
    ));

    public Interpreter(String path) {
        try {
            this.scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder convert() {
        String line = "";
        StringBuilder out = new StringBuilder();
        while (scanner.hasNextLine()) {
            String next = "";
            line = scanner.nextLine();

            Feature feature =  findLineFeature(next, line);
            if (feature.convertible()) {
                feature.convert(conversionFormat, out);
                out = conversionFormat.newline(out);
                continue;
            }

            Scanner lineScanner = new Scanner(line);

            while (lineScanner.hasNext()) {
                next = lineScanner.next();
                out = findWordFeature(next, line).convert(conversionFormat, out);
            }
            out = conversionFormat.newline(out);
        }
        return conversionFormat.end(out);
    }

    private Feature findLineFeature(String next, String line) {
        for (Feature feature : lineFeatures) {
            if (feature.detect(next, line)) {
                feature.setInput(line);
                return feature;
            }
        }
        return new NullFeature();
    }

    private Feature findWordFeature(String next, String line) {
        for (Feature feature : wordFeatures) {
            if (feature.detect(next, line)) {
                feature.setInput(next);
                return feature;
            }
        }
        return new Word(next);
    }
}
