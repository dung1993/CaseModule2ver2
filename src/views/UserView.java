package src.views;

import src.menu.Menu;
import src.models.User;
import src.service.UserService;
import src.utils.AppUtils;
import src.utils.ValidateUtils;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class UserView {
    public List<User> products;
    private static UserService userService = new UserService();
    static Scanner scanner = new Scanner(System.in);

    public static void signIn() {
        boolean flag = true;
        do {
            System.out.println();
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚Create⟚Account⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            Long idUser = System.currentTimeMillis() / 1000;
            System.out.println("Your id account:");
            System.out.println("==> User" + idUser);
            String username = inputUsername(ChoiceStatus.ADD);
            String password = inputPassword(ChoiceStatus.ADD);
            String phoneNumber = inputPhoneNumber(ChoiceStatus.ADD);
            String roleUser = inputRoleUser();
            Instant createUserTime = Instant.now();
            User user = new User(idUser, username, password, phoneNumber, roleUser,createUserTime);
            System.out.println("Create Account Success, You can return menu sign in");
            userService.addUser(user);
            int choice;
            do {
                System.out.println("Press 0 to return to product manager");
                System.out.print("==>  ");
                choice = AppUtils.retryParseInt();
            } while (choice != 0);
            Menu.gearStore();
        } while (!flag);
    }



    public static void removeUser() {
        try {
            boolean flag = true;
            showUserList();
            Long id = inputIdUser(ChoiceStatus.REMOVE);
            System.out.println();
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("⥘⥘                    ⤿Delete User⤾               ⥘⥘");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("⥘⥘                       1. Yes                    ⥘⥘");
            System.out.println("⥘⥘                       2. Return                 ⥘⥘");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("Select your choice:");
            System.out.print("==>  ");
            do {
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        userService.removeUser(id);
                        System.out.println("Remove user success!");
                        showUserList();
                        Menu.menuManageUsers();
                        break;
                    case "2":
                        Menu.menuManageUsers();
                    default:
                        System.out.println("Is wrong choice! Please choice again");
                        System.out.print("==>  ");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void setRoleUser() {
        try {
            showUserList();
            System.out.println("Input id you need set");
            System.out.print("==>  ");
            Long id = AppUtils.parseLong();
            if (userService.exitsUser(id)) {
                boolean flagUpdate = true;
                String choice;
                System.out.println();
                System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                System.out.println("⥘⥘                ⤿Set Role User⤾               ⥘⥘");
                System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                System.out.println("⥘⥘           1. Set Admin                       ⥘⥘");
                System.out.println("⥘⥘           2. Set User                        ⥘⥘");
                System.out.println("⥘⥘           0. Return Menu User                ⥘⥘");
                System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                System.out.println("Select your choice:");
                System.out.print("==>  ");
                User newUser = new User();
                newUser.setIdUser(id);
                do {
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            setAdmin(newUser);
                            break;
                        case "2":
                            setUser(newUser);
                            break;
                        case "3":
                            Menu.menuUser();
                            break;
                        default:
                            System.out.println("Is wrong choice! Please choice again:");
                            System.out.print("==>  ");
                            flagUpdate = false;
                    }
                } while (!flagUpdate);
            } else {
                System.out.println("Id not found");
                setRoleUser();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private static void setAdmin(User user) {
        user.setRole("Admin");
        userService.editUser(user);
        System.out.println("Update role user success!");
        showUserListChoice();
        Menu.menuManageUsers();
    }

    private static void setUser(User user) {
        user.setRole("User");
        userService.editUser(user);
        System.out.println("Update role user success!");
        showUserListChoice();
        Menu.menuManageUsers();
    }

    private static String inputUsername(ChoiceStatus status) {
        String username;
        switch (status){
            case ADD:
                System.out.println("Enter your username(a-z, A-Z, 0-9 and no less than 5 char and no more than 16 char)");
                break;
        }
        System.out.print("==>  ");
        boolean flagInputName = true;
        do {
            username = scanner.nextLine().trim();
            boolean exits = (username.length() >= 5 && username.length() <= 16);
            switch (status){
                case ADD:
                    if (!exits){
                        System.out.println("Incorrect format please try again!");
                        System.out.print("==>  ");
                    }
                    flagInputName = !exits;
                    break;
            }
        }while (flagInputName);
        return username;
    }


    private static String inputPassword(ChoiceStatus status) {
        String password;
        switch (status) {
            case ADD:
                System.out.println("Enter your password(a to z, A to Z,0 to 9 , no less than 8 number and no more than 16 number)");
                break;
        }
        System.out.print("==>  ");
        boolean flagInputName = true;
        do {
            password = scanner.nextLine().trim();
            boolean exits = (password.length() >= 8 && password.length() <= 16);
            switch (status) {
                case ADD:
                    if (!exits) {
                        System.out.println("Incorrect format please try again!");
                        System.out.print("==>  ");
                    }
                    flagInputName = !exits;
                    break;
            }

        } while (flagInputName);
        return password;
    }

    private static String inputPhoneNumber(ChoiceStatus status) {
        String phoneNumber;
        switch (status) {
            case ADD:
                System.out.println("Enter your phone number:");
                break;
            case EDIT:
                System.out.println("Enter new phone number");
                break;
        }
        System.out.print("==>  ");
        boolean flagInputName = true;
        do {
            phoneNumber = scanner.nextLine().trim();
            if (!ValidateUtils.isPhoneInvalid(phoneNumber)) {
                System.out.println("Phone Number" + phoneNumber + "Incorrect format please try again");
                System.out.println(" Enter your mobile phone( 10 to 11 number and start at number 0)");
                System.out.print("==>  ");
                continue;
            }
            break;
        } while (flagInputName);
        return phoneNumber;
    }

    private static String inputRoleUser() {
        System.out.println("Input Admin password, not admin password you are user");
        System.out.print("==>  ");
        String setRole = scanner.nextLine();
        String role;
        if (setRole.equals("17041993")) {
            role = "Admin";
        } else {
            role = "User";
        }
        return role;
    }

    private static Long inputIdUser(ChoiceStatus status) {
        Long idUser;
        switch (status) {
            case EDIT:
            case REMOVE:
                System.out.println("Input id you need repair");
                break;
        }
        System.out.print("==>  ");
        boolean flagInputId = true;
        do {
            idUser = AppUtils.parseLong();
            boolean exits = userService.exitsUser(idUser);
            switch (status) {
                case EDIT:
                case REMOVE:
                    if (!exits) {
                        System.out.println("Id not found! Please try again");
                        System.out.print("==>  ");
                    }
                    flagInputId = !exits;
                    break;
            }
        } while (flagInputId);
        return idUser;
    }
    public static void login() {
        User users;
        do {
            try {
                System.out.println();
                System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚Login⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                System.out.println("Please enter username");
                System.out.print("==>  ");
                String username = scanner.nextLine();
                System.out.println("Please enter password");
                System.out.print("==>  ");
                String password = scanner.nextLine();
                users = userService.loginUser(username, password);
                if (users == null) {
                    System.out.println("The account or password is incorrect! Please try again");
                    System.out.print("==>  ");
                    ContinueOrExit();
                }else{
                    if(users.getRole().equals("Admin")){
                        System.out.println("Login success");
                        Menu.menuAdmin();
                    }else{
                        if (users.getRole().equals("User")) {
                            System.out.println("Login success");
                            Menu.menuUser();
                        }
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println("The account or password is incorrect! Please try again");
                System.out.print("==>  ");
                ContinueOrExit();
            }
        } while (true);
    }

    public static void showUserListChoice() {
        showUserList();
        int choice;
        do {
            System.out.println("Press 0 to return Users.");
            System.out.print("=> ");
            choice = AppUtils.retryParseInt();
        } while (choice != 0);
        Menu.menuManageUsers();
    }

    public static void showUserList() {
        System.out.println();
        System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚List⟚User⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
        System.out.printf("⥘⥘%-15s %-15s %-25s %-25s %-15s %-15s ⥘⥘\n", "ID", "Username", "Password", "Phone Number", "Role","CreateUserTime");
        System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
        List<User> list = userService.findAllUsers();
        for (User user : list) {
            System.out.printf("⥘⥘%-15s %-15s %-25s %-25s %-15s %-15s⥘⥘\n",
                    user.getIdUser(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getPhoneNumber(),
                    user.getRole(),
                    user.getCreateUserTime());
        }
        System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
    }

    public static void ContinueOrExit() {
        boolean flag = true;
        do {
            System.out.println("Enter 1 to continues or 2 to Gear Store");
            System.out.print("=> ");
            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    break;
                case "2":
                    Menu.gearStore();
                    break;
                default:
                    System.out.println("Is wrong choice! Please choice again");
                    flag = false;
                    break;
            }
        }while (!flag);
    }
}
