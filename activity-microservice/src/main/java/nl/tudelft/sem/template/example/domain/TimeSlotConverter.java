package nl.tudelft.sem.template.example.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TimeSlotConverter implements AttributeConverter<TimeSlot, String> {

    @Override
    public String convertToDatabaseColumn(TimeSlot timeSlot) {
        return timeSlot.toString();
    }

    @Override
    public TimeSlot convertToEntityAttribute(String dbData) {
        return TimeSlot.getTimeSlot(dbData);
    }
}
