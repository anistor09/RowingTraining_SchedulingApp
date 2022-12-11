package nl.tudelft.sem.template.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
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
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The owner of the activity.
     */
    @Column(name = "owner", nullable = false)
    @Convert(converter = UsernameAttributeConverter.class)
    private Username owner;

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
     * @param timeSlot
     * @param boat
     * @param positions
     */
    public Activity(Username owner, TimeSlot timeSlot, String boat, List<String> positions) {
        this.owner = owner;
        this.timeSlot = timeSlot;
        this.boat = boat;
        this.positions = positions;
    }

    /**
     *
     * @return the string version of the activity
     */
    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", owner=" + owner +
                ", timeSlot=" + timeSlot +
                ", boat='" + boat + '\'' +
                ", positions=" + positions +
                '}';
    }
}
