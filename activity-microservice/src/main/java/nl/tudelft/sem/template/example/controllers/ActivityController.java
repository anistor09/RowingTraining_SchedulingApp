package nl.tudelft.sem.template.example.controllers;

import nl.tudelft.sem.template.example.authentication.AuthManager;
import nl.tudelft.sem.template.example.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityController {
    private final transient AuthManager authManager;
    private final ActivityService activityService;

    public ActivityController(AuthManager authManager, ActivityService activityService) {
        this.authManager = authManager;
        this.activityService = activityService;
    }

    @PostMapping("/createCompetition")
    public ResponseEntity<Competition> createCompetition(@RequestBody ActivityRequestModel request) {
        Username username= new Username(authManager.getNetId());
        return ResponseEntity.ok(activityService.createCompetition(username, request));
    }

    @PostMapping("/createTraining")
    public ResponseEntity<Training> createTraining(@RequestBody ActivityRequestModel request) {
        Username username= new Username(authManager.getNetId());
        return ResponseEntity.ok(activityService.createTraining(username, request));
    }

    @GetMapping("/all")
    public List<Activity> getAll() {
        return activityService.getAll();
    }

    @GetMapping("/training")
    public List<Training> getTrainings() { return  activityService.getTrainings(); }

    @GetMapping("/competition")
    public List<Competition> getCompetitions() { return activityService.getCompetitions(); }

    @GetMapping("/{username}")
    public List<Activity> getByUsername(@PathVariable("username") String username) {
        var activity = activityService.getByUsername(username);
        if (activity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
        return activity;
    }
}
