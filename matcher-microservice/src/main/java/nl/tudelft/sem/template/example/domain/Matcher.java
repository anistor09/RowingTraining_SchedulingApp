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
public class Matcher {

    @Id
    @SequenceGenerator(name = "match",
            sequenceName = "match",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "match")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name="participant", nullable = false)
    @Convert(converter = ParticipantAttributeCoverter.class)
    private Participant participant;

    @Column(name="activity", nullable = false)
    @Convert(converter = ActivityAttributeCoverter.class)
    private Activity activity;

    public Matcher(int id, Participant participant, Activity activity) {
        this.id = id;
        this.participant = participant;
        this.activity = activity;
    }
}
