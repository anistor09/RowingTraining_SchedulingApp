package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.domain.transferObject.RequestMatch;
import nl.tudelft.sem.template.example.domain.transferObject.TransferMatch;
import nl.tudelft.sem.template.example.domain.utils.ServerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MatcherServiceTest {
    transient MatcherEditService matcherEditService;
    MatcherRepository matcherRepository;

    @BeforeEach
    void setUp(){
        matcherRepository = Mockito.mock(MatcherRepository.class);
        matcherEditService = new MatcherEditService(matcherRepository, new ServerUtils());
    }

//    @Test
//    void computeMatch() {
//        Participant participant = new Participant(new NetId("netId"),
//                new PositionManager("cox"),
//                "M",
//                new Certificate("C4"),
//                "org",true);
//        RequestMatch requestMatch = new RequestMatch(participant, List.of("21-12-2012 17:33;29-12-2022 15:22"));
//
//    }

    @Test
    void getActivities() {
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
        TransferMatch tm = new TransferMatch(1L, "cox", "20-12-2022 09:00;20-12-2022 11:00",
                "participant", "owner");
        Match match = new Match("participant", 1L, "coach");
        Mockito.when(matcherRepository.findAll()).thenReturn(List.of(match));
        matcherEditService.removeMatches(List.of(tm));
        verify(matcherRepository, times(1)).delete(match);
    }
    @Test
    void findMatch() {
        TransferMatch tm = new TransferMatch(1L, "cox", "20-12-2022 09:00;20-12-2022 11:00",
                "participant", "owner");
        Match match = new Match("participant", 1L, "coach");
        List<Match> matches = matcherEditService.findMatch(tm, List.of(match));
        assertThat(matches).containsExactly(match);
    }

    @Test
    void deleteMatch() {
    }
}