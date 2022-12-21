package nl.tudelft.sem.template.example.domain.models;

import lombok.Data;

@Data
public class RequetsTransferMatchModel {
    Long activityId;
    String position;
    String timeSlot;
    String netId;
    String owner;
}
