package nl.tudelft.sem.template.example.controllers;


import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.ActivityId;
import nl.tudelft.sem.template.example.domain.NetId;
import nl.tudelft.sem.template.example.domain.Notification;
import nl.tudelft.sem.template.example.domain.NotificationService;
import nl.tudelft.sem.template.example.domain.models.NotificationRequestModel;
import nl.tudelft.sem.template.example.domain.transferClasses.TransferMatch;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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


    @PostMapping("/createParticipantNotification")
    public ResponseEntity<List<Notification>> createParticipantNotification(@RequestBody List<TransferMatch> requests){
        List<Notification> result = new ArrayList<>();
        for (TransferMatch request : requests){
            ActivityId activityId = new ActivityId(request.getActivityId().toString());
            NetId netId = new NetId(request.getNetId());
            NetId ownerId = new NetId(request.getOwner());
            String message = new String("You have been accepted by " + ownerId.toString()
                    + " to participate as a " + request.getPosition() + " on " + request.getTimeSlot());
            Notification temp = new Notification(activityId, netId, ownerId, message, false);
            notificationService.addNotification(temp);
            result.add(temp);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/createOwnerNotification")
    public ResponseEntity<Notification> createOwnerNotification (@RequestBody TransferMatch request){
        ActivityId activityId = new ActivityId(request.getActivityId().toString());
        NetId netId = new NetId(request.getNetId());
        NetId ownerId = new NetId(request.getOwner());
        String message = new String("The " + request.getPosition() + " " + netId.toString()
                + " would like to participate in your activity labeled " + request.getActivityId() + " at" +
                request.getTimeSlot());
        Notification temp = new Notification(activityId, netId, ownerId, message, true);
        notificationService.addNotification(temp);
        return ResponseEntity.ok(temp);
    }

    @GetMapping("/getOwnerNotifications")
    public ResponseEntity<List<Notification>> getOwnerNotifications(){
        List<Notification> notifications = notificationService.getUserNotifications(new NetId(authManager.getNetId()));
        List<Notification> result = new ArrayList<>();
        for (Notification n : notifications){
            if (n.isOwnerNotification())
                result.add(n);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getParticipantNotifications")
    public ResponseEntity<List<Notification>> getParticipantNotifications(){
        List<Notification> notifications = notificationService.getUserNotifications(new NetId(authManager.getNetId()));
        List<Notification> result = new ArrayList<>();
        for (Notification n : notifications){
            if (!n.isOwnerNotification())
                result.add(n);
        }
        return ResponseEntity.ok(result);
    }
}
