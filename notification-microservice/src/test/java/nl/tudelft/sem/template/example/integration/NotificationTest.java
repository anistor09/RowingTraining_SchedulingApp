package nl.tudelft.sem.template.example.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONUtil;
import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.authentication.JwtTokenVerifier;
import nl.tudelft.sem.template.example.domain.ActivityId;
import nl.tudelft.sem.template.example.domain.Notification;
import nl.tudelft.sem.template.example.domain.NotificationRepository;
import nl.tudelft.sem.template.example.domain.transferClasses.TransferMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
// activate profiles to have spring use mocks during auto-injection of certain beans.
@ActiveProfiles({"test", "mockTokenVerifier", "mockAuthenticationManager"})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class NotificationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private transient JwtTokenVerifier mockJwtTokenVerifier;

    @Autowired
    private transient AuthManager mockAuthenticationManager;

    @Autowired
    private transient NotificationRepository notificationRepository;

    @BeforeEach
    void setUp() {
        when(mockAuthenticationManager.getNetId()).thenReturn("ExampleUser");
        when(mockJwtTokenVerifier.validateToken(anyString())).thenReturn(true);
        when(mockJwtTokenVerifier.getNetIdFromToken(anyString())).thenReturn("ExampleUser");
    }

    @Test
    void createOwnerNotificationTest() throws Exception {
        TransferMatch tm = new TransferMatch(1L, "cox", "20-12-2022 09:00;20-12-2022 11:00", "ExampleUser", "Owner");
        ResultActions result = mockMvc.perform(post("/notifications/createOwnerNotification")
                .header("Authorization", "Bearer MockedToken")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.serialize(tm)));
        result.andExpect(status().isOk());

        String response = result.andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Notification received = mapper.readValue(response, Notification.class);
        Notification expected = notificationRepository.findByActivityId(new ActivityId("1")).get();
        assertThat(received).isEqualTo(expected);
    }

    @Test
    void createParticipantNotificationTest() throws Exception {
        TransferMatch tm = new TransferMatch(1L, "cox", "20-12-2022 09:00;20-12-2022 11:00", "ExampleUser", "Owner");
        List<TransferMatch> transferMatches = new ArrayList<>();
        transferMatches.add(tm);
        ResultActions result = mockMvc.perform(post("/notifications/createParticipantNotification")
                .header("Authorization", "Bearer MockedToken")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.serialize(transferMatches)));
        result.andExpect(status().isOk());

        String response = result.andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<Notification> received = mapper.readValue(response, new TypeReference<List<Notification>>() {});
        Notification expected = notificationRepository.findByActivityId(new ActivityId("1")).get();
        assertThat(received.get(0)).isEqualTo(expected);
    }

    @Test
    void getOwnerNotificationsTest() throws Exception{

    }
}
