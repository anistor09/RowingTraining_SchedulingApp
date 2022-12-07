package nl.tudelft.sem.template.example.controllers;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.participant.ParticipantService;
import nl.tudelft.sem.template.example.domain.participant.Username;
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
        List<String> positions;
        try {
            Username participantName = new Username(authManager.getNetId());
             positions = participantService.getParticipantPositions(participantName);

        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return positions;

    }

    @GetMapping("/certificate")
    public String getCertificate() {
        String certificate="";
        try {
            Username participantName = new Username(authManager.getNetId());
            certificate = participantService.getParticipantCertificate(participantName);

        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return certificate;

    }
    @GetMapping("/gender")
    public String getGender() {
        String gender="";
        try {
            Username participantName = new Username(authManager.getNetId());
            gender = participantService.getParticipantGender(participantName);

        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return gender;

    }
    @GetMapping("/level")
    public String getLevel() {
        String level="";
        try {
            Username participantName = new Username(authManager.getNetId());
            level = participantService.getParticipantLevel(participantName);

        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return level;

    }
    @GetMapping("/organization")
    public String getOrganization() {
        String organization="";
        try {
            Username participantName = new Username(authManager.getNetId());
            organization = participantService.getParticipantOrganization(participantName);

        }   catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return organization;
    }
}
