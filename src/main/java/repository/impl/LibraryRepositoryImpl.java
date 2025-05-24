package main.java.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.model.Book;
import main.java.model.User;
import main.java.repository.LibraryRepository;

public class LibraryRepositoryImpl implements LibraryRepository {

	private final List<Book> books = new ArrayList<>();
	private final List<User> users = new ArrayList<>();

	@Override
	public void addBook(Book book) {
		if (!books.contains(book))
			books.add(book);
	}

	public void removeBook(Book book) {
		books.remove(book);
	}

	@Override
	public boolean containsBook(Book book) {
		return books.contains(book);
	}

	@Override
	public List<Book> getAvailableBooks() {
		return Collections.unmodifiableList(books);
	}

	@Override
	public void registerUser(User user) {
		if (!users.contains(user))
			users.add(user);
	}

	@Override
	public List<User> getUsers() {
		return Collections.unmodifiableList(users);
	}

	@Override
	public void returnBook(Book book) {
		if (!books.contains(book)) {
			books.add(book);
		}
	}

	@Override
	public void borrowBook(Book book) {
		books.remove(book);
	}
}