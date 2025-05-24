
package main.java.service.impl;

import main.java.domain.Book;
import main.java.domain.User;
import main.java.repository.BookRepository;
import main.java.repository.UserRepository;
import main.java.service.LibraryService;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public LibraryServiceImpl(BookRepository bookRepo, UserRepository userRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public void addBook(Book book) {
        bookRepo.save(book);
    }

    public void removeBook(Book book) {
        bookRepo.delete(book);
    }

    public void registerUser(User user) {
        userRepo.save(user);
    }

    public boolean borrowBook(User user, Book book) {
        if (bookRepo.exists(book)) {
            if (user.getType().equals("Student") && user.getBorrowedBooks().size() < 2) {
                bookRepo.delete(book);

                return true;
            } else if (user.getType().equals("Teacher") && user.getBorrowedBooks().size() < 5) {
                bookRepo.delete(book);
                return true;
            }
        }
        return false;
    }

    public void returnBook(User user, Book book) {
        user.returnBook(book);
        bookRepo.save(book);
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder("Library Report\n");

        report.append("Available Books:\n");
        for (Book book : bookRepo.findAll()) {
            report.append(book.getTitle()).append("\n");
        }

        report.append("\nBorrowed Books:\n");
        for (User user : userRepo.findAll()) {
            for (Book borrowedBook : user.getBorrowedBooks()) {
                report.append(borrowedBook.getTitle())
                        .append(" borrowed by ")
                        .append(user.getName())
                        .append("\n");
            }
        }

        return report.toString();
    }

    public boolean isBookAvailable(Book book) {
        return bookRepo.exists(book);
    }

    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
