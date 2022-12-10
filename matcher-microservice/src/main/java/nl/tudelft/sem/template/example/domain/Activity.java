package nl.tudelft.sem.template.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public abstract class Activity {

    @Column(name= "activityName", nullable = false)
    private String activityName;

    @Column(name= "owner", nullable = false)
    @Convert(converter = NetIdAttributeConverter.class)
    private NetId owner;

    @Column(name= "date", nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private TimeSlot timeSlot;

    @Column(name= "boat", nullable = false)
    private String boat;

    @Column(name= "positions", nullable = false)
    @Convert(converter = PositionListConverter.class)
    private List<String> positions;

    public Activity(String activityName,NetId owner, TimeSlot timeSlot, String boat, List<String> positions) {
        this.activityName=activityName;
        this.owner = owner;
        this.timeSlot = timeSlot;
        this.boat = boat;
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Activity{" +
                ", owner=" + owner +
                ", timeslot=" + timeSlot +
                ", boat='" + boat + '\'' +
                ", positions=" + positions +
                '}';
    }
}
