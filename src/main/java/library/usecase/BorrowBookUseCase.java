package main.java.library.usecase;

import main.java.library.domain.model.Book;
import main.java.library.domain.model.User;
import main.java.library.domain.repository.BookRepository;
import main.java.library.domain.repository.UserRepository;

public class BorrowBookUseCase {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowBookUseCase(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    
    public boolean execute(User user, Book book, BookRepository bookRepository) {
        if (!bookRepository.contains(book)) return false;
        if (!user.canBorrow()) return false;

        user.borrowBook(book);
        bookRepository.remove(book);
        return true;
    }
}
