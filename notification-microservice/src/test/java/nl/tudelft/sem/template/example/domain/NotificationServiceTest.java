package nl.tudelft.sem.template.example.domain;

        import nl.tudelft.sem.template.example.domain.models.NotificationRequestModel;
        import org.junit.jupiter.api.Test;
        import org.mockito.ArgumentCaptor;

        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.Mockito.mock;
        import static org.mockito.Mockito.verify;
        import static org.mockito.Mockito.when;

public class NotificationServiceTest {

    @Test
    public void createValidNotification() {
        ArgumentCaptor<Notification> captor = ArgumentCaptor.forClass(Notification.class);

        NotificationRepository notificationRepo = mock(NotificationRepository.class);
        when(notificationRepo.save(any(Notification.class))).thenReturn(null);
        NotificationService service = new NotificationService(notificationRepo);

        service.createNotification(new ActivityId("1"), new NetId("sem"), "accepted");
        verify(notificationRepo).save(captor.capture());
        Notification notification = captor.getValue();
        assertEquals("1", notification.getActivityId().toString());
        assertEquals("sem", notification.getNetId().toString());
        assertEquals("accepted", notification.getMessage());
    }
}