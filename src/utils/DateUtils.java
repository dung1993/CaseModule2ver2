package src.utils;

import java.util.regex.Pattern;

public class DateUtils {
    public static final String NAME_REGEX = "^([A-Z]+[a-z]*[ ]?)+$";
    public static final String USERNAME_REGEX = "^[A-Za-z0-9]{8,16}$";
    public static final String PASSWORD_REGEX = "^[A-Za-z)-9]{8,12}$";
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";

    public static boolean isPassWordInvalid(String password){
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }

    public static boolean isNameInvalid(String name){
        return Pattern.compile(NAME_REGEX).matcher(name).matches();
    }

    public static boolean isUserNameInvalid(String username){
        return Pattern.compile(USERNAME_REGEX).matcher(username).matches();
    }

    public static boolean isPhoneInvalid(String phone){
        return Pattern.compile(PHONE_REGEX).matcher(phone).matches();
    }
}
