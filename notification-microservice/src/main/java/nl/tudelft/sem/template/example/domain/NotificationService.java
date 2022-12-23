package nl.tudelft.sem.template.example.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * Creates a notification.
     * @param activityId
     * @param netId
     * @param ownerId
     * @param message
     * @param ownerNotification
     * @return
     */
    public Notification createNotification(ActivityId activityId, NetId netId, NetId ownerId, String message, boolean ownerNotification){
        Notification n = new Notification(activityId, netId, ownerId, message, ownerNotification);
        return notificationRepository.save(n);
    }

    /**
     * Adds a notification to the database.
     * @param notification
     * @return
     */
    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    /**
     * Gets all notifications.
     * @return
     */
    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    /**
     * Gets all notifications for a specific user.
     * @param netId
     * @return
     */
    public List<Notification> getUserNotifications(NetId netId){
        return notificationRepository.getAllByNetId(netId);
    }

    /**
     * Gets all notifications for a specific user.
     * @param ownerId
     * @return
     */
    public List<Notification> getOwnerNotifications(NetId ownerId){
        return notificationRepository.getAllByOwnerId(ownerId);
    }

    /**
     * Deletes a notification.
     * @param n
     */
    public void deleteNotification(Notification n) {
        notificationRepository.delete(n);
    }
}
