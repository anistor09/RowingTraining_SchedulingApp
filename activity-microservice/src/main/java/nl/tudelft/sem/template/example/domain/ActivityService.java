package nl.tudelft.sem.template.example.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    public Training createTraining(Username username, ActivityRequestModel request) {
        username = new Username("user");
        Training training = new Training(username, request.getDate(), request.getTime(), request.getBoat(), request.getPositions());
        activityRepository.save(training);
        return training;
    }

    public Competition createCompetition(Username username, ActivityRequestModel request) {
        username = new Username("user");
        Competition competition = new Competition(username, request.getDate(), request.getTime(), request.getBoat(), request.getPositions(), request.getOrganization(), request.getGender(), request.getCompetitive());
        activityRepository.save(competition);
        return competition;
    }

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

    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    public List<Training> getTrainings() {
        List<Activity> activities = getAll();
        List<Training> trainings = new ArrayList<>();
        for(Activity activity : activities) {
            if(activity instanceof Training)
                trainings.add((Training) activity);
        }
        return trainings;
    }

    public List<Competition> getCompetitions() {
        List<Activity> activities = getAll();
        List<Competition> competitions = new ArrayList<>();
        for(Activity activity : activities) {
            if(activity instanceof Competition)
                competitions.add((Competition) activity);
        }
        return competitions;
    }

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
