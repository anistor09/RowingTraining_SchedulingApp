package nl.tudelft.sem.template.example.domain;

import javax.persistence.Entity;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Competition extends Activity{
    private String organization;
    private String gender;
    private boolean competitive;

    public Competition(String activityName,NetId owner, LocalDateTime dateTime, String boat, List<String> positions, String organization, String gender, boolean competitive) {
        super(activityName,owner, dateTime, boat, positions);
        this.organization = organization;
        this.gender = gender;
        this.competitive = competitive;
    }

    public Competition(String activityName,NetId owner, LocalDateTime dateTime, String boat, List<String> positions, String organization, String gender) {
        super(activityName,owner, dateTime, boat, positions);
        this.organization = organization;
        this.gender = gender;
    }
}
