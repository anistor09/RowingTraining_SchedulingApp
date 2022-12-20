package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.domain.transferObject.OwnerNotification;
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
        List<Activity> activities = getActivities();
        Participant p = rm.getParticipant();
        List<TimeSlot> timeSlots= TimeSlot.getTimeSlots(rm.getTimeSlots());
        //Collections.sort(activities, Comparator.comparing(a -> a.getTimeSlot().end));
        for(Activity activity : activities){
            for(TimeSlot ts : timeSlots){
                if(verifyTimeslots(ts,activity.getTimeSlot())){
                    for(String position : p.getPositionManager().getPositions()){
                        if(activity.getPositions().contains(position)){
                            if(position.equals("cox") && !verifyCertificate(p.getCertificate(),activity.getBoat()))
                                continue;

                                if(activity instanceof  Competition && !isValidCompetition((Competition) activity,p))
                                    continue;

                                    res.add(new TransferMatch
                                            (activity.getActivityName(),position,activity.getTimeSlot().toString(),p.getNetId().toString()));


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
        if((ts.begin.before(activityTs.begin)||ts.begin.equals(activityTs.begin))
                && (ts.end.after(activityTs.end) ||ts.end.equals(activityTs.end)))
            return true;
        return false;

    }
        public List<Activity> getActivities(){
        List<Activity> activities= new ArrayList<>();
        List<String> positions= new ArrayList<>();
        positions.add("cox");
        positions.add("coach");
        positions.add("sculling rower");
        activities.add(new Training
                ("name",new NetId("owner"),new TimeSlot("22-12-2012 17:33;29-12-2022 15:22"),"C4",positions));
        activities.add(new Competition("name2",new NetId("owner2"),new TimeSlot("25-12-2012 17:33;29-12-2022 15:22"),"C4",positions,"org","M","pro"));
        return activities;
    }


    public void saveMatch(Match m) {
        matcherRepository.save(m);
    }
    public List<Match> getAllMatches(){
        return matcherRepository.findAll();
    }

    public List<OwnerNotification> getAllOwnerNotifications() {
        List<Activity> activities;
        activities= getActivities();
        List<Match> matches= getAllMatches();
        List<OwnerNotification> ownerNotificationList= new ArrayList<>();

        for(Match match: matches){
            if(findOwner(activities,match.getActivityName())!=null){
                String owner = findOwner(activities,match.getActivityName());
                OwnerNotification notification= new OwnerNotification(match.getNetId(),match.getPosition(),match.getActivityName(),owner);
                ownerNotificationList.add(notification);
            }
        }

        return ownerNotificationList;
    }

    public String findOwner(List<Activity> activities, String matchActivity){
        for (Activity activity : activities){
            if(activity.getActivityName().equals(matchActivity))
                return activity.getOwner().toString();
        }
        return null;
    }

    public void removeMatches(List<TransferMatch> acceptedMatches) {
        List<Match> matches= getAllMatches();
        for(TransferMatch transferMatch: acceptedMatches){
            List<Match> toBeDeleted= findMatch(transferMatch,matches);
            for(Match m: toBeDeleted)
                deleteMatch(m);
        }
    }

    public List<Match> findMatch(TransferMatch tr,List<Match> matches){
        List<Match> toDeletMatches= new ArrayList<>();
        for(Match m : matches){
            if(m.getNetId().equals(tr.getNetId()))
                if(!toDeletMatches.contains(m))
                    toDeletMatches.add(m);
        }
        return toDeletMatches;
    }

    public void deleteMatch(Match m){
        matcherRepository.delete(m);
    }

    public OwnerNotification getOwnerNotification(TransferMatch tm) {
        List<Activity> activities = getActivities();
        String owner = findOwner(activities,tm.getActivityName());
        OwnerNotification ownerNotification= new OwnerNotification(tm.getNetId(),tm.getPosition(),tm.getActivityName(),owner);
        return ownerNotification;
    }
}
