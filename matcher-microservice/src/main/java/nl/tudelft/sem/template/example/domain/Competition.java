package nl.tudelft.sem.template.example.domain;

import lombok.Getter;

import javax.persistence.Entity;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Getter
public class Competition extends Activity{
    private transient String organization;
    private transient String gender;
    private transient String competitive;

    public Competition(String activityName,NetId owner, TimeSlot timeSlot, String boat, List<String> positions, String organization, String gender, String competitive) {
        super(activityName,owner, timeSlot, boat, positions);
        this.organization = organization;
        this.gender = gender;
        this.competitive = competitive;
    }

    public Competition(String activityName,NetId owner, TimeSlot timeSlot, String boat, List<String> positions, String organization, String gender) {
        super(activityName,owner, timeSlot, boat, positions);
        this.organization = organization;
        this.gender = gender;
    }
}
