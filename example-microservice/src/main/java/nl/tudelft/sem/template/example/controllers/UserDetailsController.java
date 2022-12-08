package nl.tudelft.sem.template.example.controllers;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.participant.ParticipantService;
import nl.tudelft.sem.template.example.domain.participant.NetId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("userDetails")
public class UserDetailsController {
    private final transient AuthManager authManager;
    private final transient ParticipantService participantService;
    /**
     * Instantiates a new controller.
     *
     * @param authManager Spring Security component used to authenticate and authorize the user
     */
    @Autowired
    public UserDetailsController(AuthManager authManager, ParticipantService participantService) {
        this.authManager = authManager;
        this.participantService= participantService;
    }
    @GetMapping("/positions")
    public List<String> getPositions() {

        try {
            List<String> positions;
            NetId participantName = new NetId(authManager.getNetId());
             positions = participantService.getParticipantPositions(participantName);
            return positions;
        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


    }

    @GetMapping("/certificate")
    public String getCertificate() {

        try {

            NetId participantName = new NetId(authManager.getNetId());
            String certificate = participantService.getParticipantCertificate(participantName);
            return certificate;
        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


    }
    @GetMapping("/gender")
    public String getGender() {

        try {

            NetId participantName = new NetId(authManager.getNetId());
            String gender = participantService.getParticipantGender(participantName);
            return gender;
        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


    }
    @GetMapping("/level")
    public String getLevel() {
        try {
            NetId participantName = new NetId(authManager.getNetId());
            String level = participantService.getParticipantLevel(participantName);
            return level;
        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
    @GetMapping("/organization")
    public String getOrganization() {
        try {
            NetId participantName = new NetId(authManager.getNetId());
            String organization = participantService.getParticipantOrganization(participantName);
            return organization;
        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
