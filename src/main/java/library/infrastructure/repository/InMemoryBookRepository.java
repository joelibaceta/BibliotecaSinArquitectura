package main.java.library.infrastructure.repository;

import main.java.library.domain.model.Book;
import main.java.library.domain.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryBookRepository implements BookRepository {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void add(Book book) {
        if (!books.contains(book)) books.add(book);
    }

    @Override
    public void remove(Book book) {
        books.remove(book);
    }

    @Override
    public boolean contains(Book book) {
        return books.contains(book);
    }

    @Override
    public List<Book> getAll() {
        return Collections.unmodifiableList(books);
    }

    @Override
    public void save(Book book) {
        
          book.save(book);
    }

    @Override
    public boolean exists(Book book) {
        
        return books.contains(book);
    }
}
