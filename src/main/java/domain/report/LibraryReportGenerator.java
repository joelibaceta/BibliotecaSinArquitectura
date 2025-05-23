package main.java.domain.report;

import main.java.domain.model.Library;
import main.java.domain.model.Book;
import main.java.domain.model.User;

public class LibraryReportGenerator {
    public String generateReport(Library library) {
        StringBuilder report = new StringBuilder("Library Report\n");
        report.append("Available Books:\n");
        for (Book book : library.getBooks()) {
            report.append(book.getTitle()).append("\n");
        }
        report.append("\nBorrowed Books:\n");
        for (User user : library.getUsers()) {
            for (Book borrowedBook : user.getBorrowedBooks()) {
                report.append(borrowedBook.getTitle()).append(" borrowed by ").append(user.getName()).append("\n");
            }
        }
        return report.toString();
    }
}
