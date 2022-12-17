package nl.tudelft.sem.template.example.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "activities")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Training.class, name = "Training"),

        @JsonSubTypes.Type(value = Competition.class, name = "Competition") }
)
@NoArgsConstructor
@Getter
@Setter
public abstract class Activity {

    /**
     * The id of the activity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable=false)
    private Long id;

    /**
     * The name of the activity.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The owner of the activity.
     */
    @Column(name = "owner", nullable = false)
    @Convert(converter = NetIdAttributeConverter.class)
    private NetId owner;

    /**
     * The time slot of the activity.
     */
    @Column(name= "timeSlot", nullable = false)
    @Convert(converter = TimeSlotConverter.class)
    private TimeSlot timeSlot;

    /**
     * The required boat for the activity.
     */
    @Column(name = "boat", nullable = false)
    private String boat;

    /**
     * The required positions for the activity.
     */
    @Column(name = "positions", nullable = false)
    @Convert(converter = PositionListConverter.class)
    private List<String> positions;

    /**
     * The constructor for the activity.
     * @param owner
     * @param name
     * @param timeSlot
     * @param boat
     * @param positions
     */
    public Activity(NetId owner, String name, TimeSlot timeSlot, String boat, List<String> positions) {
        this.name = name;
        this.owner = owner;
        this.timeSlot = timeSlot;
        this.boat = boat;
        this.positions = positions;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
}
