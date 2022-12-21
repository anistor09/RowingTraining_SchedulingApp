package nl.tudelft.sem.template.authentication.domain.user;

import lombok.EqualsAndHashCode;

import java.util.regex.Pattern;

/**
 * A DDD value object representing a NetID in our domain.
 */
@EqualsAndHashCode
public class NetId {
    private final transient String netIdValue;

    private final transient Pattern pattern = Pattern.compile("[a-zA-Z0-9_]");

    public NetId(String netId) {
        // validate NetID
        this.netIdValue = netId;
    }

    public boolean isValid(){
        return (pattern.matcher(netIdValue).matches()) && (netIdValue.length() < 30) && (netIdValue != null);
    }

    @Override
    public String toString() {
        return netIdValue;
    }
}
