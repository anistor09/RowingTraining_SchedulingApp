package nl.tudelft.sem.template.example.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {
    private final transient ActivityRepository activityRepository;

    /**
     * Constructor for ActivityService.
     * @param activityRepository the repository for activities
     */
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     *
     * @return all activities
     */
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    /**
     * Adds a training to the database.
     * @param username
     * @param request
     * @return new training
     */
    public Training createTraining(Username username, ActivityRequestModel request) {
        username = new Username("user");
        Training training = new Training(username, request.getTimeSlot(), request.getBoat(), request.getPositions());
        activityRepository.save(training);
        return training;
    }

    /**
     * Adds a competition to the database.
     * @param username
     * @param request
     * @return new competition
     */
    public Competition createCompetition(Username username, ActivityRequestModel request) {
        username = new Username("user");
        Competition competition = new Competition(username, request.getTimeSlot(), request.getBoat(), request.getPositions(), request.getOrganization(), request.getGender(), request.getCompetitive());
        activityRepository.save(competition);
        return competition;
    }

    /**
     * Edits the boat.
     * @param id
     * @param boat
     */
    public void editBoat(Long id, String boat) {
        Activity activity = activityRepository.findById(id).get();
        activity.setBoat(boat);
        activityRepository.save(activity);
    }

    /**
     * Edits positions.
     * @param id
     * @param positions
     */
    public void editPositions(Long id, List<String> positions) {
        Activity activity = activityRepository.findById(id).get();
        activity.setPositions(positions);
        activityRepository.save(activity);
    }

    /**
     * Edits the date.
     * @param id
     * @param timeSlot
     */
    public void editTimeSlot(Long id, TimeSlot timeSlot) {
        Activity activity = activityRepository.findById(id).get();
        activity.setTimeSlot(timeSlot);
        activityRepository.save(activity);
    }

    /**
     * Deletes all activities of the given user.
     * @param username
     */
    public void deleteByUser(Username username) {
        List<Activity> activities = activityRepository.findAll();
        List<Activity> toDelete = new ArrayList<>();
        for (Activity activity : activities) {
            if (activity.getOwner().equals(username)) {
                toDelete.add(activity);
            }
        }
        activityRepository.deleteAll(toDelete);
    }

    /**
     * Deletes an activity by id.
     * @param id
     */
    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    /**
     * Gets all trainings.
     * @return all trainings
     */
    public List<Training> getTrainings() {
        List<Activity> activities = getAll();
        List<Training> trainings = new ArrayList<>();
        for(Activity activity : activities) {
            if(activity instanceof Training)
                trainings.add((Training) activity);
        }
        return trainings;
    }

    /**
     * Gets all competitions.
     * @return all competitions
     */
    public List<Competition> getCompetitions() {
        List<Activity> activities = getAll();
        List<Competition> competitions = new ArrayList<>();
        for(Activity activity : activities) {
            if(activity instanceof Competition)
                competitions.add((Competition) activity);
        }
        return competitions;
    }

    /**
     * Gets all activities of the given user.
     * @param username
     * @return all activties of the given user
     */
    public List<Activity> getByUsername(String username) {
        List<Activity> activities = getAll();
        List<Activity> result = new ArrayList<>();
        for(Activity activity : activities) {
            if(activity.getOwner().toString().equals(username))
                result.add(activity);
        }
        return result;
    }
}
