package Util;

public final class validation {
    public static  boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }
    public static  boolean validateAge(int age) {
        if (age < 0 || age > 120) {
            return  false;
        }
        return true;
    }

}