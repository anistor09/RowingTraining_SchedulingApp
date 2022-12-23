package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.domain.exceptions.NoNotificationsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final transient NotificationRepository notificationRepository;

    /**
     * Constructor for a notification service
     * @param notificationRepository
     */
    public NotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(ActivityId activityId, NetId netId, NetId ownerId, String message, boolean ownerNotification){
        Notification n = new Notification(activityId, netId, ownerId, message, ownerNotification);
        return notificationRepository.save(n);
    }

    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() throws NoNotificationsException {
        List<Notification> list = notificationRepository.findAll();
        if (list.isEmpty()){
            throw new NoNotificationsException("No notifications found");
        }
        return list;
    }

    public List<Notification> getUserNotifications(NetId netId) throws NoNotificationsException {
        List<Notification> list = notificationRepository.getAllByNetId(netId);
        if (list.isEmpty()){
            throw new NoNotificationsException("No notifications found for user " + netId);
        }
        return list;
    }

    public List<Notification> getOwnerNotifications(NetId ownerId) throws NoNotificationsException {
        List<Notification> list = notificationRepository.getAllByOwnerId(ownerId);
        if (list.isEmpty()) {
            throw new NoNotificationsException("No notifications found for this owner");
        }
        return list;
    }

    public void deleteNotification(Notification n) {
        notificationRepository.delete(n);
    }
}
