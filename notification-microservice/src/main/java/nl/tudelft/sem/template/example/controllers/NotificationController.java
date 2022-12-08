package nl.tudelft.sem.template.example.controllers;


import nl.tudelft.sem.template.example.domain.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import nl.tudelft.sem.template.example.domain.participant.Notification;
import java.util.List;

@RestController
@RequestMapping("notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping("/findAll")
    public List<Notification> findAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @PostMapping("/addNotif")
    public ResponseEntity<Notification> addNotif(@RequestBody Notification request) throws Exception{
        try{
            notificationService.addNotification(request);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
