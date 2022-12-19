package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.domain.chainOfResponsability.*;
import nl.tudelft.sem.template.example.domain.transferObject.RequestMatch;
import nl.tudelft.sem.template.example.domain.transferObject.TransferMatch;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * A DDD service for registering a new user.
 */
@Service
public class MatcherService {
    private final transient MatcherRepository matcherRepository;



    public MatcherService(MatcherRepository matcherRepository) {
        this.matcherRepository = matcherRepository;
    }
//    public List<TransferMatch> computeMatch(RequestMatch rm){
//        List<TransferMatch> res = new ArrayList<>();
//        List<Activity> activities = getActivities();
//        Participant p = rm.getParticipant();
//        List<TimeSlot> timeSlots= TimeSlot.getTimeSlots(rm.getTimeSlots());
//        //Collections.sort(activities, Comparator.comparing(a -> a.getTimeSlot().end));
//        for(Activity activity : activities){
//            for(TimeSlot ts : timeSlots){
//                if(verifyTimeslots(ts,activity.getTimeSlot())){
//                    for(String position : p.getPositionManager().getPositions()){
//                        if(activity.getPositions().contains(position)){
//                            if(position.equals("cox") && !verifyCertificate(p.getCertificate(),activity.getBoat()))
//                                continue;
//
//                                if(activity instanceof  Competition && !isValidCompetition((Competition) activity,p))
//                                    continue;
//
//                                    res.add(new TransferMatch
//                                            (activity.getActivityName(),position,activity.getTimeSlot().toString(),p.getNetId().toString()));
//
//
//                        }
//                    }
//
//                }
//            }
//        }
//
//        return res;
//
//    }
    public List<TransferMatch> computeMatch(RequestMatch rm){
        List<TransferMatch> res = new ArrayList<>();
        List<Activity> activities = getActivities();
        Participant participant = rm.getParticipant();
        List<TimeSlot> timeSlots= TimeSlot.getTimeSlots(rm.getTimeSlots());

        Validator handler = setValidators();

        for(Activity activity : activities){
            for(String position : activity.getPositions()){
                if(handler.handle(activity,position,participant,timeSlots))
                    res.add(new TransferMatch(activity.getActivityName(),position,activity.getTimeSlot().toString(),
                            participant.getNetId().toString()));
            }
                    }
        return res;

    }
    private Validator setValidators(){
        Validator handler = new TimeSlotValidator();
        Validator positionValidator = new PositionValidator();
        Validator competitionValidator = new CompetitionValidator();
        Validator certificateValidator = new CertificateValidator();
        handler.setNext(positionValidator);
        positionValidator.setNext(competitionValidator);
        competitionValidator.setNext(certificateValidator);

        return handler;
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
//        activities.add(new Training
//                ("name",new NetId("owner"),new TimeSlot("22-12-2012 17:33;29-12-2022 15:22"),"C4",positions));
//        activities.add(new Competition("name2",new NetId("owner2"),new TimeSlot("25-12-2012 17:33;29-12-2022 15:22"),"C4",positions,"org","M","pro"));
        activities.add(new Competition("name3",new NetId("owner3"),new TimeSlot("26-12-2012 17:00;29-12-2022 16:00"),"C4",positions,"org","M","pro"));
        activities.add(new Training
                    ("name",new NetId("owner"),new TimeSlot("25-12-2012 18:05;29-12-2022 15:59"),"C4",positions));
            activities.add(new Training
                    ("name2",new NetId("owner2"),new TimeSlot("25-12-2012 18:35;29-12-2022 15:59"),"C4",positions));
        return activities;
    }


    public void saveMatch(Match m) {
        matcherRepository.save(m);
    }
    public List<Match> getAllMatches(){
        return matcherRepository.findAll();
    }

}
