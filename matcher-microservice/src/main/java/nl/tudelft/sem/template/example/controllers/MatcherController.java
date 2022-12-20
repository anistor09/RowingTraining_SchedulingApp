package nl.tudelft.sem.template.example.controllers;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.Match;
import nl.tudelft.sem.template.example.domain.MatcherService;
import nl.tudelft.sem.template.example.domain.models.AcceptedUsersModel;
import nl.tudelft.sem.template.example.domain.transferObject.OwnerNotification;
import nl.tudelft.sem.template.example.domain.transferObject.RequestMatch;
import nl.tudelft.sem.template.example.domain.transferObject.TransferMatch;
import nl.tudelft.sem.template.example.utils.ServerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Hello World example controller.
 * <p>
 * This controller shows how you can extract information from the JWT token.
 * </p>
 */
@RestController
public class MatcherController {

    private final transient AuthManager authManager;
    private final transient MatcherService matcherService;

    private final transient ServerUtils serverUtils;

    /**
     * Instantiates a new controller.
     *
     * @param authManager Spring Security component used to authenticate and authorize the user
     */
    @Autowired
    public MatcherController(AuthManager authManager, MatcherService matcherService, ServerUtils serverUtils) {
        this.authManager = authManager;
        this.matcherService = matcherService;
        this.serverUtils = serverUtils;
    }

    /**
     * Gets example by id.
     *
     * @return the example found in the database with the given id
     */
    @PostMapping("/requestMatch")
    public List<TransferMatch> requestMatch(@RequestBody RequestMatch rm) {
        System.out.println(rm.getTimeSlots());
        List<TransferMatch> lst = matcherService.computeMatch(rm) ;
        return lst ;

    }
    @PostMapping("/acceptedMatch")
    public void acceptedMatch(@RequestBody TransferMatch tm){
        Match m = new Match(tm.getNetId(),tm.getActivityName(),tm.getPosition());
        matcherService.saveMatch(m);

    }
    @GetMapping("/getAllPendingMatches")
    public List<Match> acceptedMatch(){
        return matcherService.getAllMatches();
    }


//    @GetMapping("/sendPendingUsers")
//    public List<OwnerNotification> sendPendingUsers(){
//        List<OwnerNotification> ownerNotificationList;
//        ownerNotificationList= matcherService.getAllOwnerNotifications();
//        return ownerNotificationList;
//    }

    @PostMapping("/sendPendingUser")
    public OwnerNotification sendPendingUser(@RequestBody TransferMatch tm){
        //OwnerNotification on= matcherService.getOwnerNotification(tm);
        return serverUtils.sendPendingUser(tm);
    }

    @PostMapping("/sendAcceptedUsers")
    public void sendAcceptedUsers(@RequestBody AcceptedUsersModel request){
        List<TransferMatch> acceptedMatches= request.getTransferMatches();
        matcherService.removeMatches(acceptedMatches);
    }
}
