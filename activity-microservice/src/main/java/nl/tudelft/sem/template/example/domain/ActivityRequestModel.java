package nl.tudelft.sem.template.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequestModel {
    private String timeSlot;
    private String boat;
    private List<String> positions;
    private String organization;
    private String gender;
    private boolean competitive;

    public TimeSlot getTimeSlot() {
        return timeSlot == null ? null : TimeSlot.getTimeSlot(timeSlot);
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String timeSlotString() { return this.timeSlot; }

    public boolean getCompetitive() {
        return competitive;
    }
}
