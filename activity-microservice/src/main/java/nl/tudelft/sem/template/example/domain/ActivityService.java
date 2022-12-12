package nl.tudelft.sem.template.example.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Competition competition = new Competition(username, request.getTimeSlot(), request.getBoat(), request.getPositions(), request.getOrganization(), request.getGender(), request.getCompetitive());
        activityRepository.save(competition);
        return competition;
    }

    /**
     * Edits an activity.
     * @param id
     * @param request
     */
    public ResponseEntity editActivity(Username username, Long id, ActivityRequestModel request) throws UnauthorizedException {
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()) {
            if (activity.get().getOwner().getUsernameValue().equals(username.getUsernameValue())) {
                if (!isNullOrEmpty(request.getTimeSlot())) {
                    activity.get().setTimeSlot(request.getTimeSlot());
                } else {
                    activity.get().setTimeSlot(activity.get().getTimeSlot());
                }
                if (!isNullOrEmpty(request.getBoat())) {
                    activity.get().setBoat(request.getBoat());
                } else {
                    activity.get().setBoat(activity.get().getBoat());
                }
                if (!isNullOrEmpty(request.getPositions())) {
                    activity.get().setPositions(request.getPositions());
                } else {
                    activity.get().setPositions(activity.get().getPositions());
                }
                if (!isNullOrEmpty(request.getOrganization())) {
                    ((Competition) activity.get()).setOrganization(request.getOrganization());
                } else {
                    ((Competition) activity.get()).setOrganization(((Competition) activity.get()).getOrganization());
                }
                if (!isNullOrEmpty(request.getGender())) {
                    ((Competition) activity.get()).setGender(request.getGender());
                } else {
                    ((Competition) activity.get()).setGender(((Competition) activity.get()).getGender());
                }
                if (!isNullOrEmpty(request.getCompetitive())) {
                    ((Competition) activity.get()).setCompetitive(request.getCompetitive());
                } else {
                    ((Competition) activity.get()).setCompetitive(((Competition) activity.get()).getCompetitive());
                }
                activityRepository.save(activity.get());
            } else {
                throw new UnauthorizedException("You are not the owner of this activity.");
            }
        }
        return ResponseEntity.ok("successfully edited the activity");
    }

    /**
     * Deletes all activities of the given user.
     * @param username
     * @param logged
     */
    public void deleteByUser(Username username, Username logged) throws UnauthorizedException {
        if(username.getUsernameValue().equals(logged.getUsernameValue())) {
            List<Activity> activities = activityRepository.findAll();
            List<Activity> toDelete = new ArrayList<>();
            for (Activity activity : activities) {
                if (activity.getOwner().getUsernameValue().equals(username.getUsernameValue())) {
                    toDelete.add(activity);
                }
            }
            activityRepository.deleteAll(toDelete);
        } else {
            throw new UnauthorizedException("You are not the owner of this activity.");
        }
    }

    public void deleteById(Username username, Long id) throws UnauthorizedException {
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()) {
            if (activity.get().getOwner().getUsernameValue().equals(username.getUsernameValue())) {
                activityRepository.deleteById(id);
            } else {
                throw new UnauthorizedException("You are not the owner of this activity.");
            }
        }
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

    public Optional<Activity> getById(long id) {
        return activityRepository.findById(id);
    }

    private static boolean isNullOrEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            String s = (String) o;
            return s.isEmpty();
        }
        return false;
    }
}
