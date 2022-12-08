package nl.tudelft.sem.template.example.domain.participant;

import java.util.List;

public class ParticipantRequestedMatchEvent {
    private final   NetId netId;
    private final List<String> timeSlots;
    public ParticipantRequestedMatchEvent(NetId netId, List<String> timeSlots) {
        this.netId = netId;
        this.timeSlots = timeSlots;
    }

    public List<String> getTimeSlots() {
        return timeSlots;
    }

    public NetId getNetId() {
        return this.netId;
    }
}
