
package library.loan;

import library.domain.User;
import library.domain.Library;
import library.domain.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {

    private Library library;
    private User student;
    private Book book;
    private LoanService loanService;

    @BeforeEach
    public void setup() {
        library = new Library();
        student = new User("Alice", "Student");
        book = new Book("Clean Code");
        loanService = new LoanService();
        library.addBook(book);
        library.registerUser(student);
    }

    @Test
    public void testBorrowBookSuccess() {
        boolean borrowed = loanService.borrowBook(student, book, library);
        assertTrue(borrowed);
        assertFalse(library.isBookAvailable(book));
        assertTrue(student.getBorrowedBooks().contains(book));
    }

    @Test
    public void testBorrowUnavailableBookFails() {
        library.removeBook(book); // make it unavailable
        boolean borrowed = loanService.borrowBook(student, book, library);
        assertFalse(borrowed);
    }

    @Test
    public void testReturnBookSuccess() {
        loanService.borrowBook(student, book, library);
        loanService.returnBook(student, book, library);

        assertTrue(library.isBookAvailable(book));
        assertFalse(student.getBorrowedBooks().contains(book));
    }
}