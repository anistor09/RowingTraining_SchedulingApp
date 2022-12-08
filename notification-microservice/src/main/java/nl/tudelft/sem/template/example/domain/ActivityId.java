package nl.tudelft.sem.template.example.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ActivityId {
    private final transient String id;

    public ActivityId(String id){
        this.id = id;
    }

    @Override
    public String toString(){
        return id;
    }
}
