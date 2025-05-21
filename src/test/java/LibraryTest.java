package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.library.service.LibraryService;
import main.java.library.model.Book;
import main.java.library.model.User;

public class LibraryTest {

    private LibraryService library;
    private Book book1;
    private Book book2;
    private User student;
    private User teacher;

    @BeforeEach
    public void setUp() {
        library = new LibraryService();
        book1 = new Book("The Catcher in the Rye");
        book2 = new Book("1984");
        student = new User("Alice", "Student");
        teacher = new User("Bob", "Teacher");

        library.addBook(book1);
        library.addBook(book2);
        library.registerUser(student);
        library.registerUser(teacher);
    }

    @Test
    public void testAddBook() {
        Book book3 = new Book("To Kill a Mockingbird");
        library.addBook(book3);
        assertTrue(library.getBooks().contains(book3));
    }

    @Test
    public void testRemoveBook() {
        library.removeBook(book1);
        assertFalse(library.getBooks().contains(book1));
    }

    @Test
    public void testRegisterUser() {
        User newUser = new User("Charlie", "Student");
        library.registerUser(newUser);
        assertTrue(library.getUsers().contains(newUser));
    }

    @Test
    public void testBorrowBookForStudent() {
        boolean borrowed = library.borrowBook(student, book1);
        assertTrue(borrowed);
        assertFalse(library.getBooks().contains(book1));
        assertTrue(student.getBorrowedBooks().contains(book1));
    }

    @Test
    public void testBorrowBookLimitForStudent() {
        library.borrowBook(student, book1);
        library.borrowBook(student, book2);
        Book book3 = new Book("Moby Dick");
        library.addBook(book3);

        boolean borrowed = library.borrowBook(student, book3);
        assertFalse(borrowed);
    }

    @Test
    public void testBorrowBookForTeacher() {
        boolean borrowed = library.borrowBook(teacher, book1);
        assertTrue(borrowed);
        assertFalse(library.getBooks().contains(book1));
        assertTrue(teacher.getBorrowedBooks().contains(book1));
    }

    @Test
    public void testReturnBook() {
        library.borrowBook(student, book1);
        library.returnBook(student, book1);
        assertTrue(library.getBooks().contains(book1));
        assertFalse(student.getBorrowedBooks().contains(book1));
    }

    @Test
    public void testGenerateReport() {
        library.borrowBook(student, book1);
        String report = library.generateReport();
        assertTrue(report.contains("Available Books:"));
        assertTrue(report.contains("Borrowed Books:"));
        assertTrue(report.contains("1984"));
        assertTrue(report.contains("The Catcher in the Rye borrowed by Alice"));
    }
}
