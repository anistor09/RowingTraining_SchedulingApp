package nl.tudelft.sem.template.example.utils;



import nl.tudelft.sem.template.example.domain.transferObject.OwnerNotification;
import nl.tudelft.sem.template.example.domain.transferObject.TransferMatch;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Component
public class ServerUtils {

    transient String MATCHER_SERVER = new String("http://localhost:8085/");
    
    public OwnerNotification sendPendingUser(OwnerNotification on){
        try{
            OwnerNotification ownerNotification= new ResteasyClientBuilder().build()
                    .target(MATCHER_SERVER).path("sendPendingUser")
                    .request(APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + SecurityContextHolder.getContext().getAuthentication().getCredentials())
                    .accept(APPLICATION_JSON)
                    .post(Entity.entity(on,APPLICATION_JSON), OwnerNotification.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
