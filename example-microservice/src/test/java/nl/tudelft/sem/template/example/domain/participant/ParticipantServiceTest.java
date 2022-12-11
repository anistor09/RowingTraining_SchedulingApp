package nl.tudelft.sem.template.example.domain.participant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ParticipantServiceTest {


    ParticipantService ps;
    private ParticipantRepository mockParticipantRepository;

    @BeforeEach
    public void Setup(){
        mockParticipantRepository=mock(ParticipantRepository.class);
        ps=new ParticipantService(mockParticipantRepository);
    }


    @Test
    void getParticipantTest() {
        NetId netId= new NetId("user");
        Participant p= new Participant(netId,new PositionManager("coach,cox"),"M",null,"org","level");
        when(mockParticipantRepository.findByNetId(netId)).thenReturn(Optional.of(p));
        assertTrue(ps.getParticipant(netId).equals(p));
    }

    @Test
    void getParticipantPositions() {
        NetId netId= new NetId("user");
        Participant p= new Participant(netId,new PositionManager("coach,cox"),"M",null,"org","level");
        when(mockParticipantRepository.findByNetId(netId)).thenReturn(Optional.of(p));
        List<String> result= new ArrayList<>();
        result.add("coach");
        result.add("cox");
        assertTrue(ps.getParticipantPositions(netId).equals(result));
    }

    @Test
    void getParticipantCertificate() {
        NetId netId= new NetId("user");
        Certificate cer= new Certificate("C4");
        Participant p= new Participant(netId,new PositionManager("coach,cox"),"M",cer,"org","level");
        when(mockParticipantRepository.findByNetId(netId)).thenReturn(Optional.of(p));
        assertTrue(ps.getParticipantCertificate(netId).equals(cer.toString()));
    }

    @Test
    void getParticipantOrganization() {
        NetId netId= new NetId("user");
        Participant p= new Participant(netId,new PositionManager("coach,cox"),"M",null,"org","level");
        when(mockParticipantRepository.findByNetId(netId)).thenReturn(Optional.of(p));
        assertTrue(ps.getParticipantOrganization(netId).equals("org"));
    }

    @Test
    void getParticipantGender() {
        NetId netId= new NetId("user");
        Participant p= new Participant(netId,new PositionManager("coach,cox"),"M",null,"org","level");
        when(mockParticipantRepository.findByNetId(netId)).thenReturn(Optional.of(p));
        assertTrue(ps.getParticipantGender(netId).equals("M"));
    }

    @Test
    void getParticipantLevel() {
        NetId netId= new NetId("user");
        Participant p= new Participant(netId,new PositionManager("coach,cox"),"M",null,"org","pro");
        when(mockParticipantRepository.findByNetId(netId)).thenReturn(Optional.of(p));
        assertTrue(ps.getParticipantLevel(netId).equals("pro"));
    }
}