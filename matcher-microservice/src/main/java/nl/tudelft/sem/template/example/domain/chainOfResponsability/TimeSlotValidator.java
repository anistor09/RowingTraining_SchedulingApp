package nl.tudelft.sem.template.example.domain.chainOfResponsability;

import nl.tudelft.sem.template.example.domain.Activity;
import nl.tudelft.sem.template.example.domain.Competition;
import nl.tudelft.sem.template.example.domain.Participant;
import nl.tudelft.sem.template.example.domain.TimeSlot;
import org.h2.util.DateTimeUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeSlotValidator extends BaseValidator{
    transient Date startActivity;
    transient TimeSlot activityTime;

    @Override
    public boolean handle(Activity activity, String position, Participant participant, List<TimeSlot> timeslots) {

         startActivity = adjustStartTime(activity.getTimeSlot().getBegin(),activity instanceof Competition);
         activityTime = activity.getTimeSlot();
        for(TimeSlot participantTime : timeslots)
        if((participantTime.getBegin().before(startActivity)
                ||participantTime.getBegin().equals(startActivity))
                && (participantTime.getEnd().after(activityTime.getEnd())
                ||participantTime.getEnd().equals(activityTime.getEnd())))
            return super.checkNext(activity,position,participant,timeslots);

        return false;


    }
    public Date adjustStartTime(Date startTime, boolean isCompetition){
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);

        if(isCompetition)
        cal.add(Calendar.HOUR_OF_DAY, -24);
        else
        cal.add(Calendar.MINUTE,-30);
        return cal.getTime();
    }
}
