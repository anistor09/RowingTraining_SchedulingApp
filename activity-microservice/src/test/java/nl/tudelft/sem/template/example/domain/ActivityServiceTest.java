package nl.tudelft.sem.template.example.domain;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ActivityServiceTest {

    @Test
    public void createValidTraining() {
        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 15:00; 10-10-2022 16:00"), "boat", List.of("cox", "other position"));
        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
        ActivityRepository activityRepo = mock(ActivityRepository.class);
        when(activityRepo.save(any(Training.class))).thenReturn(null);
        ActivityService service = new ActivityService(activityRepo);

        service.createTraining(new NetId("paula"), request);
        verify(activityRepo).save(captor.capture());
        Training training = captor.getValue();
        assertEquals("paula", training.getOwner().getNetIdValue());
        assertEquals("10-10-2022 15:00;10-10-2022 16:00", training.getTimeSlot().toString());
        assertEquals("boat", training.getBoat());
        assertEquals(List.of("cox", "other position"), training.getPositions());
    }

    @Test
    public void createValidCompetition() {
        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "yacht", List.of("captain"), "organization", "female", true);
        ArgumentCaptor<Competition> captor = ArgumentCaptor.forClass(Competition.class);
        ActivityRepository activityRepo = mock(ActivityRepository.class);
        when(activityRepo.save(any(Competition.class))).thenReturn(null);
        ActivityService service = new ActivityService(activityRepo);

        service.createCompetition(new NetId("zosia"), request);
        verify(activityRepo).save(captor.capture());
        Competition competition = captor.getValue();
        assertEquals("zosia", competition.getOwner().getNetIdValue());
        assertEquals("10-10-2022 14:30;10-10-2022 16:00", competition.getTimeSlot().toString());
        assertEquals("yacht", competition.getBoat());
        assertEquals(List.of("captain"), competition.getPositions());
        assertEquals("organization", competition.getOrganization());
        assertEquals("female", competition.getGender());
        assertTrue(competition.getCompetitive());
    }

    @Test
    public void editCompetition() throws UnauthorizedException {
        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "yacht", List.of("captain"), "organization", "female", true);
        ArgumentCaptor<Competition> captor = ArgumentCaptor.forClass(Competition.class);
        ActivityRepository activityRepo = mock(ActivityRepository.class);
        when(activityRepo.save(any(Competition.class))).thenReturn(null);
        ActivityService service = new ActivityService(activityRepo);

        Username owner = new Username("zosia");
        service.createCompetition(new NetId("zosia"), request);
        verify(activityRepo).save(captor.capture());
        Competition competition = captor.getValue();
        ActivityRequestModel edit = new ActivityRequestModel(new TimeSlot("10-10-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("captain", "cox"), "organization", "male", false);
        service.editActivity(owner, competition.getId(), edit);
        verify(activityRepo).save(captor.capture());
        Competition edited = captor.getValue();
        assertEquals("zosia", edited.getOwner().getNetIdValue());
        assertEquals("10-10-2022 14:30;10-10-2022 15:00", edited.getTimeSlot().toString());
        assertEquals("yacht", edited.getBoat());
        assertEquals(List.of("captain", "cox"), edited.getPositions());
        assertEquals("organization", edited.getOrganization());
        assertEquals("male", edited.getGender());
        assertFalse(edited.getCompetitive());
    }

    @Test
    public void editTraining() throws UnauthorizedException {
        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 15:00; 10-10-2022 16:00"), "boat", List.of("cox", "other position"));
        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
        ActivityRepository activityRepo = mock(ActivityRepository.class);
        when(activityRepo.save(any(Training.class))).thenReturn(null);
        ActivityService service = new ActivityService(activityRepo);

        Username owner = new Username("paula");
        service.createTraining(new NetId("paula"), request);
        verify(activityRepo).save(captor.capture());
        Training training = captor.getValue();
        ActivityRequestModel edit = new ActivityRequestModel(new TimeSlot("10-10-2022 15:00; 10-10-2022 17:00"), "titanic", List.of("cox"));
        service.editActivity(owner, training.getId(), edit);
        verify(activityRepo).save(captor.capture());
        Training edited = captor.getValue();
        assertEquals("paula", edited.getOwner().getNetIdValue());
        assertEquals("10-10-2022 15:00;10-10-2022 17:00", edited.getTimeSlot().toString());
        assertEquals("titanic", edited.getBoat());
        assertEquals(List.of("cox"), edited.getPositions());
    }

    @Test
    public void editTrainingUnauthorized() throws UnauthorizedException {
        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 15:00; 10-10-2022 16:00"), "boat", List.of("cox", "other position"));
        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
        ActivityRepository activityRepo = mock(ActivityRepository.class);
        when(activityRepo.save(any(Training.class))).thenReturn(null);
        ActivityService service = new ActivityService(activityRepo);

        service.createTraining(new NetId("paula"), request);
        verify(activityRepo).save(captor.capture());
        Training training = captor.getValue();
        ActivityRequestModel edit = new ActivityRequestModel(new TimeSlot("10-10-2022 15:00; 10-10-2022 17:00"), "titanic", List.of("cox"));
        assertThrows(UnauthorizedException.class, () -> service.editActivity(new Username("zosia"), training.getId(), edit));
    }

    @Test
    public void editCompetitionUnauthorized() {
        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "yacht", List.of("captain"), "organization", "female", true);
        ArgumentCaptor<Competition> captor = ArgumentCaptor.forClass(Competition.class);
        ActivityRepository activityRepo = mock(ActivityRepository.class);
        when(activityRepo.save(any(Competition.class))).thenReturn(null);
        ActivityService service = new ActivityService(activityRepo);

        service.createCompetition(new NetId("paula"), request);
        verify(activityRepo).save(captor.capture());
        Competition competition = captor.getValue();
        ActivityRequestModel edit = new ActivityRequestModel(new TimeSlot("10-10-2022 15:00; 10-10-2022 17:00"), "titaniccc", List.of("cox"), "organ", "female", false);
        assertThrows(UnauthorizedException.class, () -> service.editActivity(new Username("zosia"), competition.getId(), edit));
    }
}
