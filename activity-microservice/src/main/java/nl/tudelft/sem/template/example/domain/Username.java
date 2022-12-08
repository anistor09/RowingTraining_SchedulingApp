package nl.tudelft.sem.template.example.domain;

import lombok.EqualsAndHashCode;

/**
 * An object representing the username of the Participant
 */
@EqualsAndHashCode
public class Username {
    private final transient String usernameValue;

    public Username(String usernameValue){
        this.usernameValue=usernameValue;
    }

    @Override
    public String toString(){
        return usernameValue;
    }
}
