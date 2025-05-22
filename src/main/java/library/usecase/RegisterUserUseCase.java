package main.java.library.usecase;

import main.java.library.domain.model.User;
import main.java.library.domain.repository.UserRepository;

public class RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user) {
        if (!userRepository.getAll().contains(user)) {
            userRepository.add(user);
        }
    }
}
