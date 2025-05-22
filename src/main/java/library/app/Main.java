package main.java.library.app;

import main.java.library.domain.model.Book;
import main.java.library.domain.model.User;
import main.java.library.infrastructure.repository.InMemoryBookRepository;
import main.java.library.infrastructure.repository.InMemoryUserRepository;
import main.java.library.usecase.BorrowBookUseCase;
import main.java.library.usecase.ReturnBookUseCase;
import main.java.library.usecase.GenerateReportUseCase;

public class Main {
    public static void main(String[] args) {
        var bookRepo = new InMemoryBookRepository();
        var userRepo = new InMemoryUserRepository();

        var book1 = new Book("1984");
        var book2 = new Book("To Kill a Mockingbird");

        bookRepo.add(book1);
        bookRepo.add(book2);

        var user = new User("Alice", "Student");
        userRepo.add(user);

        var borrow = new BorrowBookUseCase(bookRepo, userRepo);
        var returned = new ReturnBookUseCase(bookRepo);
        var report = new GenerateReportUseCase(bookRepo, userRepo);

        if (borrow.execute(user, book1, bookRepo)) {
            System.out.println(user.getName() + " ha tomado prestado: " + book1.getTitle());
        }

        returned.execute(user, book1, bookRepo);
        System.out.println(user.getName() + " ha devuelto: " + book1.getTitle());

        System.out.println(report.execute(bookRepo, userRepo));
    }
}

