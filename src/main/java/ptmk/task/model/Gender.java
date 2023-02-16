package ptmk.task.model;

public enum Gender {
    MALE(1),
    FEMALE(2);

    private final int state;

    Gender(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public static Gender fromString(String gender) {
        gender = gender.trim().toLowerCase();
        return switch (gender) {
            case "male" -> MALE;
            case "female" -> FEMALE;
            default -> throw new IllegalArgumentException("Illegal gender: specify male of female");
        };
    }
}
