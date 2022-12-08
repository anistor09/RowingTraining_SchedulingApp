package nl.tudelft.sem.template.example.controllers;

import lombok.Data;

/**
 * model representing a request model
 */
@Data
public class ParticipationRequestModel {
    private String positions;
    private String certificate;
    private String gender;
    private String organization;
    private String level;
}
