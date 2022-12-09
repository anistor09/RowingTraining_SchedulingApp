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
    @Column(name= "owner", nullable = false)
    @Convert(converter = NetIdAttributeConverter.class)
    private NetId owner;

    @Column(name= "date", nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime dateTime;

    @Column(name= "boat", nullable = false)
    private String boat;

    @Column(name= "positions", nullable = false)
    @Convert(converter = PositionListConverter.class)
    private List<String> positions;

    public Activity(NetId owner, LocalDateTime dateTime, String boat, List<String> positions) {
        this.owner = owner;
        this.dateTime = dateTime;
        this.boat = boat;
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Activity{" +
                ", owner=" + owner +
                ", dateTime=" + dateTime +
                ", boat='" + boat + '\'' +
                ", positions=" + positions +
                '}';
    }
}
