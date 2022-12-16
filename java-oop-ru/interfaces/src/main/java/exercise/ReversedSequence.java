package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String value;

    public ReversedSequence(String value) {
        this.value = new StringBuilder(value).reverse().toString();
    }

    @Override
    public int length() {
        return value.length();
    }

    @Override
    public char charAt(int i) {
        return value.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return value.subSequence(i, i1);
    }

    @Override
    public String toString() {
        return value;
    }
}
// END
