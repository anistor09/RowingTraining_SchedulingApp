package nl.tudelft.sem.template.example.domain;

public class ActivityNotFoundException extends Exception {
    private static final long serialVersionUID = 1;

    public ActivityNotFoundException(long id) {
        super("Activity with id " + id + " not found");
    }

    public ActivityNotFoundException(String s) {
        super(s);
    }
}

