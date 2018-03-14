package markdownfeatures;

import conversion.ConversionVisitor;

public class Heading implements Feature {

    private static final int LIMIT = 6;
    private int level = 0;
    private String input = "";

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
}
