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
                                      String organization, Boolean level) {
            Participant participant= new Participant(netId,positionManager,gender,certificate,organization,level);

            participantRepository.save(participant);
            return participant;
    }

    /**
     * Get the participant by netId.
     * @param netId
     * @return participant
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
     * Get the positions of the participant by netId.
     * @param netId
     * @return positions
     */
    public List<String> getParticipantPositions(NetId netId) {

        if(getParticipant(netId).getPositionManager().getPositions()!=null){
            List<String> positions= getParticipant(netId).getPositionManager().getPositions();
            return positions;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    /**
     * Get the certificate of the participant by netId.
     * @param netId
     * @return certificate
     */
    public String getParticipantCertificate(NetId netId) {
        if(getParticipant(netId).getCertificate()!=null){
            String certificate= getParticipant(netId).getCertificate().toString();
            return certificate;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets the organization of the participant by netId.
     * @param netId
     * @return organization
     */
    public String getParticipantOrganization(NetId netId) {
        if(getParticipant(netId).getOrganization()!=null){
            String organization= getParticipant(netId).getOrganization();
            return organization;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets the gender of the participant by netId.
     * @param netId
     * @return gender
     */
    public String getParticipantGender(NetId netId) {
        if(getParticipant(netId).getGender()!=null){
            String gender= getParticipant(netId).getGender();
            return gender;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets the level of the participant by netId.
     * @param netId
     * @return level
     */
    public Boolean getParticipantLevel(NetId netId) {
        if(getParticipant(netId).getLevel()!=null){
            Boolean level= getParticipant(netId).getLevel();
            return level;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets the RequestMatch of the participant.
     * @param netId
     * @param timeSlots
     * @return RequestMatch
     */
    public RequestMatch getRequestMatch(NetId netId, List<String> timeSlots) {
        Participant p = getParticipant(netId);
        return new RequestMatch(p,timeSlots);

    }

    /**
     * Gets the TransferMatch of the participant.
     * @param request
     * @return TransferMatch
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
}
