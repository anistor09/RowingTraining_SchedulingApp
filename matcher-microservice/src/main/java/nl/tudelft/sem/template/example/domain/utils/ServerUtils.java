package nl.tudelft.sem.template.example.domain.utils;

import nl.tudelft.sem.template.example.domain.Activity;
import nl.tudelft.sem.template.example.domain.Competition;
import nl.tudelft.sem.template.example.domain.Training;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
public class ServerUtils {
    transient String ACTIVITY_SERVER = new String("http://localhost:8084/activity/");

    public List<Activity> getActivities(){
        try {
            return new ResteasyClientBuilder().build()
                    .target(ACTIVITY_SERVER).path("all")
                    .request(APPLICATION_JSON)
                    .header(javax.ws.rs.core.HttpHeaders.AUTHORIZATION, "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials())
                    .accept(APPLICATION_JSON)
                    .get(List.class);
        } catch(Exception e){
            System.out.println("Activities not found");
        }
        return new ArrayList<>();
    }

    public List<Training> getTrainings() {
        try {
            return new ResteasyClientBuilder().build()
                    .target(ACTIVITY_SERVER).path("training")
                    .request(APPLICATION_JSON)
                    .header(javax.ws.rs.core.HttpHeaders.AUTHORIZATION, "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials())
                    .accept(APPLICATION_JSON)
                    .get(List.class);
        } catch(Exception e){
            System.out.println("Trainings not found");
        }
        return new ArrayList<>();
    }

    public List<Competition> getCompetitions() {
        try {
            return new ResteasyClientBuilder().build()
                    .target(ACTIVITY_SERVER).path("competition")
                    .request(APPLICATION_JSON)
                    .header(javax.ws.rs.core.HttpHeaders.AUTHORIZATION, "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials())
                    .accept(APPLICATION_JSON)
                    .get(List.class);
        } catch(Exception e){
            System.out.println("Competitions not found");
        }
        return new ArrayList<>();
    }

}