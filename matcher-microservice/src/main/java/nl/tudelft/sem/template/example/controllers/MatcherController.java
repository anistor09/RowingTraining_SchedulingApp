package nl.tudelft.sem.template.example.controllers;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.MatcherService;
import nl.tudelft.sem.template.example.domain.transferObject.RequestMatch;
import nl.tudelft.sem.template.example.domain.transferObject.TransferMatch;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Instantiates a new controller.
     *
     * @param authManager Spring Security component used to authenticate and authorize the user
     */
    @Autowired
    public MatcherController(AuthManager authManager, MatcherService matcherService) {
        this.authManager = authManager;
        this.matcherService = matcherService;
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



}
