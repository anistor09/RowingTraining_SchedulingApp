package nl.tudelft.sem.template.example.controllers;


import nl.tudelft.sem.template.example.domain.Notification;
import nl.tudelft.sem.template.example.domain.NotificationService;
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
    public ResponseEntity<Notification> addNotif(@RequestBody Notification request){
        Notification n = notificationService.addNotification(request);
        return ResponseEntity.ok(n);
    }
}
