package nl.tudelft.sem.template.example.domain.participant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UsernameAttributeConverter implements AttributeConverter<nl.tudelft.sem.template.example.domain.participant.Username, String> {

    @Override
    public String convertToDatabaseColumn(nl.tudelft.sem.template.example.domain.participant.Username username){return username.toString();}

    @Override
    public nl.tudelft.sem.template.example.domain.participant.Username convertToEntityAttribute(String dbData){return new nl.tudelft.sem.template.example.domain.participant.Username(dbData);}

}
