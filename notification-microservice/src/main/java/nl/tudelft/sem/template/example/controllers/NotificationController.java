package nl.tudelft.sem.template.example.controllers;


import nl.tudelft.sem.template.example.domain.ActivityId;
import nl.tudelft.sem.template.example.domain.NetId;
import nl.tudelft.sem.template.example.domain.Notification;
import nl.tudelft.sem.template.example.domain.NotificationService;
import nl.tudelft.sem.template.example.domain.models.NotificationRequestModel;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.HttpHeaders;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
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

    @GetMapping("/try")
    public ResponseEntity trying(){
        String SERVER = new String("http://localhost:8082/");
        try{
            String res =  new ResteasyClientBuilder().build()
                    .target(SERVER).path("hello")
                    .request(APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials())
                    .accept(APPLICATION_JSON)
                    .get(String.class);
            return ResponseEntity.ok(res);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/tokennn")
    public ResponseEntity<String> token(){
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
    }
    @PostMapping("/addNotification")
    public ResponseEntity<Notification> addNotification(@RequestBody Notification request){
        return ResponseEntity.ok(notificationService.addNotification(request));
    }
}
