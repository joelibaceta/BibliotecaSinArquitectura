package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final UserType type;
    private final List<Book> borrowedBooks;

    public enum UserType { STUDENT, TEACHER }

    public User(String name, UserType type) {
        this.name = name;
        this.type = type;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public void borrow(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}