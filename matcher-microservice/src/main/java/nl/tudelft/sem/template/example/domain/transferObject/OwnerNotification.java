package nl.tudelft.sem.template.example.domain.transferObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tudelft.sem.template.example.domain.NetId;
@NoArgsConstructor
@Setter
@Getter
public class OwnerNotification {
    String netId;
    String position;
    String activityName;
    String owner;

    public OwnerNotification(String netId, String position, String activityName, String owner) {
        this.netId = netId;
        this.position = position;
        this.activityName = activityName;
        this.owner = owner;
    }
}
