package pe.tecsup.library.service;

import pe.tecsup.library.model.Book;
import pe.tecsup.library.model.User;
import pe.tecsup.library.model.UserType;
import pe.tecsup.library.rules.LoanRule;
import pe.tecsup.library.rules.StudentLoanRule;
import pe.tecsup.library.rules.TeacherLoanRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class LibraryService {
    private final List<Book> books;
    private final List<User> users;
    private final Map<UserType, LoanRule> loanRules;

    public LibraryService() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loanRules = new HashMap<>();
        // Registrar reglas de préstamo
        loanRules.put(UserType.STUDENT, new StudentLoanRule());
        loanRules.put(UserType.TEACHER, new TeacherLoanRule());
    }

    public void addBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Libro no puede ser nulo");

        if (!books.contains(book)) {
            books.add(book);
        }
    }

    public void removeBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Libro no puede ser nulo");

        books.remove(book);
    }

    public void registerUser(User user) {
        if (user == null) throw new IllegalArgumentException("Usuario no puede ser nulo");

        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public boolean borrowBook(User user, Book book) {
        if (user == null || book == null) return false;

        if (books.contains(book)) {
            LoanRule rule = loanRules.get(user.getType());
            if (rule != null && rule.canBorrow(user)) {
                user.borrowBook(book);
                books.remove(book);
                return true;
            }
        }

        return false;
    }

    public void returnBook(User user, Book book) {
        if (user == null || book == null) return;

        if (!books.contains(book)) {
            user.returnBook(book);
            books.add(book);
        }
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder("Library Report\n");
        report.append("Available Books:\n");

        for (Book book : books) {
            report.append(book.title()).append("\n");
        }
        report.append("\nBorrowed Books:\n");

        for (User user : users) {
            for (Book borrowedBook : user.getBorrowedBooks()) {
                report.append(borrowedBook.title()).append(" borrowed by ").append(user.getName()).append("\n");
            }
        }

        return report.toString();
    }

    public boolean isBookAvailable(Book book) {
        if (book == null) throw new IllegalArgumentException("Libro no puede ser nulo");
        return books.contains(book);
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}

