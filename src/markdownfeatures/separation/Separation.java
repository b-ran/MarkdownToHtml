package markdownfeatures.separation;

public interface Separation {
    boolean detect(String next);

    boolean isSeparation();

    String format(String input);

    boolean left();

    boolean right();
}
