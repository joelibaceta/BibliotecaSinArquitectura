package main.java.library.domain.model;

public enum UserType {
    STUDENT(2),
    TEACHER(5);

    private final int borrowLimit;

    UserType(int borrowLimit) {
        this.borrowLimit = borrowLimit;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public static UserType from(String type) {
        switch (type.toUpperCase()) {
            case "STUDENT":
                return STUDENT;
            case "TEACHER":
                return TEACHER;
            default:
                throw new IllegalArgumentException("Tipo inválido: " + type);
        }
    }
}
