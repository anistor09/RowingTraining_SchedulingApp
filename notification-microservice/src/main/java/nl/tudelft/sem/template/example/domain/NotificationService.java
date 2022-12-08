package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.domain.ActivityId;
import nl.tudelft.sem.template.example.domain.NetId;
import nl.tudelft.sem.template.example.domain.NotificationRepository;
import org.springframework.stereotype.Service;
import nl.tudelft.sem.template.example.domain.participant.Notification;

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
        notificationRepository.save(n);
        return n;
    }

    public Notification addNotification(Notification notification){
        notificationRepository.save(notification);
        return notification;
    }

    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }
}
