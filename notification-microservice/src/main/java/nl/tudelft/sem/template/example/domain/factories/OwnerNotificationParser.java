package nl.tudelft.sem.template.example.domain.factories;

import nl.tudelft.sem.template.example.domain.Notification;
import nl.tudelft.sem.template.example.domain.transferClasses.TransferMatch;

public class OwnerNotificationParser implements Parser{

    @Override
    public TransferMatch parse(Notification notification){
        return null;
    }

    @Override
    public Notification parseOtherWay(TransferMatch transferMatch){
        return null;
    }
}
