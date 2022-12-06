package nl.tudelft.sem.template.example.domain.participant;

import nl.tudelft.sem.template.example.domain.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * repository for participants
 */
@Repository
public interface ParticipantRepository extends JpaRepository<Participant, String> {

    /**
     * Find user by NetID.
     */
    //Optional<Participant> findByUsername(Username username);

    /**
     * checks if there is already an user with this name
     */
    //boolean existsByUsername(Username username);

}
