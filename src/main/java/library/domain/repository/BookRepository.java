package main.java.library.domain.repository;
import main.java.library.domain.model.Book;

import java.util.List;

public interface BookRepository {
    void add(Book book);
    void remove(Book book);
    boolean contains(Book book);
    List<Book> getAll();
    void save(Book book);
    boolean exists(Book book);
}
