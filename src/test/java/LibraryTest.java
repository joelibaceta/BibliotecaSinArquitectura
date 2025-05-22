package test.java;

import main.java.library.domain.model.Book;
import main.java.library.domain.model.User;
import main.java.library.infrastructure.repository.InMemoryBookRepository;
import main.java.library.infrastructure.repository.InMemoryUserRepository;
import main.java.library.usecase.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private InMemoryBookRepository bookRepository;
    private InMemoryUserRepository userRepository;

    private AddBookUseCase addBookUseCase;
    private RemoveBookUseCase removeBookUseCase;
    private RegisterUserUseCase registerUserUseCase;
    private BorrowBookUseCase borrowBookUseCase;
    private ReturnBookUseCase returnBookUseCase;
    private GenerateReportUseCase generateReportUseCase;

    private Book book1;
    private Book book2;
    private User student;
    private User teacher;

    @BeforeEach
    public void setUp() {
        bookRepository = new InMemoryBookRepository();
        userRepository = new InMemoryUserRepository();

        addBookUseCase = new AddBookUseCase(bookRepository);
        removeBookUseCase = new RemoveBookUseCase(bookRepository);
        registerUserUseCase = new RegisterUserUseCase(userRepository);
        borrowBookUseCase = new BorrowBookUseCase(bookRepository, userRepository);
        returnBookUseCase = new ReturnBookUseCase(bookRepository);
        generateReportUseCase = new GenerateReportUseCase(bookRepository, userRepository);

        book1 = new Book("The Catcher in the Rye");
        book2 = new Book("1984");

        student = new User("Alice", "Student");
        teacher = new User("Bob", "Teacher");

        addBookUseCase.execute(book1);
        addBookUseCase.execute(book2);
        registerUserUseCase.execute(student);
        registerUserUseCase.execute(teacher);
    }

    @Test
    public void testAddBook() {
        Book book3 = new Book("To Kill a Mockingbird");
        addBookUseCase.execute(book3);
        assertTrue(bookRepository.getAll().contains(book3));
    }

    @Test
    public void testRemoveBook() {
        removeBookUseCase.execute(book1);
        assertFalse(bookRepository.getAll().contains(book1));
    }

    @Test
    public void testRegisterUser() {
        User newUser = new User("Charlie", "Student");
        registerUserUseCase.execute(newUser);
        assertTrue(userRepository.findAll().contains(newUser));
    }

    @Test
    public void testBorrowBookForStudent() {
        boolean borrowed = borrowBookUseCase.execute(student, book1, bookRepository);
        assertTrue(borrowed);
        assertFalse(bookRepository.getAll()).contains(book1);
        assertTrue(student.getBorrowedBooks().contains(book1));
    }

    @Test
    public void testBorrowBookLimitForStudent() {
        borrowBookUseCase.execute(student, book1, bookRepository);
        borrowBookUseCase.execute(student, book1, bookRepository);

        Book book3 = new Book("Moby Dick");
        addBookUseCase.execute(book3);

        boolean borrowed = borrowBookUseCase.execute(student, book3, bookRepository);
        assertFalse(borrowed);
    }

    @Test
    public void testBorrowBookForTeacher() {
        boolean borrowed = borrowBookUseCase.execute(student, book1, bookRepository);
        assertTrue(borrowed);
        assertFalse(bookRepository.getAll().contains(book1));
        assertTrue(teacher.getBorrowedBooks().contains(book1));
    }

    @Test
    public void testReturnBook() {
        borrowBookUseCase.execute(student, book1, bookRepository);
        returnBookUseCase.execute(student, book1, bookRepository);
        assertTrue(bookRepository.getAll().contains(book1));
        assertFalse(student.getBorrowedBooks().contains(book1));
    }

    @Test
    public void testGenerateReport() {
        borrowBookUseCase.execute(student, book1, bookRepository);
        String report = generateReportUseCase.execute(bookRepository, userRepository);

        assertTrue(report.contains("Available Books:"));
        assertTrue(report.contains("Borrowed Books:"));
        assertTrue(report.contains("1984"));
        assertTrue(report.contains("The Catcher in the Rye borrowed by Alice"));
    }
}
