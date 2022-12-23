package nl.tudelft.sem.template.example.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionTest {
    @Test
    void testConstructor(){
        Competition competition = new Competition(new NetId("owner"),
                new TimeSlot("21-12-2012 17:33;29-12-2022 15:22"),
                "C4", List.of("cox"),"org","M",true);
        assertNotNull(competition);

    }
    @Test
    void getCompetitive(){
        Competition competition = new Competition(new NetId("owner"),
                new TimeSlot("21-12-2012 17:33;29-12-2022 15:22"),
                "C4", List.of("cox"),"org","M",true);
        assertTrue(competition.getCompetitive());

    }


}