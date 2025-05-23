package main.java.domain.policy;

import main.java.domain.model.User;

public interface LoanPolicy {
    boolean canBorrow(User user);
}
