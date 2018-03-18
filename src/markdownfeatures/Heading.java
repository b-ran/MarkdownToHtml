package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.Separation;

public class Heading implements Feature {

    private static final int LIMIT = 6;
    private int level = 0;
    private String input = "";

    @Override
    public boolean detect(String next, String line) {
        String in = next.trim();
        if (in.length() == 0) return false;
        for (char letter : in.toCharArray()) {
            level++;
            if (letter != '#') return false;
            if (level > LIMIT) return false;

        }
        return true;
    }


    public int getLevel() {
        return level;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
        this.input = this.input.replace('#' , ' ');
        this.input = this.input.trim();
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
        return conversionVisitor.translate(this, out);
    }

    @Override
    public Separation getSeparation() {
        return new SeparateNull();
    }

    @Override
    public boolean convertible() {
        return true;
    }
}


//package markdownfeatures;
//
//import conversion.ConversionVisitor;
//import markdownfeatures.separation.SeparateNull;
//import markdownfeatures.separation.Separation;
//
//import java.util.Scanner;
//
//public class Heading implements Feature {
//
//    private static final int LIMIT = 6;
//    private int level = 0;
//    private String input = "";
//
//    @Override
//    public boolean detect(String next, String line) {
//        String in = line.trim();
//        if (in.length() == 0) return false;
//        in = new Scanner(line).next();
//        for (char letter : in.toCharArray()) {
//            if (letter != '#') return false;
//            if (level > LIMIT) return false;
//            level++;
//        }
//        return true;
//    }
//
//
//    public int getLevel() {
//        return level;
//    }
//
//    @Override
//    public void setInput(String input) {
//        this.input = format(input).trim();
//    }
//
//    public String format(String input) {
//        return input.substring(level, input.length());
//    }
//
//    @Override
//    public String getInput() {
//        return input;
//    }
//
//    @Override
//    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
//       return conversionVisitor.translate(this, out);
//    }
//
//    @Override
//    public Separation getSeparation() {
//        return new SeparateNull();
//    }
//
//    @Override
//    public boolean convertible() {
//        return true;
//    }
//}
