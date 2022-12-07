package nl.tudelft.sem.template.example.domain.participant;

import java.util.List;

/**
 * A DDD domain event that indicated a user was created.
 */
public class TestEvent {
    private final Username username;
    private final List<String> timeSlots;
    public TestEvent(Username username, List<String> timeSlots) {
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
