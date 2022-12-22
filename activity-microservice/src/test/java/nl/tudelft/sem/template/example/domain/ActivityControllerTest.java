package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.controllers.ActivityController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

public class ActivityControllerTest {
    @Mock
    private ActivityService activityService;

    @Mock
    private AuthManager authManager;

    @Test
    public void createTrainingSuccessful() {
        ActivityController activityController = new ActivityController(authManager, activityService);
        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 10:00; 10-10-2022 13:00"), "boat", List.of("cox", "captain"));
        Training training = new Training(new NetId("paula"), new TimeSlot("10-10-2022 10:00; 10-10-2022 13:00"), "boat", List.of("cox", "captain"));
        when(activityService.createTraining(new NetId("paula"), request)).thenReturn(training);
        ResponseEntity<Training> response = activityController.createTraining(request);
        verify(activityService).createTraining(new NetId("paula"), request);
        assertEquals(training, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

}
