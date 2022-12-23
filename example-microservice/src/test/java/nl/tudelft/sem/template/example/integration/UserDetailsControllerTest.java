package nl.tudelft.sem.template.example.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.authentication.JwtTokenVerifier;
import nl.tudelft.sem.template.example.domain.participant.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
// activate profiles to have spring use mocks during auto-injection of certain beans.
@ActiveProfiles({"test", "mockTokenVerifier", "mockAuthenticationManager","mockParticipantService"})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class UserDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private transient JwtTokenVerifier mockJwtTokenVerifier;

    @Autowired
    private transient AuthManager mockAuthenticationManager;

    private ParticipantRepository mockParticipantRepository;
    @Autowired
    private transient ParticipantService mockParticipantService;

    @BeforeEach
    public void Setup(){
        mockParticipantRepository=mock(ParticipantRepository.class);
        mockParticipantService=new ParticipantService(mockParticipantRepository);
    }


//    @Test
//    void getPositions() throws Exception {
//        List<String> list= Arrays.asList("cox");
//        NetId netId = new NetId("ExampleUser");
//
//        when(mockAuthenticationManager.getNetId()).thenReturn("ExampleUser");
//        when(mockJwtTokenVerifier.validateToken(anyString())).thenReturn(true);
//        when(mockJwtTokenVerifier.getNetIdFromToken(anyString())).thenReturn("ExampleUser");
//        Participant p = new Participant(new NetId("ExampleUser"),new PositionManager("cox"),"M",new Certificate("C4"),"org",true);
//        mockParticipantRepository.save(p);
//        //when(mockParticipantService.getParticipantPositions(anyObject())).thenReturn(list);
//        ResultActions result = mockMvc.perform(get("/userDetails/positions")
//                .contentType(MediaType.APPLICATION_JSON).
//                header("Authorization", "Bearer MockedToken"));
//
//        String response = result.andReturn().getResponse().getContentAsString();
//        ObjectMapper mapper= new ObjectMapper();
//        List<String> positions = mapper.readValue(response, new TypeReference<List<String>>(){});
//        assertThat(positions.size()>0);
//    }

    @Test
    void getCertificate() {
    }

    @Test
    void getGender() {
    }

    @Test
    void getLevel() {
    }

    @Test
    void getOrganization() {
    }
}