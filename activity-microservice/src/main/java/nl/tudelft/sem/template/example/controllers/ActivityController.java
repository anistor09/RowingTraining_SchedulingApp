package nl.tudelft.sem.template.example.controllers;

import java.util.List;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
     * @param username
     * @throws UnauthorizedException
     */
    @DeleteMapping("/deleteUser/{username}")
    public void deleteByUser(@PathVariable String username) throws UnauthorizedException, ActivityNotFoundException {
        NetId logged = new NetId(authManager.getNetId());
        activityService.deleteByUser(new NetId(username), logged);
    }

    /**
     * Deletes an activity by id.
     * @param id
     * @throws UnauthorizedException
     */
    @DeleteMapping("/deleteId/{id}")
    public void deleteById(@PathVariable long id) throws UnauthorizedException, ActivityNotFoundException {
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
    public void editActivity(@PathVariable long id, @RequestBody ActivityRequestModel request) throws UnauthorizedException, ActivityNotFoundException {
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
}
