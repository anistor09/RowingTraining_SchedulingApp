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
     * @param netId The username of the participant
     * @param positionManager The positions of the participant
     * @param gender The gender of the participant
     * @param certificate The best certificate of the participant
     * @param organization The organization of the participant
     * @param level The competitive level of the participant
     * @return a new participant
     * @throws Exception
     */
    public Participant addParticipant(NetId netId, PositionManager positionManager, String gender, Certificate certificate,
                                      String organization, String level){
       // if(checkUsernameIsUnique(username)){
            Participant participant= new Participant(netId,positionManager,gender,certificate,organization,level);

            participantRepository.save(participant);
            return participant;
        //}
        //throw new Exception("some exception we need to create");
    }
    public Participant getParticipant(NetId netId){
        Optional<Participant> participant = participantRepository.findByNetId(netId);
        Participant currentParticipant;
        try{
            currentParticipant = participant.get();

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return currentParticipant;

    }
    public List<String> getParticipantPositions(NetId netId) {

        List<String> positions;
        try{
             positions = getParticipant(netId).getPositionManager().getPositions();

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return positions;
    }

    public String getParticipantCertificate(NetId netId) {
        String certificate;
        try{
            certificate = getParticipant(netId).getCertificate().toString();

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return certificate;
    }
    public String getParticipantOrganization(NetId netId) {
        String organization;
        try{
            organization = getParticipant(netId).getOrganization();

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return organization;
    }
    public String getParticipantGender(NetId netId) {
        String gender;
        try{
            gender = getParticipant(netId).getGender();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return gender;
    }
    public String getParticipantLevel(NetId netId) {
        String level;
        try{
            level = getParticipant(netId).getLevel();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
        return level;
    }
//    public void requestMatch(NetId netId, List<String> timeSlots){
//        Participant p = getParticipant(netId);
//        p.requestMatch(timeSlots);
//    }


    //public boolean checkUsernameIsUnique(Username username){
      //  return !participantRepository.existsByUsername(username);
    //}
}
