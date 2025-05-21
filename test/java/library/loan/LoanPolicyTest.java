
package library.loan;

import library.domain.User;
import library.domain.Book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanPolicyTest {

    @Test
    public void testStudentLoanPolicyAllowsUpTo3Books() {
        User student = new User("Alice", "Student");
        StudentLoanPolicy policy = new StudentLoanPolicy();

        assertTrue(policy.canBorrow(student));
        student.borrowBook(new Book("Book 1"));
        student.borrowBook(new Book("Book 2"));
        student.borrowBook(new Book("Book 3"));
        assertFalse(policy.canBorrow(student));
    }

    @Test
    public void testTeacherLoanPolicyAllowsUpTo5Books() {
        User teacher = new User("Bob", "Teacher");
        TeacherLoanPolicy policy = new TeacherLoanPolicy();

        for (int i = 1; i <= 5; i++) {
            teacher.borrowBook(new Book("Book " + i));
        }
        assertFalse(policy.canBorrow(teacher));
    }
}
