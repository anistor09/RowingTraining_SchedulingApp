package nl.tudelft.sem.template.example.domain;

import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.Time;
import java.util.List;

@Data
public class ActivityRequestModel {
    private String date;
    private String time;
    private String boat;
    private String organization;
    private String gender;
    private boolean competitive;
    private List<String> positions;

    public LocalDate getDate() {
        try {
            LocalDate date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return date;
        } catch (Exception e) {
            return LocalDate.now();
        }
    }

    public LocalTime getTime() {
        try {
            LocalTime time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            return time;
        } catch (Exception e) {
            return LocalTime.now();
        }
    }
    public boolean getCompetitive() {
        return competitive;
    }
}
