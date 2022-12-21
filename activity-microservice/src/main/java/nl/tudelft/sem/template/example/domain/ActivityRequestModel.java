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

    public ActivityRequestModel(TimeSlot timeSlot, String boat, List<String> positions, String organization, String gender, boolean competitive) {
        this.timeSlot = timeSlot.toString();
        this.boat = boat;
        this.positions = positions;
        this.organization = organization;
        this.gender = gender;
        this.competitive = competitive;
    }

    public ActivityRequestModel(TimeSlot timeSlot, String boat, List<String> positions) {
        this.timeSlot = timeSlot.toString();
        this.boat = boat;
        this.positions = positions;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot == null ? null : TimeSlot.getTimeSlot(timeSlot);
    }

    public String timeSlotString() { return this.timeSlot; }
    /**
     *
     * @return competitive
     */
    public boolean getCompetitive() {
        return competitive;
    }
}
