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

    //TODO:
    // only authorized users can delete it - has to be updated
    @DeleteMapping("/deleteUser/{username}")
    public void deleteByUser(@PathVariable Username username) {
        activityService.deleteByUser(username);
    }

    //TODO:
    // only authorized users can delete it - has to be updated
    @DeleteMapping("deleteId/{id}")
    public void deleteById(@PathVariable Long id) {
        activityService.deleteById(id);
    }

    @PutMapping("/editPositions/{id}")
    public void editPositions(@PathVariable Long id, @RequestBody ActivityRequestModel request) {
        activityService.editPositions(id, request.getPositions());
    }

    @PutMapping("/editBoat/{id}")
    public void editBoat(@PathVariable Long id, @RequestBody ActivityRequestModel request) {
        activityService.editBoat(id, request.getBoat());
    }

    @PutMapping("/editTimeSlot/{id}")
    public void editDate(@PathVariable Long id, @RequestBody ActivityRequestModel request) {
        activityService.editTimeSlot(id, request.getTimeSlot());
    }

    @PostMapping("/createCompetition")
    public ResponseEntity<Competition> createCompetition(@RequestBody ActivityRequestModel request) {
        Username username = new Username(authManager.getNetId());
        return ResponseEntity.ok(activityService.createCompetition(username, request));
    }

    @PostMapping("/createTraining")
    public ResponseEntity<Training> createTraining(@RequestBody ActivityRequestModel request) {
        Username username = new Username(authManager.getNetId());
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
    public List<Activity> getByUsername(@PathVariable("username") String username) {
        var activity = activityService.getByUsername(username);
        if (activity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
        return activity;
    }

    @GetMapping("/user/{id}")
    public Username getOwnerById(@PathVariable("id") long id) {
        return activityService.getById(id).get().getOwner();
    }
}
