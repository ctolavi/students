
public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender;
    }

}
