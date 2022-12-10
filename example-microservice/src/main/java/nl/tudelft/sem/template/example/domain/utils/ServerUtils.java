package nl.tudelft.sem.template.example.domain.utils;

import jakarta.ws.rs.core.GenericType;
import nl.tudelft.sem.template.example.domain.transferClasses.RequestMatch;
import nl.tudelft.sem.template.example.domain.transferClasses.TransferMatch;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import org.jvnet.hk2.annotations.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
@Component
public class ServerUtils {
    transient String MATCHER_SERVER = new String("http://localhost:8083/");

    public List<TransferMatch> sendRequestMatch(RequestMatch rm){
        try{
            List<TransferMatch> res = new ResteasyClientBuilder().build()
                    .target(MATCHER_SERVER).path("requestMatch")
                    .request(APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials())
                    .accept(APPLICATION_JSON)
                    .post(Entity.entity(rm,APPLICATION_JSON),List.class);
            return res;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}