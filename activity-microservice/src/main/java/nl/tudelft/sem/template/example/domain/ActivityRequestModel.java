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

    /**
     * Converts the date to a Date object.
     * @return the date as a Date object
     */
    public LocalDate getDate() {
        try {
            LocalDate date = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return date;
        } catch (Exception e) {
            return LocalDate.now();
        }
    }

    /**
     * Converts the time to a Time object.
     * @return the time as a Time object
     */
    public LocalTime getTime() {
        try {
            LocalTime time = LocalTime.parse(this.time, DateTimeFormatter.ofPattern("HH:mm"));
            return time;
        } catch (Exception e) {
            return LocalTime.now();
        }
    }

    /**
     *
     * @return competitive
     */
    public boolean getCompetitive() {
        return competitive;
    }
}
