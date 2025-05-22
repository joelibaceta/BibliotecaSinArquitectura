package main.java.library.usecase;

import main.java.library.domain.model.Book;
import main.java.library.domain.model.User;
import main.java.library.domain.repository.BookRepository;
import main.java.library.domain.repository.UserRepository;

public class GenerateReportUseCase {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    
    
    public GenerateReportUseCase(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public String execute(BookRepository bookRepository, UserRepository userRepository) {
        StringBuilder sb = new StringBuilder("Library Report\n");
        sb.append("Available Books:\n");
        for (Book book : bookRepository.getAll()) {
            sb.append(" - ").append(book.getTitle()).append("\n");
        }
        sb.append("\nBorrowed Books:\n");
        for (User user : userRepository.getAll()) {
            for (Book book : user.getBorrowedBooks()) {
                sb.append(" - ").append(book.getTitle()).append(" borrowed by ").append(user.getName()).append("\n");
            }
        }
        return sb.toString();
    }
}
