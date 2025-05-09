package fun.steven.bookstore.exception;

public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }

    public static LoginException usernameError() {
        return new LoginException("Username is incorrect.");
    }

    public static LoginException passwordError() {
        return new LoginException("Password is incorrect.");
    }
}
