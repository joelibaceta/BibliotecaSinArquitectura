package main.java.service;

import main.java.model.Book;
import main.java.model.User;

public interface LibraryService {
    public boolean borrowBook(User user, Book book);
    public void returnBook(User user, Book book);
    public String generateReport();

	
}
