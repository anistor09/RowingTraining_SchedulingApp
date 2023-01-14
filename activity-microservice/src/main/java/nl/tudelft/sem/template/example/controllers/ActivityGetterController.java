package nl.tudelft.sem.template.example.controllers;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityGetterController {
    private final transient AuthManager authManager;
    private final transient ActivityService activityService;

    public ActivityGetterController(AuthManager authManager, ActivityService activityService) {
        this.authManager = authManager;
        this.activityService = activityService;
    }

    /**
     * Gets all activities.
     * @return all activities
     */
    @GetMapping("/all")
    public List<Activity> getAll() {
        return activityService.getAll();
    }

    /**
     * Gets all trainings.
     * @return all trainings
     */
    @GetMapping("/training")
    public List<Training> getTrainings() { return activityService.getTrainings(); }

    /**
     * Gets all competitions.
     * @return all competitions
     */
    @GetMapping("/competition")
    public List<Competition> getCompetitions() { return activityService.getCompetitions(); }

    /**
     * Gets all activities of the given user.
     * @param username
     * @return all activities of the given user
     */
    @GetMapping("/{username}")
    public List<Activity> getByUsername(@PathVariable("username") String username) throws ActivityNotFoundException {
        var activity = activityService.getByUsername(username);
        if (activity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
        return activity;
    }

    /**
     * Gets the owner of the activity.
     * @param id
     * @return the owner of the activity
     */
    @GetMapping("/user/{id}")
    public NetId getOwnerById(@PathVariable("id") long id) throws ActivityNotFoundException {
        return activityService.getById(id).getOwner();
    }

    /**
     * Gets activites of the logged in user.
     * @param
     * @return list of activities of the logged in user
     */
    @GetMapping("/user")
    public List<Activity> getByNetId() throws ActivityNotFoundException {
        return activityService.getByUsername(authManager.getNetId());
    }

    /**
     * Gets an activity by id.
     * @param id
     * @return the activity
     */
    @GetMapping("/activityId/{id}")
    public Activity getById(@PathVariable("id") long id) throws ActivityNotFoundException {
        return activityService.getById(id);
    }
}
