package main.java.library.app;

import main.java.library.service.LibraryService;
import main.java.library.model.Book;
import main.java.library.model.User;

public class Main {
    public static void main(String[] args) {
        LibraryService library = new LibraryService();

        Book book1 = new Book("The Catcher in the Rye");
        Book book2 = new Book("To Kill a Mockingbird");
        Book book3 = new Book("1984");

        
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        User student = new User("Alice", "Student");
        User teacher = new User("Bob", "Teacher");

        library.registerUser(student);
        library.registerUser(teacher);

        System.out.println("Préstamos de libros:");
        if (library.borrowBook(student, book1)) {
            System.out.println(student.getName() + " ha tomado prestado: " + book1.getTitle());
        }

        if (library.borrowBook(teacher, book2)) {
            System.out.println(teacher.getName() + " ha tomado prestado: " + book2.getTitle());
        }

        if (library.borrowBook(student, book3)) {
            System.out.println(student.getName() + " ha tomado prestado: " + book3.getTitle());
        } else {
            System.out.println(student.getName() + " no pudo tomar prestado: " + book3.getTitle() + " (límite alcanzado)");
        }

        System.out.println("\nDevolución de libros:");
        library.returnBook(student, book1);
        System.out.println(student.getName() + " ha devuelto: " + book1.getTitle());

        System.out.println("\nReporte de la Biblioteca:");
        System.out.println(library.generateReport());
    }
}

