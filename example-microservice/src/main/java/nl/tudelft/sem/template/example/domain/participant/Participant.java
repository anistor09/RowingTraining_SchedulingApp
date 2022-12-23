package nl.tudelft.sem.template.example.domain.participant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "participants")
@NoArgsConstructor
@Getter
@Setter
public class Participant  {
    @Id
    @SequenceGenerator(name = "participant",
            sequenceName = "participant",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "participant")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name= "username", nullable = false)
    @Convert(converter = NetIdAttributeConverter.class)
    private NetId netId;

    @Column(name="positions", nullable = false)
    @Convert(converter = PositionAttributeCoverter.class)
    private PositionManager positionManager;

    @Column(name="gender", nullable = false)
    private String gender;

    @Column(name="certificate")
    @Convert(converter = CertificateAttributeConverter.class)
    private Certificate certificate;

    @Column(name = "organization")
    private String organization;

    @Column(name = "level")
    private Boolean level;


    /**
     * Constructor method for the participant
     * @param netId netId of the user
     * @param positionManager positions that the participant is able to fill
     * @param gender gender of the participant
     * @param certificate certificate of the participant
     * @param organization organization of the participant
     * @param level if he is competitive or not
     */
    public Participant(NetId netId, PositionManager positionManager, String gender, Certificate certificate, String organization, Boolean level){
        this.netId= netId;
        this.positionManager= positionManager;
        this.gender=gender;
        this.certificate= certificate;
        this.organization= organization;
        this.level=level;
    }
//    public void requestMatch(List<String> timeSlots){
//        //this.recordThat(new ParticipantRequestedMatchEvent(this.username,timeSlots));
//        TestEvent tv = new TestEvent(this.netId,timeSlots);
//        this.recordThat(tv);
//    }

}
