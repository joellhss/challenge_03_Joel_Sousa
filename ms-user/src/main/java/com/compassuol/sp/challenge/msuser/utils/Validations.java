package com.compassuol.sp.challenge.msuser.utils;

import java.util.regex.Pattern;

public class Validations {
    public static boolean cpfValidator(String cpf) {
        String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

        return Pattern.matches(regex, cpf);
    }

    public static boolean emailValidator(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return Pattern.matches(regex, email);
    }
}
