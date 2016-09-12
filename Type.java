
public enum Type {
    KINDER("Kinder"),
    ELEMENTARY("Elementary"),
    HIGH("High"),
    UNIVERSITY("University");

    private final String type;

    private Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
