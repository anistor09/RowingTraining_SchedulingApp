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
    public Training createTraining(NetId username, ActivityRequestModel request) {
        Training training = new Training(username,request.getTimeSlot(), request.getBoat(), request.getPositions());
        activityRepository.save(training);
        return training;
    }

    /**
     * Adds a competition to the database.
     * @param username
     * @param request
     * @return new competition
     */
    public Competition createCompetition(NetId username, ActivityRequestModel request) {
        Competition competition = new Competition(username, request.getTimeSlot(), request.getBoat(), request.getPositions(), request.getOrganization(), request.getGender(), request.getCompetitive());
        activityRepository.save(competition);
        return competition;
    }

    /**
     * Edits an activity.
     * @param id
     * @param request
     */
    public ResponseEntity editActivity(NetId netId, Long id, ActivityRequestModel request) throws UnauthorizedException, ActivityNotFoundException {
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()) {
            Activity change = activity.get();
            if (change.getOwner().getNetIdValue().equals(netId.getNetIdValue())) {
                if (!isNullOrEmpty(request.getTimeSlot())) {
                    change.setTimeSlot(request.getTimeSlot());
                }
                if (!isNullOrEmpty(request.getBoat())) {
                    change.setBoat(request.getBoat());
                }
                if (!isNullOrEmpty(request.getPositions())) {
                    change.setPositions(request.getPositions());
                }
                if(activity.get() instanceof Competition) {
                    if (!isNullOrEmpty(request.getOrganization())) {
                        ((Competition) change).setOrganization(request.getOrganization());
                    }
                    if (!isNullOrEmpty(request.getGender())) {
                        ((Competition) change).setGender(request.getGender());
                    }
                    if (!isNullOrEmpty(request.getCompetitive())) {
                        ((Competition) change).setCompetitive(request.getCompetitive());
                    }
                }
                activityRepository.save(change);
            } else {
                throw new UnauthorizedException("You are not the owner of this activity.");
            }
        } else {
            throw new ActivityNotFoundException(id);
        }
        return ResponseEntity.ok("successfully edited the activity");
    }

    /**
     * Deletes all activities of the given user.
     * @param netId
     * @param logged
     */
    public void deleteByUser(NetId netId, NetId logged) throws UnauthorizedException, ActivityNotFoundException {
        if(netId.getNetIdValue().equals(logged.getNetIdValue())) {
            List<Activity> activities = activityRepository.findAll();
            List<Activity> toDelete = new ArrayList<>();
            for (Activity activity : activities) {
                if (activity.getOwner().getNetIdValue().equals(netId.getNetIdValue())) {
                    toDelete.add(activity);
                }
            }
            if (toDelete.isEmpty()) {
                throw new ActivityNotFoundException("No activities found for this user.");
            }
            activityRepository.deleteAll(toDelete);
        } else {
            throw new UnauthorizedException("You are not the owner of this activity.");
        }
    }

    public void deleteById(NetId netId, Long id) throws UnauthorizedException, ActivityNotFoundException {
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()) {
            if (activity.get().getOwner().getNetIdValue().equals(netId.getNetIdValue())) {
                activityRepository.deleteById(id);
            } else {
                throw new UnauthorizedException("You are not the owner of this activity.");
            }
        } else {
            throw new ActivityNotFoundException(id);
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
    public List<Activity> getByUsername(String username) throws ActivityNotFoundException {
        List<Activity> activities = getAll();
        List<Activity> result = new ArrayList<>();
        for(Activity activity : activities) {
            if(activity.getOwner().toString().equals(username))
                result.add(activity);
        }
        if (result.isEmpty()) {
            throw new ActivityNotFoundException("No activities found for this user.");
        }
        return result;
    }

    public Activity getById(long id) throws ActivityNotFoundException {
        if (activityRepository.findById(id).isPresent()) {
            return activityRepository.findById(id).get();
        } else {
            throw new ActivityNotFoundException(id);
        }
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
