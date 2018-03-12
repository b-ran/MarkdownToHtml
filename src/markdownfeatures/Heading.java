package markdownfeatures;

import conversion.ConversionVisitor;

public class Heading implements Feature {

    private static final int LIMIT = 6;
    private int level = 0;

    @Override
    public boolean detect(String input) {
        String in = input.trim();
        for (int i = 0; i < in.length(); i++) {
            if (level > LIMIT) return false;
            switch(in.charAt(i)) {
                case '#' :
                    level++;
                    continue;
                case ' ' :
                    return true;
                default :
                    return false;
            }
        }
        return false;
    }


    @Override
    public void set(String input) {

    }

    @Override
    public void convert(ConversionVisitor conversionVisitor) {
        conversionVisitor.translate(this);
    }
}
