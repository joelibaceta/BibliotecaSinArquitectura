package main.java.library.infrastructure.repository;

import main.java.library.domain.model.User;
import main.java.library.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        if (!users.contains(user)) users.add(user);
    }

    @Override
    public List<User> getAll() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public void remove(User user) {
        
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean contains(User user) {
        
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

        
    
}
