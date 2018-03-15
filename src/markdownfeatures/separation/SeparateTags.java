package markdownfeatures.separation;

public class SeparateTags implements Separation{

    private final String format;

    private boolean left = true;
    private boolean right = true;

    public SeparateTags(String format) {
        this.format = format;
    }

    public static boolean detectSeparation(String next, String line, String format) {
        int start = line.indexOf(format)+next.length();
        return line.indexOf(format, start) > start;
    }

    @Override
    public boolean detect(String next) {
        if (left && next.startsWith(format)) {
            return true;
        } else if (right && next.endsWith(format)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSeparation() {
        return left || right;
    }

    @Override
    public String format(String input) {
        if (left && right) return input.substring(format.length());
        else {
            return input.substring(0, input.length()-format.length());
        }
    }

    @Override
    public boolean left() {
        if (left && right) {
            left = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean right() {
        if (right) {
            right = false;
            return true;
        }
        return false;
    }
}
