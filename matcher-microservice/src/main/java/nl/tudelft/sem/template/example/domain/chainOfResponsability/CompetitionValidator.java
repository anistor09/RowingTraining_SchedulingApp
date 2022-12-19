package nl.tudelft.sem.template.example.domain.chainOfResponsability;

import nl.tudelft.sem.template.example.domain.*;
import nl.tudelft.sem.template.example.domain.transferObject.TransferMatch;

import java.util.List;

public class CompetitionValidator extends BaseValidator{
    @Override
    public boolean handle(Activity activity, String position, Participant participant, List<TimeSlot> timeslots) {
        if(activity instanceof Training)
            return super.checkNext(activity,position,participant,timeslots);
        Competition competition = (Competition) activity;
        if(!participant.getGender().equals(competition.getGender()) )
            return false;
        if(!participant.getOrganization().equals(competition.getOrganization()))
            return false;
        if(!participant.getLevel().equals(competition.getCompetitive()))
            return false;
        return super.checkNext(activity,position,participant,timeslots);

    }
}
