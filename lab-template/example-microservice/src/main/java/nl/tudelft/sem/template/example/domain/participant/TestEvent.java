package nl.tudelft.sem.template.example.domain.participant;

import java.util.List;

/**
 * A DDD domain event that indicated a user was created.
 */
public class TestEvent {
    private final NetId netId;
    private final List<String> timeSlots;
    public TestEvent(NetId netId, List<String> timeSlots) {
        this.netId = netId;
        this.timeSlots = timeSlots;
    }

    public List<String> getTimeSlots() {
        return timeSlots;
    }

    public NetId getUsername() {
        return this.netId;
    }
}
