package nl.tudelft.sem.template.example.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * model representing a request model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationRequestModel {
    private String positions;
    private String certificate;
    private String gender;
    private String organization;
    private Boolean level;
}
