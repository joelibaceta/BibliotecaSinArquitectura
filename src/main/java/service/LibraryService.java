package main.java.service;

import main.java.domain.Book;
import main.java.domain.User;

import java.util.List;

public interface LibraryService {
    void addBook(Book book);
    void removeBook(Book book);
    void registerUser(User user);
    boolean borrowBook(User user, Book book);
    void returnBook(User user, Book book);
    String generateReport();
    boolean isBookAvailable(Book book);
    List<Book> getBooks();
    List<User> getUsers();
}
