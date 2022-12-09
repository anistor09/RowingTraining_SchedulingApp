package nl.tudelft.sem.template.example.domain;

import javax.persistence.Entity;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class Training extends Activity{
    public Training() {
    }

    public Training(Username owner, LocalDate date, LocalTime time, String boat, List<String> positions) {
        super(owner, date, time, boat, positions);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public Username getOwner() {
        return super.getOwner();
    }

    @Override
    public LocalDate getDate() {
        return super.getDate();
    }

    @Override
    public LocalTime getTime() {
        return super.getTime();
    }

    @Override
    public void setDate(LocalDate date) {
        super.setDate(date);
    }

    @Override
    public void setTime(LocalTime time) {
        super.setTime(time);
    }

    @Override
    public String getBoat() {
        return super.getBoat();
    }

    @Override
    public List<String> getPositions() {
        return super.getPositions();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public void setBoat(String boat) {
        super.setBoat(boat);
    }

    @Override
    public void setPositions(List<String> positions) {
        super.setPositions(positions);
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
