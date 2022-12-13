package nl.tudelft.sem.template.example.domain.transferObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransferMatch {
    String activityName;
    String position;
    String  timeSlot;

    String netId;
    public TransferMatch(String activityName, String position, String timeSlot,String netId) {
        this.activityName = activityName;
        this.position = position;
        this.timeSlot = timeSlot;
        this.netId = netId;
    }
}
