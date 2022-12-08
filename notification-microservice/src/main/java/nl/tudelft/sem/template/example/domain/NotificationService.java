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

    public Notification createNotification(ActivityId activityId, NetId netId, String message){
        Notification n = new Notification(activityId, netId, message);
        return notificationRepository.save(n);
    }

    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }
}
