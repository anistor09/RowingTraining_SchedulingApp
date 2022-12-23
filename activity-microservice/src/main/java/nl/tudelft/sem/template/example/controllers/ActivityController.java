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

    public ActivityController(AuthManager authManager, ActivityService activityService) {
        this.authManager = authManager;
        this.activityService = activityService;
    }

    @DeleteMapping("/deleteUser/{username}")
    public void deleteByUser(@PathVariable String username) throws UnauthorizedException, ActivityNotFoundException {
        NetId logged = new NetId(authManager.getNetId());
        activityService.deleteByUser(new NetId(username), logged);
    }


    @DeleteMapping("/deleteId/{id}")
    public void deleteById(@PathVariable int id) throws UnauthorizedException, ActivityNotFoundException {
        NetId username = new NetId(authManager.getNetId());
        activityService.deleteById(username, id);
    }


    @PutMapping("/edit/{id}")
    public void editActivity(@PathVariable long id, @RequestBody ActivityRequestModel request) throws UnauthorizedException, ActivityNotFoundException {
        NetId username = new NetId(authManager.getNetId());
        activityService.editActivity(username, id, request);
    }

    @PostMapping("/createCompetition")
    public ResponseEntity<Competition> createCompetition(@RequestBody ActivityRequestModel request) {
        NetId username = new NetId(authManager.getNetId());
        return ResponseEntity.ok(activityService.createCompetition(username, request));
    }

    @PostMapping("/createTraining")
    public ResponseEntity<Training> createTraining(@RequestBody ActivityRequestModel request) {
        NetId username = new NetId(authManager.getNetId());
        return ResponseEntity.ok(activityService.createTraining(username, request));
    }

    @GetMapping("/all")
    public List<Activity> getAll() {
        return activityService.getAll();
    }

    @GetMapping("/training")
    public List<Training> getTrainings() { return activityService.getTrainings(); }

    @GetMapping("/competition")
    public List<Competition> getCompetitions() { return activityService.getCompetitions(); }

    @GetMapping("/{username}")
    public List<Activity> getByUsername(@PathVariable("username") String username) throws ActivityNotFoundException {
        var activity = activityService.getByUsername(username);
        if (activity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
        return activity;
    }

    @GetMapping("/user/{id}")
    public NetId getOwnerById(@PathVariable("id") long id) throws ActivityNotFoundException {
        return activityService.getById(id).getOwner();
    }

    @GetMapping("/user")
    public List<Activity> getByNetId() throws ActivityNotFoundException {
        return activityService.getByUsername(authManager.getNetId());
    }

    @GetMapping("/activityId/{id}")
    public Activity getById(@PathVariable("id") long id) throws ActivityNotFoundException {
        return activityService.getById(id);
    }
}
