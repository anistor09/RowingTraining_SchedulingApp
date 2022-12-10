package nl.tudelft.sem.template.example.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A DDD value object representing a NetID in our domain.
 */
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class NetId {
    private  transient String netIdValue;

    public NetId(String netId) {
        // validate NetID
        this.netIdValue = netId;
    }


}
