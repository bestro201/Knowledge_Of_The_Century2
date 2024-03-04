package repository;

import enums.Role;
import models.User;
import utils.MyArrayList;

import java.util.List;

import java.util.List;

public interface UserRepository {
    void addUser(User user);
    void removeUser(int userId);
    User getUserById(int userId);
    User getUserByUsername(String username);
    MyArrayList<User> getAllUsers();


    int getUserIdByUsername(String username);


    boolean verifyPassword(int userId, String password);

}
