package markdownfeatures.separation;

public class SeparateNull implements Separation {

    @Override
    public boolean detect(String next) {
        return false;
    }

    @Override
    public boolean isSeparation() {
        return false;
    }

    @Override
    public String format(String input) {
        return "";
    }

    @Override
    public boolean left() {
        return false;
    }

    @Override
    public boolean right() {
        return false;
    }
}
