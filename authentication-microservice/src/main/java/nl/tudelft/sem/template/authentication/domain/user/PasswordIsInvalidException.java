package nl.tudelft.sem.template.authentication.domain.user;

public class PasswordIsInvalidException extends Exception {
    private serialVersionUID = 1L;
    public PasswordIsInvalidException() {
        super("Password is invalid.");
    }
}
