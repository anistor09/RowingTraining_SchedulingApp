package nl.tudelft.sem.template.example.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class TimeSlot {
    private transient Date begin;
    private transient Date end;
    transient SimpleDateFormat converter;

    public TimeSlot(String timeSlot){
        String begin = timeSlot.split(";")[0];
        String end =  timeSlot.split(";")[1];

        converter = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US);
        try {
            this.begin= converter.parse(begin);
            this.end= converter.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static List<TimeSlot> getTimeSlots(List<String> timeSlots) {
        List<TimeSlot> ts = new ArrayList<>();
        for(String s : timeSlots){
            ts.add(getTimeSlot(s));
        }
        return  ts;
    }
    public static TimeSlot getTimeSlot(String timeSlot){
        return new TimeSlot(timeSlot);
    }

    public String timeSlotToString() {
        return converter.format(begin) +";"+ converter.format(end);
    }
}