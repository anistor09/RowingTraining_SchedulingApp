package nl.tudelft.sem.template.example.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Converter
public class PositionListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ",";

    /**
     * Converts a List of Strings to a String.
     * @param stringList
     * @return
     */
    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
    }

    /**
     * Converts a String to a List of Strings.
     * @param string
     * @return
     */
    @Override
    public List<String> convertToEntityAttribute(String string) {
        return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : emptyList();
    }
}
