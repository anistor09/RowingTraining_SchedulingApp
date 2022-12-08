package nl.tudelft.sem.template.example.controllers;


import nl.tudelft.sem.template.example.domain.ActivityId;
import nl.tudelft.sem.template.example.domain.NetId;
import nl.tudelft.sem.template.example.domain.Notification;
import nl.tudelft.sem.template.example.domain.NotificationService;
import nl.tudelft.sem.template.example.domain.models.NotificationRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("notifications")
public class NotificationController {
    private final transient NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping("/findAll")
    public List<Notification> findAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @PostMapping("/addNotif")
    public ResponseEntity<Notification> addNotif(@RequestBody NotificationRequestModel request){
        ActivityId activityId = new ActivityId(request.getActivityId());
        NetId netId = new NetId(request.getNetId());
        String message = request.getMessage();
        Notification n = notificationService.createNotification(activityId, netId, message);
        return ResponseEntity.ok(n);
    }
}
