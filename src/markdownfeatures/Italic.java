package markdownfeatures;

import conversion.ConversionVisitor;

public class Italic implements Feature {

    private static final int limit = 2;

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
    public void set(String input) {

    }

    @Override
    public void convert(ConversionVisitor conversionVisitor) {

    }
}
