package nl.tudelft.sem.template.example.domain.participant;

import nl.tudelft.sem.template.example.domain.models.RequetsTransferMatchModel;
import nl.tudelft.sem.template.example.domain.transferClasses.RequestMatch;
import nl.tudelft.sem.template.example.domain.transferClasses.TransferMatch;
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
                                      String organization, Boolean level){
       // if(checkUsernameIsUnique(username)){
            Participant participant= new Participant(netId,positionManager,gender,certificate,organization,level);

            participantRepository.save(participant);
            return participant;
        //}
        //throw new Exception("some exception we need to create");
    }

    /**
     * Get a participant from the repository based on his username
     * @param netId The username of the participant
     * @return the participant with that username
     */
    public Participant getParticipant(NetId netId){
        if(participantRepository.findByNetId(netId)!=null) {
            Optional<Participant> participant = participantRepository.findByNetId(netId);
            Participant currentParticipant;
            currentParticipant = participant.get();
            return currentParticipant;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    }

    /**
     * Get positions of a participant based on his username
     * @param netId The username
     * @return positions that a participant with the entered netId is capable to fill
     */
    public List<String> getParticipantPositions(NetId netId) {

        if(getParticipant(netId).getPositionManager().getPositions()!=null){
            List<String> positions= getParticipant(netId).getPositionManager().getPositions();
            return positions;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    /**
     * Get certificate of a participant based on his username
     * @param netId The username
     * @return certificate of that participant
     */
    public String getParticipantCertificate(NetId netId) {
        if(getParticipant(netId).getCertificate()!=null){
            String certificate= getParticipant(netId).getCertificate().toString();
            return certificate;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    /**
     * Get organization of a participant based on his username
     * @param netId The username
     * @return organization of that participant
     */
    public String getParticipantOrganization(NetId netId) {
        if(getParticipant(netId).getOrganization()!=null){
            String organization= getParticipant(netId).getOrganization();
            return organization;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    /**
     * Get gender of a participant based on his username
     * @param netId The username
     * @return gender of that participant
     */
    public String getParticipantGender(NetId netId) {
        if(getParticipant(netId).getGender()!=null){
            String gender= getParticipant(netId).getGender();
            return gender;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    /**
     * Get level of competitiveness of a participant based on his username
     * @param netId The username
     * @return level of competitiveness of that participant
     */
    public Boolean getParticipantLevel(NetId netId) {
        if(getParticipant(netId).getLevel()!=null){
            Boolean level= getParticipant(netId).getLevel();
            return level;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    /**
     * Create a new RequestMatch based on the netId and the timeslots of the user
     * @param netId username of the participant
     * @param timeSlots the time slots when he is able to participate in activities
     * @return a request match
     */
    public RequestMatch getRequestMatch(NetId netId, List<String> timeSlots) {
        Participant p = getParticipant(netId);
        return new RequestMatch(p,timeSlots);

    }

    /**
     * Based on the TransferMatchModel it creates a new TransferMatch
     * @param request a model that contains the id of the activity, positions, timeslot, netId and the name of the owner
     * @return a new TranferMatch
     */
    public TransferMatch getTransferMatch(RequetsTransferMatchModel request){
        Long activityId = request.getActivityId();
        String positions = request.getPosition();
        String timeSlot = request.getTimeSlot();
        String netId = request.getNetId();
        String owner = request.getOwner();
        TransferMatch transferMatch= new TransferMatch(activityId,positions,timeSlot,netId,owner);
        return transferMatch;

    }
//    public void requestMatch(NetId netId, List<String> timeSlots){
//        Participant p = getParticipant(netId);
//        p.requestMatch(timeSlots);
//    }


    //public boolean checkUsernameIsUnique(Username username){
      //  return !participantRepository.existsByUsername(username);
    //}
}
