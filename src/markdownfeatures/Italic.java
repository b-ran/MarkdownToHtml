package markdownfeatures;

import conversion.ConversionVisitor;

public class Italic implements Feature {

    private static final int limit = 2;
    private String input = "";

    @Override
    public boolean detect(String input) {
        String in = input.trim();
        return in.startsWith("*") && in.endsWith("*") && input.length() > 2 && limit(in);
    }

    private boolean limit(String input) {
        int count = 0;
        for (char c : input.toCharArray()) {
            if (c == '*') {
                count++;
            }
        }
        return count <= limit;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
        format();
    }

    private void format() {
        input = input.substring(1);
        input = input.substring(0, input.length()-1);
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public StringBuilder convert(ConversionVisitor conversionVisitor, StringBuilder out) {
        return conversionVisitor.translate(this, out);
    }
}
