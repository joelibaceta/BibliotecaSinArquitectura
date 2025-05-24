
package main.java.repository;


import main.java.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public void save(User user) {
        if (!users.contains(user)) users.add(user);
    }

    public Optional<User> findByName(String name) {
        return users.stream()
                    .filter(u -> u.getName().equalsIgnoreCase(name))
                    .findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public boolean exists(User user) {
        return users.contains(user);
    }
}
