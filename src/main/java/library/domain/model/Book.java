package main.java.library.domain.model;
import java.util.Objects;

public class Book {
    private final String title;

    public Book(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public void save(Book book) {

          book.save(book);
    }

    public boolean exists(Book book) {

        return book.exists(book);
    }

    
    
}
