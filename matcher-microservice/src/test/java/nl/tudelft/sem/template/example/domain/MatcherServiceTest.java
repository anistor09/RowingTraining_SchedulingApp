package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.domain.chainOfResponsability.*;

import nl.tudelft.sem.template.example.domain.utils.ServerUtils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class MatcherServiceTest {


    @Test
    void setValidators() {
        MatcherRepository matcherRepository = mock(MatcherRepository.class);
        ServerUtils serverUtils = mock(ServerUtils.class);
        MatcherComputingService mc = new MatcherComputingService(matcherRepository,serverUtils);

        Validator first = mc.setValidators();
        Validator second = first.getNext();
        Validator third = second.getNext();
        Validator forth = third.getNext();

        assertTrue(first instanceof TimeSlotValidator);
        assertTrue(second instanceof PositionValidator);
        assertTrue(third instanceof CompetitionValidator);
        assertTrue(forth instanceof CertificateValidator);
    }

    @Test
    void getTrainings() {
    }

    @Test
    void getCompetitions() {
    }

    @Test
    void saveMatch() {
    }

    @Test
    void getAllMatches() {
    }

    @Test
    void removeMatches() {
    }

    @Test
    void findMatch() {
    }

    @Test
    void deleteMatch() {
    }
}