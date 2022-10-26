package src.Raw;

import java.time.Instant;

public class User {
    private Long idUser;
    private String username;
    private String password;
    private String phoneNumber;
    private String role;
    private Instant createUserTime;

    public User() {
    }

    public User(Long idUser, String username, String password, String phoneNumber, String role, Instant createUserTime) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createUserTime = createUserTime;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getCreateUserTime() {
        return createUserTime;
    }

    public void setCreateUserTime(Instant createUserTime) {
        this.createUserTime = createUserTime;
    }

    public String toString(){
        return String.format("%s,%s,%s,%s,%s,%s",idUser,username,password,phoneNumber,role,createUserTime);
    }

    public static User parseUser(String myUser){
        String[] array = myUser.split(",");
        User user = new User();
        user.setIdUser(Long.parseLong(array[0]));
        user.setUsername(array[1]);
        user.setPassword(array[2]);
        user.setRole(array[4]);
        user.setCreateUserTime(Instant.parse(array[5]));
        return user;
    }
}
