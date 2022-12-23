package nl.tudelft.sem.template.authentication.domain.user;

public class PasswordIsInvalidException extends Exception {
    public PasswordIsInvalidException() {
        super("Password is invalid.");
    }
}
