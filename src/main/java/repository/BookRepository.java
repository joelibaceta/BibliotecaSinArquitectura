
package main.java.repository;

import main.java.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    private final List<Book> books = new ArrayList<>();

    public void save(Book book) {
        if (!books.contains(book)) books.add(book);
    }

    public void delete(Book book) {
        books.remove(book);
    }

    public Optional<Book> findByTitle(String title) {
        return books.stream()
                    .filter(b -> b.getTitle().equalsIgnoreCase(title))
                    .findFirst();
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    public boolean exists(Book book) {
        return books.contains(book);
    }
}
