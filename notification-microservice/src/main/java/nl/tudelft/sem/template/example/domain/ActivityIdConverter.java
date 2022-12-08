package nl.tudelft.sem.template.example.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ActivityIdConverter implements AttributeConverter<ActivityId, String> {

    @Override
    public String convertToDatabaseColumn(ActivityId activityID){return activityID.toString();}

    @Override
    public ActivityId convertToEntityAttribute(String dbData){return new ActivityId(dbData);}
}
