package nl.tudelft.sem.template.authentication.domain.user;

import lombok.EqualsAndHashCode;

/**
 * A DDD value object representing a password in our domain.
 */
@EqualsAndHashCode
public class Password {
    private final transient String passwordValue;

    public Password(String password) {
        // Validate input
        this.passwordValue = password;
    }

    public boolean isValid(){
        return (passwordValue.length() > 5) && (passwordValue != null);
    }

    @Override
    public String toString() {
        return passwordValue;
    }
}
