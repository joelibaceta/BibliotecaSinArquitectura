package library.report;

import library.domain.User;
import library.domain.Book;
import library.domain.Library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryReportGeneratorTest {

    private Library library;
    private LibraryReportGenerator reportGenerator;
    private User student;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setup() {
        library = new Library();
        reportGenerator = new LibraryReportGenerator();

        student = new User("Alice", "Student");
        book1 = new Book("The Pragmatic Programmer");
        book2 = new Book("Clean Architecture");

        library.addBook(book1);
        library.registerUser(student);
        student.borrowBook(book2);  // book2 está prestado
    }

    @Test
    public void testReportIncludesAvailableBooks() {
        String report = reportGenerator.generate(library);
        assertTrue(report.contains("Available Books:"));
        assertTrue(report.contains(book1.getTitle()));
    }

    @Test
    public void testReportIncludesBorrowedBooks() {
        String report = reportGenerator.generate(library);
        assertTrue(report.contains("Borrowed Books:"));
        assertTrue(report.contains(book2.getTitle()));
        assertTrue(report.contains(student.getName()));
    }
}
