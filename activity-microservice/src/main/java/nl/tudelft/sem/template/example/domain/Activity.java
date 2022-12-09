package nl.tudelft.sem.template.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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

    @Column(name = "owner", nullable = false)
    @Convert(converter = UsernameAttributeConverter.class)
    private Username owner;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Column(name = "time", nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    @Column(name = "boat", nullable = false)
    private String boat;

    @Column(name = "positions", nullable = false)
    @Convert(converter = PositionListConverter.class)
    private List<String> positions;

    public Activity(Username owner, LocalDate date, LocalTime time, String boat, List<String> positions) {
        this.owner = owner;
        this.date = date;
        this.time = time;
        this.boat = boat;
        this.positions = positions;
    }

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
