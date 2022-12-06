package nl.tudelft.sem.template.example.domain.participant;

import nl.tudelft.sem.template.example.domain.participant.Certificate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CertificateAttributeConverter implements AttributeConverter<Certificate, String> {

    @Override
    public String convertToDatabaseColumn(Certificate certificate){return certificate.toString();}

    @Override
    public Certificate convertToEntityAttribute(String dbData){return new Certificate(dbData);}

}