package service;


import repository.UserRepository;

public class AuthenticationService {
    private UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public int authenticate(String username, String password) {

        int userId = userRepository.getUserIdByUsername(username);


        if (userId != -1 && userRepository.verifyPassword(userId, password)) {
            return userId;
        } else {
            return -1;
        }
    }
}
