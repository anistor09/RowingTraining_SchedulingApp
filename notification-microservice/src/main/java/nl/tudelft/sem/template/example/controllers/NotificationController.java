package nl.tudelft.sem.template.example.controllers;


import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.ActivityId;
import nl.tudelft.sem.template.example.domain.NetId;
import nl.tudelft.sem.template.example.domain.Notification;
import nl.tudelft.sem.template.example.domain.NotificationService;
import nl.tudelft.sem.template.example.domain.models.NotificationRequestModel;
import org.aspectj.weaver.ast.Not;
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
    private final transient AuthManager authManager;

    @Autowired
    public NotificationController(NotificationService notificationService, AuthManager authManager){
        this.notificationService = notificationService;
        this.authManager = authManager;
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
        boolean ownerNotitication = request.isOwnerNotification();
        NetId ownerId = new NetId(request.getOwnerId());
        Notification n = notificationService.createNotification(activityId, netId, ownerId, message, ownerNotitication);
        return ResponseEntity.ok(n);
    }

    @PostMapping("/addNotification")
    public ResponseEntity<Notification> addNotification(@RequestBody Notification request){
        return ResponseEntity.ok(notificationService.addNotification(request));
    }

    @GetMapping("/getUserNotifications")
    public ResponseEntity<List<Notification>> getUserNotifications(){
        return ResponseEntity.ok(notificationService.getUserNotifications(new NetId(authManager.getNetId())));
    }
}
