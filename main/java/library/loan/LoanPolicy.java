package library.loan;

import library.domain.User;


public interface LoanPolicy {
    boolean canBorrow(User user);
}