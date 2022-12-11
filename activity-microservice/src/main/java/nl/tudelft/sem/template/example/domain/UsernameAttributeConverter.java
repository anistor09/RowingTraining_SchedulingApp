package nl.tudelft.sem.template.example.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UsernameAttributeConverter implements AttributeConverter<Username, String> {

    @Override
    public String convertToDatabaseColumn(Username username){return username.getUsernameValue();}

    @Override
    public Username convertToEntityAttribute(String dbData){return new Username(dbData);}

}
