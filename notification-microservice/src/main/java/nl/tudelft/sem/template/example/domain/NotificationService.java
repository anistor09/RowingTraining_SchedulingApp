package nl.tudelft.sem.template.example.domain;

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

    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    public List<Notification> getUserNotifications(NetId netId){
        return notificationRepository.getAllByNetId(netId);
    }

    public List<Notification> getOwnerNotifications(NetId ownerId){
        return notificationRepository.getAllByOwnerId(ownerId);
    }

    public void deleteNotification(Notification n) {
        notificationRepository.delete(n);
    }
}
