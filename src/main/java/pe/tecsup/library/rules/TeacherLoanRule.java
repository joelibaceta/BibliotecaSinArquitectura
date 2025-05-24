package pe.tecsup.library.rules;

import pe.tecsup.library.model.User;
import pe.tecsup.library.model.UserType;

public class TeacherLoanRule implements LoanRule {
    private static final int MAX_BOOKS = 5;

    @Override
    public boolean canBorrow(User user) {
        return user.getType() == UserType.TEACHER && user.getBorrowedBooks().size() < MAX_BOOKS;
    }

    @Override
    public int getMaxBooks() {
        return MAX_BOOKS;
    }
}

