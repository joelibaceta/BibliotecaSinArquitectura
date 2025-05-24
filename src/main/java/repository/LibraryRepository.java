package main.java.repository;

import java.util.List;

import main.java.model.Book;
import main.java.model.User;

public interface LibraryRepository  {
    public void addBook(Book book);
    public void removeBook(Book book);
    public boolean containsBook(Book book);
    public List<Book> getAvailableBooks();
    public void registerUser(User user);
    public List<User> getUsers();
    public void returnBook(Book book);
    public void borrowBook(Book book);


}
