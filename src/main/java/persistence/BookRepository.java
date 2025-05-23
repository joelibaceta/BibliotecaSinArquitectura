package main.java.persistence;

import main.java.domain.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private final List<Book> books = new ArrayList<>();

    public void save(Book book) {
        if (!books.contains(book)) {
            books.add(book);
        }
    }

    public void delete(Book book) {
        books.remove(book);
    }

    public boolean exists(Book book) {
        return books.contains(book);
    }
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }
}
