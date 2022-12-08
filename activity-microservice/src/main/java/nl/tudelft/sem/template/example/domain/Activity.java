package nl.tudelft.sem.template.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "participants")
@NoArgsConstructor
@Getter
@Setter
public abstract class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name= "owner", nullable = false)
    @Convert(converter = UsernameAttributeConverter.class)
    private Username owner;

    @Column(name= "date", nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime dateTime;

    @Column(name= "boat", nullable = false)
    private String boat;

    @Column(name= "positions", nullable = false)
    @Convert(converter = PositionListConverter.class)
    private List<String> positions;

    public Activity(Username owner, LocalDateTime dateTime, String boat, List<String> positions) {
        this.owner = owner;
        this.dateTime = dateTime;
        this.boat = boat;
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", owner=" + owner +
                ", dateTime=" + dateTime +
                ", boat='" + boat + '\'' +
                ", positions=" + positions +
                '}';
    }
}
