package main.java.library.domain.repository;
import main.java.library.domain.model.User;

import java.util.List;

public interface UserRepository {
    void add(User user);
    void remove(User user); 
    List<User> getAll();
    boolean contains(User user); 
}


