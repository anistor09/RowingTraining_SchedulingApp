package nl.tudelft.sem.template.example.domain.factories;

import nl.tudelft.sem.template.example.domain.ActivityId;
import nl.tudelft.sem.template.example.domain.NetId;
import nl.tudelft.sem.template.example.domain.Notification;
import nl.tudelft.sem.template.example.domain.transferClasses.TransferMatch;

public class OwnerNotificationParser implements Parser{

    @Override
    public TransferMatch parse(Notification notification){
        Long activityId = Long.parseLong(notification.getActivityId().toString());
        String position = notification.getPosition();
        String timeSlot = notification.getTimeSlot();
        String netId = notification.getNetId().toString();
        String owner = notification.getOwnerId().toString();
        return new TransferMatch(activityId, position, timeSlot, netId, owner);
    }

    @Override
    public Notification parseOtherWay(TransferMatch transferMatch){
        ActivityId activityId = new ActivityId(transferMatch.getActivityId().toString());
        NetId netId = new NetId(transferMatch.getNetId().toString());
        NetId ownerId = new NetId(transferMatch.getOwner().toString());
        String message = new String("The user " + netId + " would like to participate in this activity:"
        + activityId + ", on position:" + transferMatch.getPosition() + ", on " + transferMatch.getTimeSlot());
        return new Notification(activityId, netId, ownerId, message, true);
    }
}
