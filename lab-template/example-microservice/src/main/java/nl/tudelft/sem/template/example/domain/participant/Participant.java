package nl.tudelft.sem.template.example.domain.participant;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "participants")
@NoArgsConstructor
public class Participant {
    @Id
    @SequenceGenerator(name = "participant",
            sequenceName = "participant",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "participant")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name= "username", nullable = false)
    @Convert(converter = UsernameAttributeConverter.class)
    private nl.tudelft.sem.template.example.domain.participant.Username username;

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
    private String level;


    public Participant(nl.tudelft.sem.template.example.domain.participant.Username username, PositionManager positionManager, String gender, Certificate certificate, String organization, String level){
        this.username= username;
        this.positionManager= positionManager;
        this.gender=gender;
        this.certificate= certificate;
        this.organization= organization;
        this.level=level;
    }

}
