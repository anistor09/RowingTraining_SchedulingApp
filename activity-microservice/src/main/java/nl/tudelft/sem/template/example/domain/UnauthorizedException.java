package nl.tudelft.sem.template.example.domain;

public class UnauthorizedException extends Exception {
    private static final long serialVersionUID = 1;


    public UnauthorizedException(String s) {
        super(s);
    }
}
