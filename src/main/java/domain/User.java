package main.java.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String type; // "Student" or "Teacher"
    private List<Book> borrowedBooks;

    public User(String name, String type) {
        this.name = name;
        this.type = type;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public String getType() {
        return type;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getName() {
        return name;
    }
}