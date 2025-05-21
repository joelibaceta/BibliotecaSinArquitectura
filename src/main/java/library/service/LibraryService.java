package main.java.library.service;

import main.java.library.model.Book;
import main.java.library.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibraryService {
    private List<Book> books;
    private List<User> users;

    public LibraryService() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
        }
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void registerUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public boolean borrowBook(User user, Book book) {
        if (books.contains(book)) {
            if (user.getType().equals("Student") && user.getBorrowedBooks().size() < 2) {
                user.borrowBook(book);
                books.remove(book);
                return true;
            } else if (user.getType().equals("Teacher") && user.getBorrowedBooks().size() < 5) {
                user.borrowBook(book);
                books.remove(book);
                return true;
            }
        }
        return false;
    }

    public void returnBook(User user, Book book) {
        if (!books.contains(book)) {
            user.returnBook(book);
            books.add(book);
        }
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder("Library Report\n");
        report.append("Available Books:\n");
        for (Book book : books) {
            report.append(book.getTitle()).append("\n");
        }
        report.append("\nBorrowed Books:\n");
        for (User user : users) {
            for (Book borrowedBook : user.getBorrowedBooks()) {
                report.append(borrowedBook.getTitle()).append(" borrowed by ").append(user.getName()).append("\n");
            }
        }
        return report.toString();
    }

    public boolean isBookAvailable(Book book) {
        return books.contains(book);
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
