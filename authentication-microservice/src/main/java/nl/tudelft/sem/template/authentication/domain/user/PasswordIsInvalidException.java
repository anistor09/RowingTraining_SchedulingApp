package nl.tudelft.sem.template.authentication.domain.user;

public class PasswordIsInvalidException extends Exception {
    private static final long serialVersionUID = 10;
    public PasswordIsInvalidException() {
        super("Password is invalid.");
    }
}
