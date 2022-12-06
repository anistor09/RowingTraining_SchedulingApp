package nl.tudelft.sem.template.example.domain;

import org.springframework.stereotype.Service;

/**
 * Service for adding new Participants and their details
 */
@Service
public class ParticipantService {
    private final transient  ParticipantRepository participantRepository;

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
    public Participant addParticipant(nl.tudelft.sem.template.example.domain.participant.Username username, PositionManager positionManager, String gender, Certificate certificate,
                                      String organization, String level){
       // if(checkUsernameIsUnique(username)){
            Participant participant= new Participant(username,positionManager,gender,certificate,organization,level);

            participantRepository.save(participant);
            return participant;
        //}
        //throw new Exception("some exception we need to create");
    }

    //public boolean checkUsernameIsUnique(Username username){
      //  return !participantRepository.existsByUsername(username);
    //}
}
