package library.loan;

import library.domain.User;

public class LoanPolicyResolver {

    public LoanPolicy resolve(User user) {
        return switch (user.getType()) {
            case "Student" -> new StudentLoanPolicy();
            case "Teacher" -> new TeacherLoanPolicy();
            default -> throw new IllegalArgumentException("Tipo de usuario desconocido: " + user.getType());
        };
    }
}
