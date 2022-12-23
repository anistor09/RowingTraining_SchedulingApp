package nl.tudelft.sem.template.example.domain;

        import nl.tudelft.sem.template.example.domain.exceptions.NoNotificationsException;
        import nl.tudelft.sem.template.example.domain.models.NotificationRequestModel;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.ArgumentCaptor;

        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.Mockito.mock;
        import static org.mockito.Mockito.verify;
        import static org.mockito.Mockito.when;

public class NotificationServiceTest {

    private NotificationService service;
    private NotificationRepository notificationRepo;
    private NetId paula = new NetId("paula");
    private NetId minouk = new NetId("minouk");
    private NetId owner = new NetId("owner");

    private Notification forPaula = new Notification(new ActivityId("1"), paula, owner, "message", false);
    private Notification forMinouk = new Notification(new ActivityId("2"), minouk, owner, "message", false);
    private Notification forOwner = new Notification(new ActivityId("3"), owner, owner, "message", true);

    @BeforeEach
    public void setUp() {
        notificationRepo = mock(NotificationRepository.class);
        service = new NotificationService(notificationRepo);
    }

    @Test
    public void createValidNotification() {
        ArgumentCaptor<Notification> captor = ArgumentCaptor.forClass(Notification.class);
        service.createNotification(new ActivityId("1"), new NetId("paula"), new NetId("owner"),"message", false);
        verify(notificationRepo).save(captor.capture());
        Notification notification = captor.getValue();
        assertEquals(forPaula, notification);
    }


    @Test
    public void getAllWithOneNotification() throws NoNotificationsException {
        when(notificationRepo.findAll()).thenReturn(List.of(forPaula));
        List<Notification> notifications = service.getAllNotifications();
        assertEquals(1, notifications.size());
        assertEquals(forPaula, notifications.get(0));
    }

    @Test
    public void getAllWithNoNotifications() throws NoNotificationsException {
        when(notificationRepo.findAll()).thenReturn(List.of());
        assertThrows(NoNotificationsException.class, () -> service.getAllNotifications());
    }

    @Test
    public void getUserNotificationsWithNoNotifications() throws NoNotificationsException {
        when(notificationRepo.getAllByNetId(any())).thenReturn(List.of());
        assertThrows(NoNotificationsException.class, () -> service.getUserNotifications(paula));
    }

    @Test
    public void getUserNotificationsWithOneNotification() throws NoNotificationsException {
        when(notificationRepo.getAllByNetId(any())).thenReturn(List.of(forPaula));
        List<Notification> notifications = service.getUserNotifications(new NetId("paula"));
        assertEquals(1, notifications.size());
        assertEquals(forPaula, notifications.get(0));
    }

    @Test
    public void getOwnerNotificationsWithNoNotifications() throws NoNotificationsException {
        when(notificationRepo.getAllByOwnerId(any())).thenReturn(List.of());
        assertThrows(NoNotificationsException.class, () -> service.getOwnerNotifications(new NetId("sem")));
    }

    @Test
    public void getOwnerNotificationsWithOneNotification() throws NoNotificationsException {
        when(notificationRepo.getAllByOwnerId(any())).thenReturn(List.of(forOwner));
        List<Notification> notifications = service.getOwnerNotifications(new NetId("owner"));
        assertEquals(1, notifications.size());
        assertEquals(forOwner, notifications.get(0));
    }
}