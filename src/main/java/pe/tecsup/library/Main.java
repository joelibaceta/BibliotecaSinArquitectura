package pe.tecsup.library;

import pe.tecsup.library.model.Book;
import pe.tecsup.library.model.User;
import pe.tecsup.library.model.UserType;
import pe.tecsup.library.service.LibraryService;

public class Main {
    public static void main(String[] args) {
        // Crear instancia del servicio de biblioteca
        LibraryService library = new LibraryService();

        // Crear algunos libros
        Book book1 = new Book("The Catcher in the Rye");
        Book book2 = new Book("To Kill a Mockingbird");
        Book book3 = new Book("1984");

        // Agregar libros a la biblioteca
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Crear algunos usuarios
        User student = new User("Alice", UserType.STUDENT);
        User teacher = new User("Bob", UserType.TEACHER);

        // Registrar usuarios en la biblioteca
        library.registerUser(student);
        library.registerUser(teacher);

        // Realizar algunos préstamos
        System.out.println("Préstamos de libros:");
        if (library.borrowBook(student, book1)) {
            System.out.println(student.getName() + " ha tomado prestado: " + book1.title());
        } else {
            System.out.println(student.getName() + " no pudo tomar prestado: " + book1.title());
        }

        if (library.borrowBook(teacher, book2)) {
            System.out.println(teacher.getName() + " ha tomado prestado: " + book2.title());
        } else {
            System.out.println(teacher.getName() + " no pudo tomar prestado: " + book2.title());
        }

        // Intentar tomar otro libro para el estudiante, hasta el límite
        if (library.borrowBook(student, book3)) {
            System.out.println(student.getName() + " ha tomado prestado: " + book3.title());
        } else {
            System.out.println(student.getName() + " no pudo tomar prestado: " + book3.title() + " (límite alcanzado)");
        }

        // Devolver un libro
        System.out.println("\nDevolución de libros:");
        library.returnBook(student, book1);
        System.out.println(student.getName() + " ha devuelto: " + book1.title());

        // Generar y mostrar el reporte de la biblioteca
        System.out.println("\nReporte de la Biblioteca:");
        System.out.println(library.generateReport());
    }
}

