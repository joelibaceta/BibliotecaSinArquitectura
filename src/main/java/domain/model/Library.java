package main.java.domain.model;

import main.java.domain.policy.StudentLoanPolicy;
import main.java.domain.policy.TeacherLoanPolicy;
import main.java.persistence.BookRepository;
import main.java.persistence.UserRepository;

import java.util.List;

public class Library {

//    private List<Book> books;
//    private List<User> users;

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private final StudentLoanPolicy studentLoanPolicy; ///
    private final TeacherLoanPolicy teacherLoanPolicy;///


    public Library(StudentLoanPolicy studentLoanPolicy , TeacherLoanPolicy teacherLoanPolicy
    , BookRepository bookRepository , UserRepository userRepository
    ) {
//        this.books = new ArrayList<>();
//        this.users = new ArrayList<>();
        this.studentLoanPolicy = studentLoanPolicy;
        this.teacherLoanPolicy = teacherLoanPolicy;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    // Agregar un libro a la biblioteca
    public void addBook(Book book) {
//        if (!books.contains(book)) {
//            books.add(book);
//        }
        bookRepository.save(book);
    }

    // Remover un libro de la biblioteca
    public void removeBook(Book book) {

//        books.remove(book);
        bookRepository.delete(book);
    }

    // Registrar un usuario en la biblioteca
    public void registerUser(User user) {
//        if (!users.contains(user)) {
//            users.add(user);
//        }

        userRepository.save(user);
    }

    // Permitir que un usuario tome un libro prestado
    public boolean borrowBook(User user, Book book) {
//        if (books.contains(book)) {

        if(bookRepository.exists(book)){

            boolean canBorrow = false;


            if(user.getType().equals("Student")){
                canBorrow = studentLoanPolicy.canBorrow(user);
            }else if (user.getType().equals("Teacher")){
                canBorrow = teacherLoanPolicy.canBorrow(user);
            }

            if (canBorrow){
                user.borrowBook(book);
//                books.remove(book);
                bookRepository.delete(book);
                return true;
            }

//            if (user.getType().equals("Student") && user.getBorrowedBooks().size() < 2) {
//                user.borrowBook(book);
//                books.remove(book);
//                return true;
//            } else if (user.getType().equals("Teacher") && user.getBorrowedBooks().size() < 5) {
//                user.borrowBook(book);
//                books.remove(book);
//                return true;
//            }

        }
        return false;
    }

    // Permitir que un usuario devuelva un libro
    public void returnBook(User user, Book book) {
//        if (!books.contains(book)) {
//            user.returnBook(book);
//            books.add(book);
//        }

        if(!bookRepository.exists(book)){
            user.returnBook(book);
            bookRepository.save(book);
        }


    }

    // Generar un reporte de la biblioteca
//    public String generateReport() {
//        StringBuilder report = new StringBuilder("Library Report\n");
//        report.append("Available Books:\n");
//        for (Book book : books) {
//            report.append(book.getTitle()).append("\n");
//        }
//        report.append("\nBorrowed Books:\n");
//        for (User user : users) {
//            for (Book borrowedBook : user.getBorrowedBooks()) {
//                report.append(borrowedBook.getTitle()).append(" borrowed by ").append(user.getName()).append("\n");
//            }
//        }
//        return report.toString();
//    }

    // Método para verificar si un libro está disponible en la biblioteca
//    public boolean isBookAvailable(Book book) {
//       return books.contains(book);
//
//    }


    // Obtener una lista inmutable de los libros
    public List<Book> getBooks() {
//        return Collections.unmodifiableList(books);
        return bookRepository.findAll();
    }

    // Obtener una lista inmutable de los usuarios
    public List<User> getUsers() {
        //return Collections.unmodifiableList(users);
        return userRepository.findAll();
    }
}