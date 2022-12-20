package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.domain.chainOfResponsability.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.tudelft.sem.template.example.domain.transferObject.RequestMatch;
import nl.tudelft.sem.template.example.domain.transferObject.TransferMatch;
import nl.tudelft.sem.template.example.domain.utils.ServerUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * A DDD service for registering a new user.
 */
@Service
public class MatcherService {
    private final transient MatcherRepository matcherRepository;
    private final transient ServerUtils serverUtils;


    public MatcherService(MatcherRepository matcherRepository, ServerUtils serverUtils) {
        this.matcherRepository = matcherRepository;
        this.serverUtils = serverUtils;
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
    transient Validator handler;
    transient List<TimeSlot> timeSlots;

    public List<TransferMatch> computeMatch(RequestMatch rm) {
        List<TransferMatch> res = new ArrayList<>();
        handler = setValidators();
        timeSlots = TimeSlot.getTimeSlots(rm.getTimeSlots());
        for (Activity activity : getActivities()) {
            for (String position : activity.getPositions()) {
                if (handler.handle(activity, position, rm.getParticipant(), timeSlots))
                    res.add(new TransferMatch(activity.getId().toString(), position, activity.getTimeSlot().toString(),
                            rm.getParticipant().getNetId().toString()));
            }
        }
        return res;

    }

    private Validator setValidators() {
        Validator handler = new TimeSlotValidator();
        Validator positionValidator = new PositionValidator();
        Validator competitionValidator = new CompetitionValidator();
        Validator certificateValidator = new CertificateValidator();
        handler.setNext(positionValidator);
        positionValidator.setNext(competitionValidator);
        competitionValidator.setNext(certificateValidator);

        return handler;
    }

    //    private boolean isValidCompetition(Competition comp, Participant p){
//        if(!p.getGender().equals(comp.getGender()) )
//            return false;
//        if(!p.getOrganization().equals(comp.getOrganization()))
//            return false;
//        if(!p.getLevel().equals(comp.getCompetitive()))
//            return false;
//        return true;
//    }
//
//
//    private boolean verifyCertificate(Certificate certificate, String boat) {
//        Certificate boatCertificate = new Certificate(boat);
//        if(boatCertificate.isValid())
//            return certificate.isBetterCertificate(boatCertificate);
//        return false;
//
//    }
//
//    public boolean verifyTimeslots(TimeSlot ts, TimeSlot activityTs){
//        if((ts.begin.before(activityTs.begin)||ts.begin.equals(activityTs.begin))
//                && (ts.end.after(activityTs.end) ||ts.end.equals(activityTs.end)))
//            return true;
//        return false;
//
//    }
    public List<Activity> getActivities() {
        List<Activity> activities = new ArrayList<>();
        activities.addAll(getTrainings());
        activities.addAll(getCompetitions());
        return activities;
    }

    public List<Training> getTrainings() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(serverUtils.getTrainings(), new TypeReference<List<Training>>() { });
    }

    public List<Competition> getCompetitions() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(serverUtils.getCompetitions(), new TypeReference<List<Competition>>() { });
    }


    public void saveMatch(Match m) {
        matcherRepository.save(m);
    }

    public List<Match> getAllMatches() {
        return matcherRepository.findAll();
    }

    transient List<Match> matches;

    public void removeMatches(List<TransferMatch> acceptedMatches) {
        matches= getAllMatches();
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

}
