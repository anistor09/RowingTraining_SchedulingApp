package nl.tudelft.sem.template.example.domain;

import lombok.Data;

import java.util.List;

@Data
public class ActivityRequestModel {
    private String timeSlot;
    private String boat;
    private String organization;
    private String gender;
    private boolean competitive;
    private List<String> positions;

    public TimeSlot getTimeSlot() {
        return timeSlot == null ? null : TimeSlot.getTimeSlot(timeSlot);
    }

    public String timeSlotString() { return this.timeSlot; }

    public boolean getCompetitive() {
        return competitive;
    }
}
