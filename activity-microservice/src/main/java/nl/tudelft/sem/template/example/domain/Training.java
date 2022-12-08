package nl.tudelft.sem.template.example.domain;

import javax.persistence.Entity;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Training extends Activity{
    public Training() {
    }

    public Training(Username owner, LocalDateTime dateTime, String boat, List<String> positions) {
        super(owner, dateTime, boat, positions);
    }
}
