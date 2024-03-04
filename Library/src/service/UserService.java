package service;

import enums.Role;
import models.User;
import repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Role getUserRole(int userId) {

        User user = userRepository.getUserById(userId);
        return (user != null) ? user.getRole() : null;
    }

    public void registerUser(String username, String password, Role role) {
        User user = new User( username, password, role, generateUserId());
        userRepository.addUser(user);
        System.out.println("Пользователь успешно зарегистрирован: " + user);
    }

    public void removeUser(int userId) {
        User user = userRepository.getUserById(userId);
        if (user != null) {
            userRepository.removeUser(userId);
            System.out.println("Пользователь успешно удален: " + user);
        } else {
            System.out.println("Пользователь не найден по ID: " + userId);
        }
    }

    public void displayAllUsers() {
        System.out.println("Все юзеры:");
        for (User user : userRepository.getAllUsers()) {
            System.out.println(user);
        }
    }

    private int generateUserId() {

        return (int) (Math.random() * 1000);
    }
}

