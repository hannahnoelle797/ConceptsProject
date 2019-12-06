public class variable {
    String id;
    int value;

    public variable(String i, int v) {
        id = i.trim();
        value = v;
    }

    public String getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        value = v;
    }
}