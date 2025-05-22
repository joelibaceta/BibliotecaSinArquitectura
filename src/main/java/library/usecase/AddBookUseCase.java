package main.java.library.usecase;

import main.java.library.domain.model.Book;
import main.java.library.domain.repository.BookRepository;

public class AddBookUseCase {

    private final BookRepository bookRepository;

    public AddBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void execute(Book book) {
        if (!bookRepository.exists(book)) {
            bookRepository.save(book);
        }
    }
}
