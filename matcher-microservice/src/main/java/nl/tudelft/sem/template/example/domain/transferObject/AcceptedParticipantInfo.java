package nl.tudelft.sem.template.example.domain.transferObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AcceptedParticipantInfo {
    String activityName;
    String netId;
    String position;
    String timeslot;

    public AcceptedParticipantInfo(String activityName, String netId, String position, String timeslot) {
        this.activityName = activityName;
        this.netId = netId;
        this.position = position;
        this.timeslot = timeslot;
    }


}

