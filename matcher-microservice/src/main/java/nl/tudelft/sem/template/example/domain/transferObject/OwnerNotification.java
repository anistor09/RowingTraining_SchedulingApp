package nl.tudelft.sem.template.example.domain.transferObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tudelft.sem.template.example.domain.NetId;
@NoArgsConstructor
@Setter
@Getter
public class OwnerNotification {
    NetId netId;
    String position;
    String activityName;
    NetId owner;

    public OwnerNotification(NetId netId, String position, String activityName, NetId owner) {
        this.netId = netId;
        this.position = position;
        this.activityName = activityName;
        this.owner = owner;
    }
}
