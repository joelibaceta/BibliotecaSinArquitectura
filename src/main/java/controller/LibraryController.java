package main.java.controller;

import main.java.model.Book;
import main.java.model.User;
import main.java.repository.LibraryRepository;
import main.java.service.LibraryService;

public class LibraryController {
    private final LibraryService service;
    private final LibraryRepository repository;

    
    public LibraryController(LibraryService service, LibraryRepository repository) {
		this.service = service;
		this.repository = repository;
	}



	public void addBook(Book book) {
        repository.addBook(book);
    }

    public void registerUser(User user) {
        repository.registerUser(user);
    }

    public String borrowBook(User user, Book book) {
        return service.borrowBook(user, book)?
        		user.getName() + " ha tomado prestado: " +  book.getTitle() :
        		user.getName() + " no pudo tomar prestado: " +  book.getTitle();
    }

    public void returnBook(User user, Book book) {
        service.returnBook(user, book);
    }

    public String generateReport() {
        return service.generateReport();
    }
}