package nl.tudelft.sem.template.example.domain;

public class NetId {
    private final transient String id;

    public NetId(String id){
        this.id = id;
    }

    public String toString(){
        return id;
    }
}
