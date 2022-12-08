package nl.tudelft.sem.template.example.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
public class ActivityId {
    private final transient String id;

    public ActivityId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return id;
    }
}
