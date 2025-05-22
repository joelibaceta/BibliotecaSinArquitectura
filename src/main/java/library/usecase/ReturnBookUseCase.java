package main.java.library.usecase;

import main.java.library.domain.model.Book;
import main.java.library.domain.model.User;
import main.java.library.domain.repository.BookRepository;


public class ReturnBookUseCase {
    private final BookRepository bookRepository;
    
    public ReturnBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        
    }

    public void execute(User user, Book book, BookRepository bookRepository) {
        user.returnBook(book);
        bookRepository.add(book);
    }
}
