package nl.tudelft.sem.template.example.domain.participant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UsernameAttributeConverter implements AttributeConverter<Username, String> {

    @Override
    public String convertToDatabaseColumn(Username username){return username.toString();}

    @Override
    public Username convertToEntityAttribute(String dbData){return new Username(dbData);}

}
