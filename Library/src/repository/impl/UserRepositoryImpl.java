package repository.impl;

import enums.Role;
import models.User;
import repository.UserRepository;
import utils.MyArrayList;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private MyArrayList<User> users;

    public UserRepositoryImpl() {
        this.users = new MyArrayList<>();
        generateSampleUsers();
    }

    private void generateSampleUsers() {
        for (int i = 1; i <= 10; i++) {
            String username = "user" + i;
            String password = "password" + i;
            Role role = (i % 2 == 0) ? Role.ADMIN : Role.READER;
            User user = new User(username, password, role, generateUserId());
            addUser(user);
        }
    }
    private int generateUserId() {
        return (int) (Math.random() * 1000);
    }
    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void removeUser(int userId) {
        User userToRemove = getUserById(userId);
        if (userToRemove != null) {
            users.remove(userToRemove);
        }
    }

    @Override
    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public MyArrayList<User> getAllUsers() {
        return new MyArrayList<>(users);
    }

    @Override
    public int getUserIdByUsername(String username) {
        User user = getUserByUsername(username);
        return (user != null) ? user.getId() : -1;
    }

    @Override
    public boolean verifyPassword(int userId, String password) {
        User user = getUserById(userId);
        return (user != null) && user.getPassword().equals(password);
    }

}
