package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.Separation;

import java.util.Scanner;

public class Heading implements Feature {

    private static final int LIMIT = 6;
    private int level = 0;
    private String input = "";

    private final static String format = "#";

    @Override
    public boolean detect(String next, String line) {
        String in = next.trim();

        if (in.length() == 0 || line.length() == 0) return false;
        if (!new Scanner(line).next().equals(in)) return false;

        if (line.length() > 3) {
            if (line.charAt(0) == ' ' && line.charAt(3) == ' ') return false;
        }

        for (char letter : in.toCharArray()) {
            if (letter != format.charAt(0)) return false;
            level++;
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
        this.input = this.input.replace(format.charAt(0) , ' ');
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

