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

        service.createNotification(new ActivityId("1"), new NetId("sem"), new NetId("owner"),"test message", false);
        verify(notificationRepo).save(captor.capture());
        Notification notification = captor.getValue();
        assertEquals("1", notification.getActivityId().toString());
        assertEquals("sem", notification.getNetId().toString());
        assertEquals("owner", notification.getOwnerId().toString());
        assertEquals("test message", notification.getMessage());
        assertEquals(false, notification.isOwnerNotification());
    }
    //PASSED


    @Test
    public void getAllWithOneNotification() {
        NotificationRepository NotificationRepo = mock(NotificationRepository.class);
        NotificationService service = new NotificationService(NotificationRepo);
        Notification notification = new Notification(new ActivityId("1"), new NetId("sem"),new NetId("owner"),"test message", false);
        when(NotificationRepo.findAll()).thenReturn(List.of(notification));
        List<Notification> notifications = service.getAllNotifications();
        assertEquals(1, notifications.size());
        assertEquals("sem", notifications.get(0).getNetId().toString());
        assertEquals("1", notifications.get(0).getActivityId().toString());
        assertEquals("sem", notifications.get(0).getNetId().toString());
        assertEquals("owner", notifications.get(0).getOwnerId().toString());
        assertEquals("test message", notifications.get(0).getMessage());
        assertEquals(false, notifications.get(0).isOwnerNotification());
    }
    //PASSED

    @Test
    public void getAllWithNoNotifications() {
        NotificationRepository NotificationRepo = mock(NotificationRepository.class);
        NotificationService service = new NotificationService(NotificationRepo);
        when(NotificationRepo.findAll()).thenReturn(List.of());
        List<Notification> notifications = service.getAllNotifications();
        assertEquals(0, notifications.size());
    }
    //PASSED

    @Test
    public void getUserNotificationsWithNoNotifications() {
        NotificationRepository notificationRepo = mock(NotificationRepository.class);
        NotificationService service = new NotificationService(notificationRepo);
        when(notificationRepo.getAllByNetId(null)).thenReturn(List.of());
        List<Notification> notifications = service.getUserNotifications(new NetId("sem"));
        assertEquals(0, notifications.size());
    }
    //PASSED
    @Test
    public void getUserNotificationsWithOneNotification() {
        NotificationRepository NotificationRepo = mock(NotificationRepository.class);
        NotificationService service = new NotificationService(NotificationRepo);
        Notification notification = new Notification(new ActivityId("1"), new NetId("sem"),new NetId("owner"),"test message", false);
        when(NotificationRepo.getAllByNetId(notification.getNetId())).thenReturn(List.of(notification));
        List<Notification> notifications = service.getUserNotifications(new NetId("sem"));
        assertEquals(1, notifications.size());
        assertEquals("sem", notifications.get(0).getNetId().toString());
        assertEquals("1", notifications.get(0).getActivityId().toString());
        assertEquals("sem", notifications.get(0).getNetId().toString());
        assertEquals("owner", notifications.get(0).getOwnerId().toString());
        assertEquals("test message", notifications.get(0).getMessage());
        assertEquals(false, notifications.get(0).isOwnerNotification());
    }
    //PASSED but should be improved (see below)

    @Test
    public void getUserNotificationsWithWrongNetId() {
        NotificationRepository NotificationRepo = mock(NotificationRepository.class);
        NotificationService service = new NotificationService(NotificationRepo);
        Notification notification = new Notification(new ActivityId("1"), new NetId("sem"),new NetId("owner"),"test message", false);
        when(NotificationRepo.getAllByNetId(notification.getNetId())).thenReturn(List.of(notification));
        List<Notification> notifications = service.getUserNotifications(new NetId("sem2"));
        assertEquals(0, notifications.size());
    }
    //FAILS - NetId does not match but getUserNotifications still retrieves the notification






}