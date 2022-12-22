package nl.tudelft.sem.template.example.controllers;

import java.util.List;
import java.util.Optional;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.authentication.JwtTokenVerifier;
import nl.tudelft.sem.template.example.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("activity")
public class ActivityController {
    private final transient AuthManager authManager;
    private final transient ActivityService activityService;

    /**
     * Constructor for ActivityController.
     * @param authManager the authentication manager
     * @param activityService the service for activities
     */
    public ActivityController(AuthManager authManager, ActivityService activityService) {
        this.authManager = authManager;
        this.activityService = activityService;
    }

    /**
     * Deletes activities of the given user.
     * @param netId
     * @throws UnauthorizedException
     */
    @DeleteMapping("/deleteUser/{username}")
    public void deleteByUser(@PathVariable NetId netId) throws UnauthorizedException, ActivityNotFoundException {
        NetId logged = new NetId(authManager.getNetId());
        activityService.deleteByUser(netId, logged);
    }

    /**
     * Deletes an activity by id.
     * @param id
     * @throws UnauthorizedException
     */
    @DeleteMapping("/deleteId/{id}")
    public void deleteById(@PathVariable int id) throws UnauthorizedException, ActivityNotFoundException {
        NetId username = new NetId(authManager.getNetId());
        activityService.deleteById(username, id);
    }

    /**
     * Edits an activity.
     * @param id
     * @param request
     * @throws UnauthorizedException
     */
    @PutMapping("/edit/{id}")
    public void editActivity(@PathVariable int id, @RequestBody ActivityRequestModel request) throws UnauthorizedException, ActivityNotFoundException {
        NetId username = new NetId(authManager.getNetId());
        activityService.editActivity(username, id, request);
    }

    /**
     * Creates a competition.
     * @param request
     * @return
     */
    @PostMapping("/createCompetition")
    public ResponseEntity<Competition> createCompetition(@RequestBody ActivityRequestModel request) {
        NetId username = new NetId(authManager.getNetId());
        return ResponseEntity.ok(activityService.createCompetition(username, request));
    }

    /**
     * Creates a training.
     * @param request
     * @return
     */
    @PostMapping("/createTraining")
    public ResponseEntity<Training> createTraining(@RequestBody ActivityRequestModel request) {
        NetId username = new NetId(authManager.getNetId());
        return ResponseEntity.ok(activityService.createTraining(username, request));
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
    public NetId getOwnerById(@PathVariable("id") int id) throws ActivityNotFoundException {
        return activityService.getById(id).getOwner();
    }

    /**
     * Gets activites of the logged in user.
     * @param token
     * @return list of activities of the logged in user
     */
    @GetMapping("/user")
    public List<Activity> getByNetId(UsernamePasswordAuthenticationToken token) throws ActivityNotFoundException {
        return activityService.getByUsername(token.getName());
    }

    /**
     * Gets an activity by id.
     * @param id
     * @return the activity
     */
    @GetMapping("/activityId/{id}")
    public Activity getById(@PathVariable("id") int id) throws ActivityNotFoundException {
        return activityService.getById(id);
    }
}
