package nl.tudelft.sem.template.example.domain;

import javax.persistence.AttributeConverter;

public class NetIdConverter implements AttributeConverter<NetId, String> {
    @Override
    public String convertToDatabaseColumn(NetId netId){return netId.getId();}

    @Override
    public NetId convertToEntityAttribute(String dbData){return new NetId(dbData);}
}
