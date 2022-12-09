package nl.tudelft.sem.template.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tudelft.sem.template.example.domain.NetIdAttributeConverter;
import nl.tudelft.sem.template.example.domain.PositionAttributeCoverter;
import nl.tudelft.sem.template.example.domain.participant.CertificateAttributeConverter;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
public class Participant  {

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
    private String level;


    public Participant(NetId netId, PositionManager positionManager, String gender, Certificate certificate, String organization, String level){
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
