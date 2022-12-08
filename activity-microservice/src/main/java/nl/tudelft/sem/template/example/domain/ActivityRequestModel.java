package nl.tudelft.sem.template.example.domain;

import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.Time;
import java.util.List;

@Data
public class ActivityRequestModel {
    private String dateTime;
    private String boat;
    private String organization;
    private String gender;
    private boolean competitive;
    private List<String> positions;

    public LocalDateTime getDateTime(){
        try {
            LocalDateTime datetime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return datetime;
        } catch (Exception e) {
            return LocalDateTime.now();
        }
    }
}
