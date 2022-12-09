package nl.tudelft.sem.template.example.domain;

import javax.persistence.Entity;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class Competition extends Activity{
    private String organization;
    private String gender;
    private boolean competitive;

    public Competition(Username owner, LocalDate date, LocalTime time, String boat, List<String> positions, String organization, String gender, boolean competitive) {
        super(owner, date, time, boat, positions);
        this.organization = organization;
        this.gender = gender;
        this.competitive = competitive;
    }
}
