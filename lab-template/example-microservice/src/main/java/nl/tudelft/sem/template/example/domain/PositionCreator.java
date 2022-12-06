package nl.tudelft.sem.template.example.domain.participant;

public class PositionCreator implements PositionInterface{
    @Override
    public void createPositions(String positions, PositionManager pm){

        if(positions.contains("cox"))
            pm.setCox(true);

        if(positions.contains("coach"))
            pm.setCoach(true);

        if(positions.contains("port side rower"))
            pm.setPortSideRower(true);

        if(positions.contains("starboard side rower"))
            pm.setStarboardSideRower(true);

        if(positions.contains("sculling rower"))
            pm.setScullingRower(true);
    }

}
