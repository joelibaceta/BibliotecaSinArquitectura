package main.java.domain.policy;

import main.java.domain.model.User;

public class TeacherLoanPolicy implements LoanPolicy{
    @Override
    public boolean canBorrow(User user) {
        return user.getType().equals("Teacher") && user.getBorrowedBooks().size() < 5;
    }
}
