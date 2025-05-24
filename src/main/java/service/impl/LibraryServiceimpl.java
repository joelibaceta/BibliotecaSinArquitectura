package main.java.service.impl;

import main.java.model.Book;
import main.java.model.User;
import main.java.repository.LibraryRepository;
import main.java.service.LibraryService;

public class LibraryServiceimpl implements LibraryService {

	private final LibraryRepository repository;

	public LibraryServiceimpl(LibraryRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean borrowBook(User user, Book book) {
		if (!repository.containsBook(book))
			return false;

		int maxBooks = user.getType() == User.UserType.STUDENT ? 2 : 5;
		if (user.getBorrowedBooks().size() >= maxBooks)
			return false;

		user.borrow(book);
		repository.borrowBook(book);
		return true;
	}

	@Override
	public void returnBook(User user, Book book) {
		user.returnBook(book);
		repository.returnBook(book);
	}

	@Override
	public String generateReport() {
		StringBuilder sb = new StringBuilder("Library Report\nAvailable Books:\n");
		for (Book book : repository.getAvailableBooks()) {
			sb.append(book.getTitle()).append("\n");
		}

		sb.append("\nBorrowed Books:\n");
		for (User user : repository.getUsers()) {
			for (Book b : user.getBorrowedBooks()) {
				sb.append(b.getTitle()).append(" borrowed by ").append(user.getName()).append("\n");
			}
		}

		return sb.toString();
	}
}