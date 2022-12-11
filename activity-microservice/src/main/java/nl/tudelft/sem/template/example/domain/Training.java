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
public class Training extends Activity{

    /**
     * Constructor for Training.
     * @param owner
     * @param timeSlot
     * @param boat
     * @param positions
     */
    public Training(Username owner, TimeSlot timeSlot, String boat, List<String> positions) {
        super(owner, timeSlot, boat, positions);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}