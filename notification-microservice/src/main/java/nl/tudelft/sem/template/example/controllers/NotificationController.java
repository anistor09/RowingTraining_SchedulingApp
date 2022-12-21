package nl.tudelft.sem.template.example.controllers;


import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.ActivityId;
import nl.tudelft.sem.template.example.domain.NetId;
import nl.tudelft.sem.template.example.domain.Notification;
import nl.tudelft.sem.template.example.domain.NotificationService;
import nl.tudelft.sem.template.example.domain.factories.OwnerNotificationParserFactory;
import nl.tudelft.sem.template.example.domain.factories.ParserFactory;
import nl.tudelft.sem.template.example.domain.factories.Parser;
import nl.tudelft.sem.template.example.domain.factories.ParticipantNotificationParserFactory;
import nl.tudelft.sem.template.example.domain.transferClasses.TransferMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("notifications")
public class NotificationController {
    private final transient NotificationService notificationService;
    private final transient AuthManager authManager;

    private final transient ParserFactory ownerNotificationParserFactory;

    private final transient ParserFactory participantNotificationParserFactory;

    @Autowired
    public NotificationController(NotificationService notificationService, AuthManager authManager){
        this.notificationService = notificationService;
        this.authManager = authManager;
        this.ownerNotificationParserFactory = new OwnerNotificationParserFactory();
        this.participantNotificationParserFactory = new ParticipantNotificationParserFactory();
    }

    @GetMapping("/findAll")
    public List<Notification> findAllNotifications(){
        return notificationService.getAllNotifications();
    }


    @PostMapping("/createParticipantNotification")
    public ResponseEntity<List<Notification>> createParticipantNotification(@RequestBody List<TransferMatch> requests){
        List<Notification> result = new ArrayList<>();
        for (TransferMatch request : requests){
            Notification temp = participantNotificationParserFactory.createParser().parseOtherWay(request);
            notificationService.addNotification(temp);
            result.add(temp);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/createOwnerNotification")
    public ResponseEntity<Notification> createOwnerNotification (@RequestBody TransferMatch request){
        Notification temp = ownerNotificationParserFactory.createParser().parseOtherWay(request);
        notificationService.addNotification(temp);
        return ResponseEntity.ok(temp);
    }

    @GetMapping("/getOwnerNotifications")
    public ResponseEntity<List<TransferMatch>> getOwnerNotifications(){
        List<Notification> notifications = notificationService.getUserNotifications(new NetId(authManager.getNetId()));
        List<TransferMatch> result = new ArrayList<>();
        for (Notification n : notifications){
            if (n.isOwnerNotification())
                result.add(ownerNotificationParserFactory.createParser().parse(n));
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getParticipantNotifications")
    public ResponseEntity<List<TransferMatch>> getParticipantNotifications(){
        List<Notification> notifications = notificationService.getUserNotifications(new NetId(authManager.getNetId()));
        List<TransferMatch> result = new ArrayList<>();
        for (Notification n : notifications){
            if (!n.isOwnerNotification())
                result.add(participantNotificationParserFactory.createParser().parse(n));
        }
        return ResponseEntity.ok(result);
    }
}
