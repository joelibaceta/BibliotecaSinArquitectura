package pe.tecsup.library.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {
    private final String name;
    private final UserType type;
    private final List<Book> borrowedBooks;

    public User(String name, UserType type) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        if (type == null) throw new IllegalArgumentException("UserType cannot be null");

        this.name = name;
        this.type = type;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book cannot be null");

        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book cannot be null");

        borrowedBooks.remove(book);
    }

    public UserType getType() {
        return type;
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && type == user.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
