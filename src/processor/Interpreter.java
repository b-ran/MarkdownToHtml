package processor;

import markdownfeatures.Bold;
import markdownfeatures.Feature;
import markdownfeatures.Heading;
import markdownfeatures.Italic;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interpreter {

    private Scanner scanner;

    private static final List<Feature> features = new ArrayList<>(Arrays.asList(
            new Heading(),
            new Italic(),
            new Bold()
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
        while (scanner.hasNext()) {
            next = scanner.next();
            for (Feature feature : features) {
                System.out.println(feature.toString() + " " + next + " " + feature.detect(next));
            }
        }
        return null;
    }
}
