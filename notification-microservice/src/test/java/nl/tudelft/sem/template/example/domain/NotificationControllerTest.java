package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.controllers.NotificationController;
import nl.tudelft.sem.template.example.domain.models.NotificationRequestModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
public class NotificationControllerTest {
    @Mock
    private NotificationService notificationService;

    @Mock
    private AuthManager authManager;

    @Test
    public void createParticipantNotificationSuccessful() {
        NotificationController notificationController = new NotificationController(notificationService, authManager);
        //NOT FINISHED
    }
}
