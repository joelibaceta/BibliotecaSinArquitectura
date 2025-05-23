package main.java.app;

import main.java.domain.model.Library;
import main.java.domain.model.Book;
import main.java.domain.model.User;
import main.java.domain.policy.StudentLoanPolicy;
import main.java.domain.policy.TeacherLoanPolicy;
import main.java.domain.report.LibraryReportGenerator;
import main.java.persistence.BookRepository;
import main.java.persistence.UserRepository;

public class Main {
    public static void main(String[] args) {

        // Crear instancia de la biblioteca
        Library library = new Library(
                new StudentLoanPolicy() , new TeacherLoanPolicy(), new BookRepository() , new UserRepository());

        // Crear algunos libros
        Book book1 = new Book("The Catcher in the Rye");
        Book book2 = new Book("To Kill a Mockingbird");
        Book book3 = new Book("1984");

        // Agregar libros a la biblioteca
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Crear algunos usuarios
        User student = new User("Alice", "Student");
        User teacher = new User("Bob", "Teacher");

        // Registrar usuarios en la biblioteca
        library.registerUser(student);
        library.registerUser(teacher);

        // Realizar algunos préstamos
        System.out.println("Préstamos de libros:");
        if (library.borrowBook(student, book1)) {
            System.out.println(student.getName() + " ha tomado prestado: " + book1.getTitle());
        } else {
            System.out.println(student.getName() + " no pudo tomar prestado: " + book1.getTitle());
        }

        if (library.borrowBook(teacher, book2)) {
            System.out.println(teacher.getName() + " ha tomado prestado: " + book2.getTitle());
        } else {
            System.out.println(teacher.getName() + " no pudo tomar prestado: " + book2.getTitle());
        }

        // Intentar tomar otro libro para el estudiante, hasta el límite
        if (library.borrowBook(student, book3)) {
            System.out.println(student.getName() + " ha tomado prestado: " + book3.getTitle());
        } else {
            System.out.println(student.getName() + " no pudo tomar prestado: " + book3.getTitle() + " (límite alcanzado)");
        }

        // Devolver un libro
        System.out.println("\nDevolución de libros:");
        library.returnBook(student, book1);
        System.out.println(student.getName() + " ha devuelto: " + book1.getTitle());

        // Generar y mostrar el reporte de la biblioteca
        System.out.println("\nReporte de la Biblioteca:");
        LibraryReportGenerator reportGenerator = new LibraryReportGenerator();
        System.out.println(reportGenerator.generateReport(library));
    }
}