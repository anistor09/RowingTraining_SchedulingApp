package nl.tudelft.sem.template.example.domain.participant;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Service for adding new Participants and their details
 */
@Service
public class ParticipantService {
    private final transient ParticipantRepository participantRepository;

    /**
     * Instantiates a new ParticipantService
     *
     * @param participantRepository participant repository
     */
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    /**
     * Add a new participant
     *
     * @param username The username of the participant
     * @param positionManager The positions of the participant
     * @param gender The gender of the participant
     * @param certificate The best certificate of the participant
     * @param organization The organization of the participant
     * @param level The competitive level of the participant
     * @return a new participant
     * @throws Exception
     */
    public Participant addParticipant(Username username, PositionManager positionManager, String gender, Certificate certificate,
                                      String organization, String level){
       // if(checkUsernameIsUnique(username)){
            Participant participant= new Participant(username,positionManager,gender,certificate,organization,level);

            participantRepository.save(participant);
            return participant;
        //}
        //throw new Exception("some exception we need to create");
    }
    public Participant getParticipant(Username username){
        Optional<Participant> participant = participantRepository.findByUsername(username);
        Participant currentParticipant;
        try{
            currentParticipant = participant.get();

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return currentParticipant;

    }
    public List<String> getParticipantPositions(Username username) {

        List<String> positions;
        try{
             positions = getParticipant(username).getPositionManager().getPositions();

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return positions;
    }

    public String getParticipantCertificate(Username username) {
        String certificate;
        try{
            certificate = getParticipant(username).getCertificate().toString();

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return certificate;
    }
    public String getParticipantOrganization(Username username) {
        String organization;
        try{
            organization = getParticipant(username).getOrganization();

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return organization;
    }
    public String getParticipantGender(Username username) {
        String gender;
        try{
            gender = getParticipant(username).getGender();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return gender;
    }
    public String getParticipantLevel(Username username) {
        String level;
        try{
            level = getParticipant(username).getLevel();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return level;
    }
    public void requestMatch(Username username, List<String> timeSlots){
        Participant p = getParticipant(username);
        p.requestMatch(timeSlots);
    }


    //public boolean checkUsernameIsUnique(Username username){
      //  return !participantRepository.existsByUsername(username);
    //}
}
