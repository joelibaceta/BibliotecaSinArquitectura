package pe.tecsup.library.rules;

import pe.tecsup.library.model.User;
import pe.tecsup.library.model.UserType;

public class StudentLoanRule implements LoanRule {
    private static final int MAX_BOOKS = 3;

    @Override
    public boolean canBorrow(User user) {
        return user.getType() == UserType.STUDENT && user.getBorrowedBooks().size() < MAX_BOOKS;
    }

    @Override
    public int getMaxBooks() {
        return MAX_BOOKS;
    }
}

