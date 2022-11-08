package src.service;

import src.models.User;

import java.util.List;

public interface IUserService {
    List<User> findAllUsers();

    User loginUser(String username, String password);

    void addUser(User newUser);

    void removeUser(Long idUser);

    void editUser(User newUser);

    User findIdUser(Long IdUser);

    boolean exitsUser(Long idUser);

    boolean exitsUserName(String Name);

    boolean exitsPhoneNumber(String phoneNumber);
}
