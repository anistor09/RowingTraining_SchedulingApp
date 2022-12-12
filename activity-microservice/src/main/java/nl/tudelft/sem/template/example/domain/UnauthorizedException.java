package nl.tudelft.sem.template.example.domain;

public class UnauthorizedException extends Throwable {
    public UnauthorizedException(String s) {
        super(s);
    }
}
