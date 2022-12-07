package nl.tudelft.sem.template.example.domain.participant;

import java.util.List;

public class ParticipantRequestedMatchEvent {
    private final Username username;
    private final List<String> timeSlots;
    public ParticipantRequestedMatchEvent(Username username, List<String> timeSlots) {
        this.username = username;
        this.timeSlots = timeSlots;
    }

    public List<String> getTimeSlots() {
        return timeSlots;
    }

    public Username getUsername() {
        return this.username;
    }
}
