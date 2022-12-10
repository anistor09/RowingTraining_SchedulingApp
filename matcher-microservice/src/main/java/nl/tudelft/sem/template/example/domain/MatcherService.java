package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.domain.transferObject.RequestMatch;
import nl.tudelft.sem.template.example.domain.transferObject.TransferMatch;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;

/**
 * A DDD service for registering a new user.
 */
@Service
public class MatcherService {
    private final transient MatcherRepository matcherRepository;



    public MatcherService(MatcherRepository matcherRepository) {
        this.matcherRepository = matcherRepository;
    }
    public List<TransferMatch> computeMatch(RequestMatch rm){
        List<TransferMatch> res = new ArrayList<>();
        List<Activity> activities = new ArrayList<>();
        Participant p = rm.getParticipant();
        List<TimeSlot> timeSlots= TimeSlot.getTimeSlots(rm.getTimeSlots());
        //Collections.sort(activities, Comparator.comparing(a -> a.getTimeSlot().end));
        for(Activity activity : activities){
            for(TimeSlot ts : timeSlots){
                if(verifyTimeslots(ts,activity.getTimeSlot())){
                    for(String position : p.getPositionManager().getPositions()){
                        if(activity.getPositions().contains(position)){
                            if(position=="cox" && !verifyCertificate(p.getCertificate(),activity.getBoat()))
                                continue;
                            else{
                                if(activity instanceof  Competition && !isValidCompetition((Competition) activity,p))
                                    continue;

                                    res.add(new TransferMatch
                                            (activity.getActivityName(),position,activity.getTimeSlot().toString(),p.getNetId().toString()));

                            }

                        }
                    }

                }
            }
        }

        return res;

    }
    private boolean isValidCompetition(Competition comp, Participant p){
        if(!p.getGender().equals(comp.getGender()) )
            return false;
        if(!p.getOrganization().equals(comp.getOrganization()))
            return false;
        if(!p.getLevel().equals(comp.getCompetitive()))
            return false;
        return true;
    }


    private boolean verifyCertificate(Certificate certificate, String boat) {
        Certificate boatCertificate = new Certificate(boat);
        if(boatCertificate.isValid())
            return certificate.isBetterCertificate(boatCertificate);
        return false;

    }

    public boolean verifyTimeslots(TimeSlot ts, TimeSlot activityTs){
        if((ts.begin.after(activityTs.begin)||ts.begin.equals(activityTs.begin))
                && (ts.end.before(activityTs.end) ||ts.end.equals(activityTs.end)))
            return true;
        return false;

    }




}
