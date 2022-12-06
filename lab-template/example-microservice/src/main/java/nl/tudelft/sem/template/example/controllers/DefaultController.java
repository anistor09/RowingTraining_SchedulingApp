package nl.tudelft.sem.template.example.controllers;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.models.ParticipationRequestModel;
import nl.tudelft.sem.template.example.domain.participant.Certificate;
import nl.tudelft.sem.template.example.domain.participant.ParticipantService;
import nl.tudelft.sem.template.example.domain.participant.PositionManager;
import nl.tudelft.sem.template.example.domain.participant.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    /**
     * Instantiates a new controller.
     *
     * @param authManager Spring Security component used to authenticate and authorize the user
     */
    @Autowired
    public DefaultController(AuthManager authManager, ParticipantService participantService) {
        this.authManager = authManager;
        this.participantService= participantService;
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
    public ResponseEntity addDetails(@RequestBody ParticipationRequestModel request){
        try{
            Username username= new Username(authManager.getNetId());
            PositionManager positionManager= new PositionManager(request.getPositions());
            Certificate certificate= new Certificate(request.getCertificate());
            String gender= request.getGender();
            //here we need to throw an exception if the entered certificate is wrong
            String organization= request.getOrganization();
            String level= request.getLevel();
            participantService.addParticipant(username,positionManager,gender,certificate,organization,level);
        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok("Fine");
    }
}
