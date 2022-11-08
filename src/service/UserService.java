package src.service;

import src.models.User;
import src.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{
    private final String Path = "D:\\CaseModule2ver2\\data\\Users.csv";
    public static UserService instanceUserService;

    public UserService(){

    }

    public UserService getInstanceUserService(){
        if (instanceUserService == null){
            instanceUserService = new UserService();
        }
        return instanceUserService;
    }
    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        List<String> records = CSVUtils.read(Path);
        for (String record: records) {
            users.add(User.parseUser(record));
        }
        return users;
    }

    @Override
    public User loginUser(String username, String password) {
        List<User> users = findAllUsers();
        for (User user: users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(User newUser) {
        List<User> users = findAllUsers();
        users.add(newUser);
        CSVUtils.write(Path,users);
    }

    @Override
    public void removeUser(Long idUser) {
        List<User> users = findAllUsers();
        users.removeIf(id -> id.getIdUser().equals(idUser));
        CSVUtils.write(Path,users);
    }

    @Override
    public void editUser(User newUser) {
        List<User> users = findAllUsers();
        for (User user: users) {
            if (user.getIdUser().equals(newUser.getIdUser())){
                String phoneNumber = newUser.getPhoneNumber();
                if (phoneNumber != null && !phoneNumber.isEmpty()){
                    user.setPhoneNumber(phoneNumber);
                }
                String role = newUser.getRole();
                if (role != null && !role.isEmpty()){
                    user.setRole(role);
                }
                CSVUtils.write(Path,users);
            }
        }
    }

    @Override
    public User findIdUser(Long IdUser) {
        List<User> users = findAllUsers();
        for (User user: users) {
            if (user.getIdUser().equals(IdUser)){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean exitsUser(Long idUser) {
        return findIdUser(idUser) != null;
    }

    @Override
    public boolean exitsUserName(String name) {
        List<User> users = findAllUsers();
        for (User user : users){
            if (user.getUsername().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean exitsPhoneNumber(String phoneNumber) {
        List<User> users = findAllUsers();
        for (User user : users){
            if (user.getPhoneNumber().equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }
}
