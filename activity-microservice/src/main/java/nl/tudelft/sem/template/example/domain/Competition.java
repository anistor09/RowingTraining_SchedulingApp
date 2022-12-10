package nl.tudelft.sem.template.example.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public class Competition extends Activity{
    private String organization;
    private String gender;
    private boolean competitive;

    /**
     * Constructor for Competition.
     * @param owner
     * @param date
     * @param time
     * @param boat
     * @param positions
     * @param organization
     * @param gender
     * @param competitive
     */
    public Competition(Username owner, LocalDate date, LocalTime time, String boat, List<String> positions, String organization, String gender, boolean competitive) {
        super(owner, date, time, boat, positions);
        this.organization = organization;
        this.gender = gender;
        this.competitive = competitive;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isCompetitive() {
        return competitive;
    }

    public void setCompetitive(boolean competitive) {
        this.competitive = competitive;
    }
}
