package main.java.library.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {
    private final String name;
    private final UserType type;
    private final List<Book> borrowedBooks;

    public User(String name, String type) {
        this.name = name;
        this.type = UserType.from(type);
        this.borrowedBooks = new ArrayList<>();
    }

    public boolean canBorrow() {
        return borrowedBooks.size() < type.getBorrowLimit();
    }

    public void borrowBook(Book book) {
        if (!canBorrow()) {
            throw new IllegalStateException("Límite de préstamos alcanzado.");
        }
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return name.equals(user.name) && type == user.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}

