package library.report;

import library.domain.User;
import library.domain.Book;
import library.domain.Library;

public class LibraryReportGenerator {

    public String generate(Library library) {
        StringBuilder sb = new StringBuilder("=== Library Report ===\n");

        sb.append("\nAvailable Books:\n");
        for (Book book : library.getBooks()) {
            sb.append("- ").append(book.getTitle()).append("\n");
        }

        sb.append("\nBorrowed Books:\n");
        for (User user : library.getUsers()) {
            for (Book book : user.getBorrowedBooks()) {
                sb.append("- ").append(book.getTitle())
                  .append(" (borrowed by ").append(user.getName()).append(")\n");
            }
        }

        return sb.toString();
    }
}
