package pe.tecsup.library.rules;

import pe.tecsup.library.model.User;

public interface LoanRule {
    boolean canBorrow(User user);
    int getMaxBooks();
}

