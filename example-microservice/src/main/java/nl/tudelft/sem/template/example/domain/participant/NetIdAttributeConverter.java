package nl.tudelft.sem.template.example.domain.participant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter class for the nedId
 */
@Converter
public class NetIdAttributeConverter implements AttributeConverter<NetId, String> {

    @Override
    public String convertToDatabaseColumn(NetId netId){return netId.toString();}

    @Override
    public NetId convertToEntityAttribute(String dbData){return new NetId(dbData);}

}
