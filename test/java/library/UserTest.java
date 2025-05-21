
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import library.domain.User;
import library.domain.Book;

public class UserTest {

    @Test
    public void testBorrowAndReturnBook() {
        User user = new User("Alice", "Student");
        Book book = new Book("Design Patterns");

        user.borrowBook(book);
        assertTrue(user.getBorrowedBooks().contains(book));

        user.returnBook(book);
        assertFalse(user.getBorrowedBooks().contains(book));
    }
}
