package nl.tudelft.sem.template.example.controllers;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.models.ParticipationRequestModel;
import nl.tudelft.sem.template.example.domain.models.RequestMatchModel;
import nl.tudelft.sem.template.example.domain.models.RequetsTransferMatchModel;
import nl.tudelft.sem.template.example.domain.participant.Certificate;
import nl.tudelft.sem.template.example.domain.participant.ParticipantService;
import nl.tudelft.sem.template.example.domain.participant.PositionManager;
import nl.tudelft.sem.template.example.domain.participant.NetId;
import nl.tudelft.sem.template.example.domain.transferClasses.RequestMatch;
import nl.tudelft.sem.template.example.domain.transferClasses.TransferMatch;
import nl.tudelft.sem.template.example.domain.utils.ServerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Hello World example controller.
 * <p>
 * This controller shows how you can extract information from the JWT token.
 * </p>
 */
@RestController
public class DefaultController {

    private final transient AuthManager authManager;
    private final transient ParticipantService participantService;
    private final transient ServerUtils serverUtils;

    /**
     * Instantiates a new controller.
     *
     * @param authManager Spring Security component used to authenticate and authorize the user
     */
    @Autowired
    public DefaultController(AuthManager authManager, ParticipantService participantService, ServerUtils serverUtils) {
        this.authManager = authManager;
        this.participantService= participantService;
        this.serverUtils = serverUtils;
    }

    /**
     * Gets example by id.
     *
     * @return the example found in the database with the given id
     */
    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello " + authManager.getNetId());

    }

    /**
     * Endpoint for adding a new participant
     *
     * @param request The registration model
     * @return 200 OK if the registration is successful
     * @throws Exception
     */
    @PostMapping("/details")
    public ResponseEntity addDetails(@ModelAttribute("req") @RequestBody ParticipationRequestModel request){
        try{
            NetId netId= new NetId(authManager.getNetId());
            PositionManager positionManager= new PositionManager(request.getPositions());
            Certificate certificate= new Certificate(request.getCertificate());
            String gender= request.getGender();
            //here we need to throw an exception if the entered certificate is wrong
            String organization= request.getOrganization();
            Boolean level= request.getLevel();
            participantService.addParticipant(netId,positionManager,gender,certificate,organization,level);
        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok("Fine");
    }

    /**
     * Endpoint for requesting a match
     *
     * @param request The MatchModel
     * @return a list of possible matches for the participant
     */
    @GetMapping("/requestMatch")
    public List<TransferMatch> requestMatch(@ModelAttribute @RequestBody RequestMatchModel request) {
        List<String> timeSlots = request.getTimeslots();
        NetId netId= new NetId(authManager.getNetId());
        RequestMatch rm = participantService.getRequestMatch(netId,timeSlots);

        return serverUtils.sendRequestMatch(rm);

    }

    /**
     * Endpoint for sending the accepted match
     *
     * @param request The TransferMatch model
     * @return 200 ok if it was correctly sent
     */
    @PostMapping("/acceptedMatch")
    public ResponseEntity<String> requestTransferMatch(@ModelAttribute @RequestBody RequetsTransferMatchModel request){
        TransferMatch tm = participantService.getTransferMatch(request);
        return serverUtils.sendAcceptedMatch(tm);
    }
}
