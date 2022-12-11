package nl.tudelft.sem.template.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Competition extends Activity{
    private String organization;
    private String gender;
    private boolean competitive;

    public Competition(Username owner, TimeSlot timeSlot, String boat, List<String> positions, String organization, String gender, boolean competitive) {
        super(owner, timeSlot, boat, positions);
        this.organization = organization;
        this.gender = gender;
        this.competitive = competitive;
    }

}