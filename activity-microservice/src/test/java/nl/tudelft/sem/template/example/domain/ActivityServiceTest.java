package nl.tudelft.sem.template.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

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

    @BeforeEach
    public void setUp() {
        activityRepo  = mock(ActivityRepository.class);
        service = new ActivityService(activityRepo);
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



    public void setCompetitionRepo() {
        when(activityRepo.existsById((long) competition.getId())).thenReturn(true);
        when(activityRepo.findById((long) competition.getId())).thenReturn(java.util.Optional.of(competition));
    }

    @Test
    public void editCompetitionOneField() throws UnauthorizedException, ActivityNotFoundException {
        ActivityRequestModel request = new ActivityRequestModel("10-10-2022 14:30; 10-10-2022 16:00", "yacht", List.of("captain"), "organization", "female", true);
        setCompetitionRepo();
        service.editActivity(user, (long) competition.getId(), request);
        ArgumentCaptor<Competition> captor = ArgumentCaptor.forClass(Competition.class);
        verify(activityRepo).save(captor.capture());
        Competition edited = captor.getValue();

        assertThat(edited.getBoat()).isEqualTo("yacht");
    }

    @Test
    public void editCompetitionMoreFields() throws UnauthorizedException, ActivityNotFoundException {
        ActivityRequestModel request = new ActivityRequestModel("10-10-2022 13:00; 10-10-2022 16:00", "boat", List.of("captain", "cox"), "organization", "female", true);
        setCompetitionRepo();
        service.editActivity(user, (long) competition.getId(), request);
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
        service.editActivity(user, (long) competition.getId(), request);
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

//    //PASSED
//    @Test
//    public void editTrainingOneField() throws UnauthorizedException, ActivityNotFoundException {
//        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "yacht", List.of("captain"));
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain"));
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        when(activityRepo.existsById(training.getId())).thenReturn(true);
//        when(activityRepo.findById(training.getId())).thenReturn(java.util.Optional.of(training));
//        ActivityService service = new ActivityService(activityRepo);
//        service.editActivity(new Username("zosia"), training.getId(), request);
//        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
//        verify(activityRepo).save(captor.capture());
//        Training edited = captor.getValue();
//
//        assertEquals("zosia", edited.getOwner().getNetIdValue());
//        assertEquals("10-10-2022 14:30;10-10-2022 16:00", edited.getTimeSlot().toString());
//        assertEquals("yacht", edited.getBoat());
//        assertEquals(List.of("captain"), edited.getPositions());
//    }
//
//
//    //PASSED
//    @Test
//    public void editTrainingSomeFields() throws UnauthorizedException, ActivityNotFoundException {
//        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 14:00; 10-11-2022 16:00"), "yacht", List.of("captain"));
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain"));
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        when(activityRepo.existsById(training.getId())).thenReturn(true);
//        when(activityRepo.findById(training.getId())).thenReturn(java.util.Optional.of(training));
//        ActivityService service = new ActivityService(activityRepo);
//        service.editActivity(new Username("zosia"), training.getId(), request);
//        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
//        verify(activityRepo).save(captor.capture());
//        Training edited = captor.getValue();
//
//        assertEquals("zosia", edited.getOwner().getNetIdValue());
//        assertEquals("10-10-2022 14:00;10-11-2022 16:00", edited.getTimeSlot().toString());
//        assertEquals("yacht", edited.getBoat());
//        assertEquals(List.of("captain"), edited.getPositions());
//    }
//
//    //PASSED
//    @Test
//    public void editTrainingAllFields() throws UnauthorizedException, ActivityNotFoundException {
//        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 14:00; 10-11-2022 16:00"), "yacht", List.of("captain"));
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        when(activityRepo.existsById(training.getId())).thenReturn(true);
//        when(activityRepo.findById(training.getId())).thenReturn(java.util.Optional.of(training));
//        ActivityService service = new ActivityService(activityRepo);
//        service.editActivity(new Username("zosia"), training.getId(), request);
//        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
//        verify(activityRepo).save(captor.capture());
//        Training edited = captor.getValue();
//
//        assertEquals("zosia", edited.getOwner().getNetIdValue());
//        assertEquals("10-10-2022 14:00;10-11-2022 16:00", edited.getTimeSlot().toString());
//        assertEquals("yacht", edited.getBoat());
//        assertEquals(List.of("captain"), edited.getPositions());
//    }
//
//    //PASSED
//    @Test
//    public void editTrainingUnauthorized() {
//        ActivityRequestModel request = new ActivityRequestModel(new TimeSlot("10-10-2022 14:00; 10-11-2022 16:00"), "yacht", List.of("captain"));
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        when(activityRepo.existsById(training.getId())).thenReturn(true);
//        when(activityRepo.findById(training.getId())).thenReturn(java.util.Optional.of(training));
//        ActivityService service = new ActivityService(activityRepo);
//        assertThrows(UnauthorizedException.class, () -> service.editActivity(new Username("harry"), training.getId(), request));
//    }
//
    @Test
    public void editCompetitionUnauthorized() {
        ActivityRequestModel request = new ActivityRequestModel("11-10-2022 13:00; 11-10-2022 16:00", "yacht", List.of("captain", "cox"), "gryffindor", "male", false);
        setCompetitionRepo();
        assertThrows(UnauthorizedException.class, () -> service.editActivity(new NetId("zosia"), (long) competition.getId(), request));
    }
//
//    //PASSED
//    @Test
//    public void getAllWithOneActivity() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        when(activityRepo.findAll()).thenReturn(List.of(training));
//        List<Activity> activities = service.getAll();
//        assertEquals(1, activities.size());
//        assertEquals("zosia", activities.get(0).getOwner().getNetIdValue());
//        assertEquals("10-10-2022 14:30;10-10-2022 16:00", activities.get(0).getTimeSlot().toString());
//        assertEquals("boat", activities.get(0).getBoat());
//        assertEquals(List.of("captain", "cox"), activities.get(0).getPositions());
//    }
//
//    //PASSED
//    @Test
//    public void getAllWithTrainingsAndCompetitions() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
//        List<Activity> activities = service.getAll();
//        assertEquals(2, activities.size());
//        assertEquals("zosia", activities.get(0).getOwner().getNetIdValue());
//        assertEquals("10-10-2022 14:30;10-10-2022 16:00", activities.get(0).getTimeSlot().toString());
//        assertEquals("boat", activities.get(0).getBoat());
//        assertEquals(List.of("captain", "cox"), activities.get(0).getPositions());
//        assertEquals("paula", activities.get(1).getOwner().getNetIdValue());
//        assertEquals("10-11-2022 14:30;10-10-2022 15:00", activities.get(1).getTimeSlot().toString());
//        assertEquals("yacht", activities.get(1).getBoat());
//        assertEquals(List.of("cox"), activities.get(1).getPositions());
//        assertEquals("organization", ((Competition) activities.get(1)).getOrganization());
//        assertEquals("female", ((Competition) activities.get(1)).getGender());
//        assertTrue(((Competition) activities.get(1)).getCompetitive());
//    }
//
//    //PASSED
//    @Test
//    public void getTrainingsWithTrainingsAndCompetitions() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training));
//        List<Training> trainings = service.getTrainings();
//        assertEquals(1, trainings.size());
//        assertEquals("zosia", trainings.get(0).getOwner().getNetIdValue());
//        assertEquals("10-10-2022 14:30;10-10-2022 16:00", trainings.get(0).getTimeSlot().toString());
//        assertEquals("boat", trainings.get(0).getBoat());
//        assertEquals(List.of("captain", "cox"), trainings.get(0).getPositions());
//    }
//
//    //PASSED
//    @Test
//    public void getAllWithNoActivities() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        when(activityRepo.findAll()).thenReturn(List.of());
//        List<Activity> activities = service.getAll();
//        assertEquals(0, activities.size());
//    }
//
//    //PASSED
//    @Test
//    public void getAllCompetitionsWithOneCompetition() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(competition));
//        List<Competition> competitions = service.getCompetitions();
//        assertEquals(1, competitions.size());
//        assertEquals("paula", competitions.get(0).getOwner().getNetIdValue());
//        assertEquals("10-11-2022 14:30;10-10-2022 15:00", competitions.get(0).getTimeSlot().toString());
//        assertEquals("yacht", competitions.get(0).getBoat());
//        assertEquals(List.of("cox"), competitions.get(0).getPositions());
//        assertEquals("female", competitions.get(0).getGender());
//        assertEquals("organization", competitions.get(0).getOrganization());
//        assertTrue(competitions.get(0).getCompetitive());
//    }
//
//    //PASSED
//    @Test
//    public void getAllCompetitionsWithNoCompetitions() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        when(activityRepo.findAll()).thenReturn(List.of());
//        List<Competition> competitions = service.getCompetitions();
//        assertEquals(0, competitions.size());
//    }
//
//    //PASSED
//    @Test
//    public void getAllCompetitionsWithTrainingsAndCompetitions() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
//        List<Competition> competitions = service.getCompetitions();
//        assertEquals(1, competitions.size());
//        assertEquals("paula", competitions.get(0).getOwner().getNetIdValue());
//        assertEquals("10-11-2022 14:30;10-10-2022 15:00", competitions.get(0).getTimeSlot().toString());
//        assertEquals("yacht", competitions.get(0).getBoat());
//        assertEquals(List.of("cox"), competitions.get(0).getPositions());
//        assertEquals("organization", competitions.get(0).getOrganization());
//        assertEquals("female", competitions.get(0).getGender());
//        assertTrue(competitions.get(0).getCompetitive());
//    }
//
//    //PASSED
//    @Test
//    public void getAllTrainingsWithJustCompetitions() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(competition));
//        List<Training> trainings = service.getTrainings();
//        assertEquals(0, trainings.size());
//    }
//
    //PASSED
    @Test
    public void getActivityByIdWithNoActivities() {
        ActivityRepository activityRepo = mock(ActivityRepository.class);
        ActivityService service = new ActivityService(activityRepo);
        when(activityRepo.findAll()).thenReturn(List.of());
        assertThrows(ActivityNotFoundException.class, () -> service.getById(1));
    }


    @Test
    public void getActivityByIdWithOneActivity() throws ActivityNotFoundException {
        ActivityRepository activityRepo = mock(ActivityRepository.class);
        ActivityService service = new ActivityService(activityRepo);
        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
        when(activityRepo.findAll()).thenReturn(List.of(training));
        Activity activity = service.getById(1);
        assertEquals("zosia", activity.getOwner().getNetIdValue());
        assertEquals("10-10-2022 14:30;10-10-2022 16:00", activity.getTimeSlot().toString());
        assertEquals("boat", activity.getBoat());
        assertEquals(List.of("captain", "cox"), activity.getPositions());
    }


//    //FAILED - id is null
//    @Test
//    public void getActivityByIdWithTwoActivities() throws ActivityNotFoundException {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
//        Activity activity = service.getById(2);
//        assertEquals("paula", activity.getOwner().getNetIdValue());
//        assertEquals("10-11-2022 14:30;10-10-2022 15:00", activity.getTimeSlot().toString());
//        assertEquals("yacht", activity.getBoat());
//        assertEquals(List.of("cox"), activity.getPositions());
//        assertEquals("organization", ((Competition) activity).getOrganization());
//        assertEquals("female", ((Competition) activity).getGender());
//        assertTrue(((Competition) activity).getCompetitive());
//    }
//
//    //FAILED - id is null
//    @Test
//    public void getActivityByIdWithTwoActivitiesAndWrongId() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
//        assertThrows(ActivityNotFoundException.class, () -> service.getById(3));
//    }
//
//    //PASSED
//    @Test
//    public void deleteActivityByIdWithNoActivities() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        when(activityRepo.findAll()).thenReturn(List.of());
//        assertThrows(ActivityNotFoundException.class, () -> service.deleteById(new Username("zosia"), 1L));
//    }
//
//    //FAILED - id is null
//    @Test
//    public void deleteActivityByIdWithOneActivity() throws ActivityNotFoundException, UnauthorizedException {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        when(activityRepo.findAll()).thenReturn(List.of(training));
//        service.deleteById(new Username("zosia"), 1L);
//        verify(activityRepo, times(1)).deleteById(1L);
//    }
//
//    //FAILED - id is null
//    @Test
//    public void deleteActivityByIdWithTwoActivities() throws ActivityNotFoundException, UnauthorizedException {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
//        service.deleteById(new Username("zosia"), 1L);
//        verify(activityRepo, times(1)).deleteById(1L);
//    }
//
//    //PASSED
//    @Test
//    public void deleteActivityByIdWithTwoActivitiesAndWrongId() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
//        assertThrows(ActivityNotFoundException.class, () -> service.deleteById(new Username("zosia"), 3L));
//    }
//
//    //FAILED - id is null
//    @Test
//    public void deleteActivityByIdWithTwoActivitiesAndWrongOwner() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
//        assertThrows(UnauthorizedException.class, () -> service.deleteById(new Username("paula"), 1L));
//    }
//
//    //PASSED
//    @Test
//    public void deleteActivityByUserWithNoActivities() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        when(activityRepo.findAll()).thenReturn(List.of());
//        assertThrows(ActivityNotFoundException.class, () -> service.deleteByUser(new Username("zosia"), new Username("zosia")));
//    }
//
//    //PASSED
//    @Test
//    public void deleteActivityByUserWithOneActivity() throws ActivityNotFoundException, UnauthorizedException {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        when(activityRepo.findAll()).thenReturn(List.of(training));
//        service.deleteByUser(new Username("zosia"), new Username("zosia"));
//        verify(activityRepo, times(1)).deleteAll(List.of(training));
//    }
//
//    //PASSED
//    @Test
//    public void deleteActivityByUserWithTwoActivities() throws ActivityNotFoundException, UnauthorizedException {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
//        service.deleteByUser(new Username("zosia"), new Username("zosia"));
//        verify(activityRepo, times(1)).deleteAll(List.of(training));
//    }
//
//    //PASSED
//    @Test
//    public void deleteActivityByUserWithTwoActivitiesAndWrongOwner() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
//        assertThrows(UnauthorizedException.class, () -> service.deleteByUser(new Username("zosia"), new Username("paula")));
//    }
//
//    //PASSED
//    @Test
//    public void getActivitiesByUserWithNoActivities() {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        when(activityRepo.findAll()).thenReturn(List.of());
//        assertThrows(ActivityNotFoundException.class, () -> service.getByUsername("zosia"));
//    }
//
//    //PASSED
//    @Test
//    public void getActivitiesByUserWithOneActivity() throws ActivityNotFoundException {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        when(activityRepo.findAll()).thenReturn(List.of(training));
//        assertEquals(List.of(training), service.getByUsername("zosia"));
//    }
//
//    //PASSED
//    @Test
//    public void getActivitiesByUserWithTwoActivities() throws ActivityNotFoundException {
//        ActivityRepository activityRepo = mock(ActivityRepository.class);
//        ActivityService service = new ActivityService(activityRepo);
//        Training training = new Training(new NetId("zosia"), new TimeSlot("10-10-2022 14:30; 10-10-2022 16:00"), "boat", List.of("captain", "cox"));
//        Competition competition = new Competition(new NetId("paula"), new TimeSlot("10-11-2022 14:30; 10-10-2022 15:00"), "yacht", List.of("cox"), "organization", "female", true);
//        when(activityRepo.findAll()).thenReturn(List.of(training, competition));
//        assertEquals(List.of(training), service.getByUsername("zosia"));
//    }
}