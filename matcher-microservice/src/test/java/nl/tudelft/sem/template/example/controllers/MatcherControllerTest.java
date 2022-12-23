package nl.tudelft.sem.template.example.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.authentication.JwtTokenVerifier;
import nl.tudelft.sem.template.example.domain.*;
import nl.tudelft.sem.template.example.domain.transferObject.RequestMatch;
import nl.tudelft.sem.template.example.domain.transferObject.TransferMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
// activate profiles to have spring use mocks during auto-injection of certain beans.
@ActiveProfiles({"test", "mockTokenVerifier", "mockAuthenticationManager"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class MatcherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private transient JwtTokenVerifier mockJwtTokenVerifier;

    @Autowired
    private transient AuthManager mockAuthenticationManager;

    @Autowired
    private transient MatcherService matcherService;

    @BeforeEach
    void setUp() {
        when(mockAuthenticationManager.getNetId()).thenReturn("ExampleUser");
        when(mockJwtTokenVerifier.validateToken(anyString())).thenReturn(true);
        when(mockJwtTokenVerifier.getNetIdFromToken(anyString())).thenReturn("ExampleUser");

    }


    @Test
    void requestMatchTest() throws Exception {

//        ObjectMapper mapper = new ObjectMapper();
//        Participant participant = getParticipant();
//        List<String> timeSlots = new ArrayList<>();
//        timeSlots.add("");
//
//        RequestMatch requestMatch = new RequestMatch(participant,timeSlots);
//        String jsonRequest = mapper.writeValueAsString(requestMatch);
//
//        TransferMatch expected = new TransferMatch(1L,"cox","timeslot",
//                "participant","owner");
//        when(matcherService.computeMatch(any())).thenReturn(List.of(expected));
//
//        ResultActions result = mockMvc.perform(post("/requestMatch")
//                .header("Authorization", "Bearer MockedToken")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonRequest));
//        result.andExpect(status().isOk());
//
//        String response = result.andReturn().getResponse().getContentAsString();
//        List<TransferMatch> transferMatches = mapper.readValue(response, List.class);
//        TransferMatch transferMatch = transferMatches.get(0);
//
//        assertThat(transferMatch.getActivityId().equals(expected.getActivityId()));

    }

    private Participant getParticipant() {
        return new Participant(new NetId("participant"),
                new PositionManager("cox"),"M",
                new Certificate("C4"),"org",true);
    }


}