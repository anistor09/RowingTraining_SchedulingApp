package nl.tudelft.sem.template.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public abstract class Activity {

    @Id
    @Column(name = "id", nullable = false, updatable=false)
    private Long id;

    @Column(name= "activityName", nullable = false)
    private String name;

    @Column(name= "owner", nullable = false)
    @Convert(converter = NetIdAttributeConverter.class)
    private NetId owner;

    @Column(name= "date", nullable = false)
    @Convert(converter = TimeSlotConverter.class)
    private TimeSlot timeSlot;

    @Column(name= "boat", nullable = false)
    private String boat;

    @Column(name= "positions", nullable = false)
    @Convert(converter = PositionListConverter.class)
    private List<String> positions;

    public Activity(NetId owner, String name, TimeSlot timeSlot, String boat, List<String> positions) {
        this.name=name;
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
