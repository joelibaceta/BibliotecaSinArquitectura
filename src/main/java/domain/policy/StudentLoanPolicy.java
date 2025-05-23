package main.java.domain.policy;

import main.java.domain.model.User;

public class StudentLoanPolicy implements LoanPolicy {
    @Override
    public boolean canBorrow(User user) {
        return user.getType().equals("Student") && user.getBorrowedBooks().size() < 2;
    }
}
