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
    public static boolean validateName(String name) {
        return (name.matches("[^a-zA-Z\\s\\-]\n"));
    }
    public static boolean validatePassword(String password1, String password2) {
        return (password1.equals(password2));
    }
    public static boolean validatePhone(String phone) {
        return (phone.matches("^(03|05|07|08|09)\\d{8}$\n"));
    }
}
