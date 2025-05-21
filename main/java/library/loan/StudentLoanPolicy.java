package library.loan;

import library.domain.User;

public class StudentLoanPolicy implements LoanPolicy {

    @Override
    public boolean canBorrow(User user) {
        return user.getBorrowedBooks().size() < 3;
    }
}