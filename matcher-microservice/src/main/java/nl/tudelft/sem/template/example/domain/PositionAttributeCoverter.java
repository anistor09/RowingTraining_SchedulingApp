package nl.tudelft.sem.template.example.domain.participant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PositionAttributeCoverter implements AttributeConverter<PositionManager, String> {

    @Override
    public String convertToDatabaseColumn(PositionManager pm){return pm.toString();}

    @Override
    public PositionManager convertToEntityAttribute(String dbData){return new PositionManager(dbData);}

}