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
     * The date of the activity.
     */
    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    /**
     * The time of the activity.
     */
    @Column(name = "time", nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

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
     * @param date
     * @param time
     * @param boat
     * @param positions
     */
    public Activity(Username owner, LocalDate date, LocalTime time, String boat, List<String> positions) {
        this.owner = owner;
        this.date = date;
        this.time = time;
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
                ", date=" + date +
                ", time=" + time +
                ", boat='" + boat + '\'' +
                ", positions=" + positions +
                '}';
    }
}
