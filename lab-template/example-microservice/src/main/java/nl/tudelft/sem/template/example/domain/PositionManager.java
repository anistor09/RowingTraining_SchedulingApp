package nl.tudelft.sem.template.example.domain.participant;

import lombok.EqualsAndHashCode;

/**
 * Positions that the participant is able to fill
 */
@EqualsAndHashCode
public class PositionManager {

    private boolean cox;
    private boolean coach;
    private boolean portSideRower;
    private boolean starboardSideRower;
    private boolean scullingRower;
    private final transient PositionCreator pc;

    public PositionManager(String positions){
        this.cox=false;
        this.coach=false;
        this.portSideRower=false;
        this.starboardSideRower=false;
        this.scullingRower=false;
        pc = new PositionCreator();
        pc.createPositions(positions,this);
    }



    public void setCox(boolean cox) {
        this.cox = cox;
    }

    public void setCoach(boolean coach) {
        this.coach = coach;
    }

    public void setPortSideRower(boolean portSideRower) {
        this.portSideRower = portSideRower;
    }

    public void setStarboardSideRower(boolean starboardSideRower) {
        this.starboardSideRower = starboardSideRower;
    }

    public void setScullingRower(boolean scullingRower) {
        this.scullingRower = scullingRower;
    }

    public boolean isCox(){
        return this.cox;
    }

    public boolean isCoach(){
        return this.coach;
    }

    public boolean isPortSideRower(){
        return this.portSideRower;
    }

    public boolean isStarboardSideRower(){
        return this.starboardSideRower;
    }

    public boolean isScullingRower(){
        return this.scullingRower;
    }
}
