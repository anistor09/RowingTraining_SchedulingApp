package nl.tudelft.sem.template.example.application;


import nl.tudelft.sem.template.example.domain.participant.ParticipantRequestedMatchEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This event listener is automatically called when a domain entity is saved
 * which has stored events of type: UserWasCreated.
 */
@Component
public class ParticipantRequestedMatchListener {
    /**
     * The name of the function indicated which event is listened to.
     * The format is onEVENTNAME.
     *
     * @param event The event to react to
     */
    @EventListener
    public void onParticipantRequestedMatch(ParticipantRequestedMatchEvent event) {
        // Handler code here
        List<String> timeSlots = event.getTimeSlots();

        for(String timeslot : timeSlots){
            System.out.println(timeslot);;
        }
    }
}
