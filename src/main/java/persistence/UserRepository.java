package main.java.persistence;

import main.java.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> users = new ArrayList<>();
    public void save(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }
    public boolean exists(User user) {
        return users.contains(user);
    }
    public List<User> findAll() {
        return new ArrayList<>(users);
    }
}

