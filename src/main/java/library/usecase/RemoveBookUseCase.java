package main.java.library.usecase;

import main.java.library.domain.model.Book;
import main.java.library.domain.repository.BookRepository;

public class RemoveBookUseCase {

    private final BookRepository bookRepository;

    public RemoveBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void execute(Book book) {
        bookRepository.remove(book);
    }
}
