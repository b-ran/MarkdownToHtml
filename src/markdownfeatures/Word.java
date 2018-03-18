package markdownfeatures;

import conversion.ConversionVisitor;
import markdownfeatures.separation.SeparateNull;
import markdownfeatures.separation.Separation;

public class Word implements Feature{

    private String input = "";

    public Word(String input) {
        setInput(input);
    }

    public Word() {
    }

    @Override
    public boolean detect(String next, String line) {
        return !next.isEmpty() && !next.chars().allMatch(Character::isSpaceChar);
    }

    @Override
    public void setInput(String input) {this.input = input;}


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
