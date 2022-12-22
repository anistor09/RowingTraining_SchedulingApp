package nl.tudelft.sem.template.example.domain;

public class ActivityNotFoundException extends RuntimeException {
    public ActivityNotFoundException(Long id) {
        super("Could not find activity with id: " + id);
    }

    public ActivityNotFoundException(String s) {
        super(s);
    }
}
