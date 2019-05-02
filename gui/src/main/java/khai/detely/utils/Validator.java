package khai.detely.utils;

import java.util.Objects;

public class Validator {

    public static boolean validInteger(String str) {
        try {
            Integer.valueOf(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validDouble(String str) {
        try {
            Double.valueOf(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validateDirectionTextField(String str) {
        return Objects.nonNull(str) && !str.isEmpty();
    }
}
