package nl.tudelft.sem.template.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ActivityServiceTest {

    ActivityService service;
    ActivityRepository activityRepo;
    NetId user = new NetId("paula");
    Competition competition = new Competition(user, new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain"), "organization", "female", true);
    Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));

    @BeforeEach
    public void setUp() {
        activityRepo  = mock(ActivityRepository.class);
        service = new ActivityService(activityRepo);
    }

    public void setCompetitionRepo() {
        when(activityRepo.existsById(competition.getId())).thenReturn(true);
        when(activityRepo.findById(competition.getId())).thenReturn(java.util.Optional.of(competition));
    }

    public void setTrainingRepo() {
        when(activityRepo.existsById(training.getId())).thenReturn(true);
        when(activityRepo.findById(training.getId())).thenReturn(java.util.Optional.of(training));
    }

    @Test
    public void createTraining() {
        ActivityRequestModel request = new ActivityRequestModel("10-10-2022 14:30; 10-10-2022 16:00", "yacht", List.of("captain"), null, null, false);
        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
        service.createTraining(user, request);
        verify(activityRepo).save(captor.capture());
        Training training = captor.getValue();
        Training expected = new Training(user, TimeSlot.getTimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "yacht", List.of("captain"));

        assertThat(training.getId()).isEqualTo(expected.getId());
        assertThat(training.getOwner()).isEqualTo(expected.getOwner());
        assertThat(training.getTimeSlot()).isEqualTo(expected.getTimeSlot());
        assertThat(training.getBoat()).isEqualTo(expected.getBoat());
        assertThat(training.getPositions()).isEqualTo(expected.getPositions());
    }


    @Test
    public void createCompetition() {
        ActivityRequestModel request = new ActivityRequestModel("10-10-2022 14:30; 10-10-2022 16:00", "yacht", List.of("captain"), "organization", "female", true);
        ArgumentCaptor<Competition> captor = ArgumentCaptor.forClass(Competition.class);
        service.createCompetition(user, request);
        verify(activityRepo).save(captor.capture());
        Competition competition = captor.getValue();
        Competition expected = new Competition(user, TimeSlot.getTimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "yacht", List.of("captain"), "organization", "female", true);

        assertThat(competition.getId()).isEqualTo(expected.getId());
        assertThat(competition.getOwner()).isEqualTo(expected.getOwner());
        assertThat(competition.getTimeSlot()).isEqualTo(expected.getTimeSlot());
        assertThat(competition.getBoat()).isEqualTo(expected.getBoat());
        assertThat(competition.getPositions()).isEqualTo(expected.getPositions());
        assertThat(competition.getOrganization()).isEqualTo(expected.getOrganization());
        assertThat(competition.getGender()).isEqualTo(expected.getGender());
        assertThat(competition.getCompetitive()).isEqualTo(expected.getCompetitive());
    }

    @Test
    public void editCompetitionOneField() throws UnauthorizedException, ActivityNotFoundException {
        ActivityRequestModel request = new ActivityRequestModel("10-10-2022 14:30; 10-10-2022 16:00", "yacht", List.of("captain"), "organization", "female", true);
        setCompetitionRepo();
        service.editActivity(user, competition.getId(), request);
        ArgumentCaptor<Competition> captor = ArgumentCaptor.forClass(Competition.class);
        verify(activityRepo).save(captor.capture());
        Competition edited = captor.getValue();

        assertThat(edited.getBoat()).isEqualTo("yacht");
    }

    @Test
    public void editCompetitionMoreFields() throws UnauthorizedException, ActivityNotFoundException {
        ActivityRequestModel request = new ActivityRequestModel("10-10-2022 13:00; 10-10-2022 16:00", "boat", List.of("captain", "cox"), "organization", "female", true);
        setCompetitionRepo();
        service.editActivity(user, competition.getId(), request);
        ArgumentCaptor<Competition> captor = ArgumentCaptor.forClass(Competition.class);
        verify(activityRepo).save(captor.capture());
        Competition edited = captor.getValue();

        assertThat(edited.getTimeSlot()).isEqualTo(TimeSlot.getTimeSlot("10-10-2022 13:00; 10-10-2022 16:00"));
        assertThat(edited.getPositions()).isEqualTo(List.of("captain", "cox"));
    }


    @Test
    public void editCompetitionAllFields() throws UnauthorizedException, ActivityNotFoundException {
        ActivityRequestModel request = new ActivityRequestModel("11-10-2022 13:00; 11-10-2022 16:00", "yacht", List.of("captain", "cox"), "gryffindor", "male", false);
        setCompetitionRepo();
        service.editActivity(user, competition.getId(), request);
        ArgumentCaptor<Competition> captor = ArgumentCaptor.forClass(Competition.class);
        verify(activityRepo).save(captor.capture());
        Competition edited = captor.getValue();

        assertThat(edited.getTimeSlot()).isEqualTo(TimeSlot.getTimeSlot("11-10-2022 13:00; 11-10-2022 16:00"));
        assertThat(edited.getBoat()).isEqualTo("yacht");
        assertThat(edited.getPositions()).isEqualTo(List.of("captain", "cox"));
        assertThat(edited.getOrganization()).isEqualTo("gryffindor");
        assertThat(edited.getGender()).isEqualTo("male");
        assertThat(edited.getCompetitive()).isEqualTo(false);
    }

    @Test
    public void editTrainingOneField() throws UnauthorizedException, ActivityNotFoundException {
        ActivityRequestModel request = new ActivityRequestModel("10-10-2022 14:30; 10-10-2022 16:00", "yacht", List.of("captain"), null, null, false);
        setTrainingRepo();
        ActivityService service = new ActivityService(activityRepo);
        service.editActivity(new NetId("zosia"), training.getId(), request);
        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
        verify(activityRepo).save(captor.capture());
        Training edited = captor.getValue();

        assertEquals("zosia", edited.getOwner().getNetIdValue());
        assertEquals("10-10-2022 14:30;10-10-2022 16:00", edited.getTimeSlot().toString());
        assertEquals("yacht", edited.getBoat());
        assertEquals(List.of("captain"), edited.getPositions());
    }


    @Test
    public void editTrainingSomeFields() throws UnauthorizedException, ActivityNotFoundException {
        ActivityRequestModel request = new ActivityRequestModel("10-10-2022 14:00; 10-11-2022 16:00", "yacht", List.of("captain"), null, null, false);
        setTrainingRepo();
        ActivityService service = new ActivityService(activityRepo);
        service.editActivity(new NetId("zosia"), training.getId(), request);
        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
        verify(activityRepo).save(captor.capture());
        Training edited = captor.getValue();

        assertEquals("zosia", edited.getOwner().getNetIdValue());
        assertEquals("10-10-2022 14:00;10-11-2022 16:00", edited.getTimeSlot().toString());
        assertEquals("yacht", edited.getBoat());
        assertEquals(List.of("captain"), edited.getPositions());
    }

    @Test
    public void editTrainingAllFields() throws UnauthorizedException, ActivityNotFoundException {
        ActivityRequestModel request = new ActivityRequestModel("10-10-2022 14:00; 10-11-2022 16:00", "yacht", List.of("captain"), null, null, false);
        setTrainingRepo();
        service.editActivity(new NetId("zosia"), training.getId(), request);
        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
        verify(activityRepo).save(captor.capture());
        Training edited = captor.getValue();

        assertEquals("zosia", edited.getOwner().getNetIdValue());
        assertEquals("10-10-2022 14:00;10-11-2022 16:00", edited.getTimeSlot().toString());
        assertEquals("yacht", edited.getBoat());
        assertEquals(List.of("captain"), edited.getPositions());
    }

    @Test
    public void editTrainingUnauthorized() {
        ActivityRequestModel request = new ActivityRequestModel("10-10-2022 14:00; 10-11-2022 16:00", "yacht", List.of("captain"), null, null, false);
        setTrainingRepo();

        assertThrows(UnauthorizedException.class, () -> service.editActivity(new NetId("harry"), training.getId(), request));
    }


    @Test
    public void editCompetitionUnauthorized() {
        ActivityRequestModel request = new ActivityRequestModel("11-10-2022 13:00; 11-10-2022 16:00", "yacht", List.of("captain", "cox"), "gryffindor", "male", false);
        setCompetitionRepo();

        assertThrows(UnauthorizedException.class, () -> service.editActivity(new NetId("zosia"), competition.getId(), request));
    }

    @Test
    public void getAllWithOneActivity() {
        when(activityRepo.findAll()).thenReturn(List.of(training));
        List<Activity> activities = service.getAll();

        assertEquals(1, activities.size());
        assertEquals(training, activities.get(0));
    }


    @Test
    public void getAllWithTrainingsAndCompetitions() {
        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
        List<Activity> activities = service.getAll();

        assertEquals(2, activities.size());
        assertEquals(training, activities.get(0));
        assertEquals(competition, activities.get(1));
    }

    @Test
    public void getTrainingsWithTrainingsAndCompetitions() {
        when(activityRepo.findAll()).thenReturn(List.of(training));
        List<Training> trainings = service.getTrainings();

        assertEquals(1, trainings.size());
        assertEquals(training, trainings.get(0));
    }

    @Test
    public void getAllWithNoActivities() {
        when(activityRepo.findAll()).thenReturn(List.of());
        List<Activity> activities = service.getAll();

        assertEquals(0, activities.size());
    }

    @Test
    public void getAllCompetitionsWithOneCompetition() {
        when(activityRepo.findAll()).thenReturn(List.of(competition));
        List<Competition> competitions = service.getCompetitions();

        assertEquals(1, competitions.size());
        assertEquals(competition, competitions.get(0));
    }

    @Test
    public void getAllCompetitionsWithNoCompetitions() {
        when(activityRepo.findAll()).thenReturn(List.of());
        List<Competition> competitions = service.getCompetitions();

        assertEquals(0, competitions.size());
    }

    @Test
    public void getAllCompetitionsWithTrainingsAndCompetitions() {
        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
        List<Competition> competitions = service.getCompetitions();

        assertEquals(1, competitions.size());
        assertEquals(competition, competitions.get(0));
    }

    @Test
    public void getAllTrainingsWithJustCompetitions() {
        when(activityRepo.findAll()).thenReturn(List.of(competition));
        List<Training> trainings = service.getTrainings();

        assertEquals(0, trainings.size());
    }

    @Test
    public void getActivityByIdWithNoActivities() {
        ActivityRepository activityRepo = mock(ActivityRepository.class);
        ActivityService service = new ActivityService(activityRepo);
        when(activityRepo.findAll()).thenReturn(List.of());

        assertThrows(ActivityNotFoundException.class, () -> service.getById(1));
    }

    @Test
    public void getActivityByIdWithOneActivity() throws ActivityNotFoundException {
        when(activityRepo.findAll()).thenReturn(List.of(training));
        training.setId(1);
        setTrainingRepo();
        Activity activity = service.getById(1);

        assertEquals(training, activity);
    }

    @Test
    public void getActivityByIdWithTwoActivities() throws ActivityNotFoundException {
        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
        competition.setId(2);
        setCompetitionRepo();
        Activity activity = service.getById(2);

        assertEquals(activity, competition);
    }

    @Test
    public void getActivityByIdWithTwoActivitiesAndWrongId() {
        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
        assertThrows(ActivityNotFoundException.class, () -> service.getById(3));
    }

    @Test
    public void deleteActivityByIdWithNoActivities() {
        when(activityRepo.findAll()).thenReturn(List.of());
        assertThrows(ActivityNotFoundException.class, () -> service.deleteById(new NetId("zosia"), 1));
    }

    @Test
    public void deleteActivityByIdWithOneActivity() throws ActivityNotFoundException, UnauthorizedException {
        when(activityRepo.findAll()).thenReturn(List.of(training));
        training.setId(1);
        setTrainingRepo();
        service.deleteById(new NetId("zosia"), 1);
        verify(activityRepo, times(1)).deleteById(1);
    }

    @Test
    public void deleteActivityByIdWithTwoActivities() throws ActivityNotFoundException, UnauthorizedException {
        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
        training.setId(1);
        competition.setId(2);
        setTrainingRepo();
        setCompetitionRepo();
        service.deleteById(new NetId("zosia"), 1);
        verify(activityRepo, times(1)).deleteById(1);
    }

    @Test
    public void deleteActivityByIdWithTwoActivitiesAndWrongId() {
        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
        training.setId(1);
        competition.setId(2);
        setTrainingRepo();
        setCompetitionRepo();

        assertThrows(ActivityNotFoundException.class, () -> service.deleteById(new NetId("zosia"), 3));
    }

    @Test
    public void deleteActivityByIdWithTwoActivitiesAndWrongOwner() {
        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
        training.setId(1);
        competition.setId(2);
        setTrainingRepo();
        setCompetitionRepo();

        assertThrows(UnauthorizedException.class, () -> service.deleteById(new NetId("harry"), 1));
    }

    @Test
    public void deleteActivityByUserWithNoActivities() {
        when(activityRepo.findAll()).thenReturn(List.of());
        assertThrows(ActivityNotFoundException.class, () -> service.deleteByUser(new NetId("zosia"), new NetId("zosia")));
    }

    @Test
    public void deleteActivityByUserWithOneActivity() throws ActivityNotFoundException, UnauthorizedException {
        when(activityRepo.findAll()).thenReturn(List.of(training));
        service.deleteByUser(new NetId("zosia"), new NetId("zosia"));
        verify(activityRepo, times(1)).deleteAll(List.of(training));
    }

    @Test
    public void deleteActivityByUserWithTwoActivities() throws ActivityNotFoundException, UnauthorizedException {
        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
        service.deleteByUser(new NetId("zosia"), new NetId("zosia"));
        verify(activityRepo, times(1)).deleteAll(List.of(training));
    }

    @Test
    public void deleteActivityByUserWithTwoActivitiesAndWrongOwner() {
        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
        assertThrows(UnauthorizedException.class, () -> service.deleteByUser(new NetId("zosia"), new NetId("paula")));
    }

    @Test
    public void getActivitiesByUserWithNoActivities() {
        when(activityRepo.findAll()).thenReturn(List.of());
        assertThrows(ActivityNotFoundException.class, () -> service.getByUsername("zosia"));
    }

    @Test
    public void getActivitiesByUserWithOneActivity() throws ActivityNotFoundException {
        when(activityRepo.findAll()).thenReturn(List.of(training));
        assertEquals(List.of(training), service.getByUsername("zosia"));
    }

    @Test
    public void getActivitiesByUserWithTwoActivities() throws ActivityNotFoundException {
        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
        assertEquals(List.of(training), service.getByUsername("zosia"));
    }
}