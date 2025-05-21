package library.loan;

import library.domain.User;
import library.domain.Book;
import library.domain.Library;

public class LoanService {

    private final LoanPolicyResolver resolver = new LoanPolicyResolver();

    public boolean borrowBook(User user, Book book, Library library) {
        if (!library.isBookAvailable(book)) return false;

        LoanPolicy policy = resolver.resolve(user);
        if (policy.canBorrow(user)) {
            user.borrowBook(book);
            library.removeBook(book);
            return true;
        }

        return false;
    }

    public void returnBook(User user, Book book, Library library) {
        user.returnBook(book);
        library.addBook(book);
    }
}