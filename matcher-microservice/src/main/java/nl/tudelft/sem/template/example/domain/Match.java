package nl.tudelft.sem.template.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "matches")
@NoArgsConstructor
@Getter
@Setter
public class Match {

    @Id
    @SequenceGenerator(name = "match",
            sequenceName = "match",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "match")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name="participant", nullable = false)
    private String netId;

    @Column(name="activity", nullable = false)
    private String activityName;

    @Column(name="position", nullable = false)
    private String position;

    public Match(String netId, String activityName,String position) {
        this.netId = netId;
        this.activityName = activityName;
        this.position=position;
    }
}
