package nl.tudelft.sem.template.example.domain.participant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tudelft.sem.template.example.domain.ActivityId;
import nl.tudelft.sem.template.example.domain.ActivityIdConverter;
import nl.tudelft.sem.template.example.domain.NetId;
import nl.tudelft.sem.template.example.domain.NetIdConverter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "notifications")
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "notification", sequenceName = "notification", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "notification")
    private int id;
    @Column(name = "activityID")
    @Convert(converter = ActivityIdConverter.class)
    private ActivityId activityID;
    @Column(name="receivingUser")
    @Convert(converter = NetIdConverter.class)
    private NetId userID;
    @Column(name="message")
    private String message;

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notification notif = (Notification) o;
        return id == (notif.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(activityID);
    }
    public Notification(ActivityId activityId, NetId netId, String message){
        this.activityID = activityId;
        this.userID = netId;
        this.message = message;
    }
}
