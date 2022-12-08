package nl.tudelft.sem.template.example.domain;

import nl.tudelft.sem.template.example.domain.participant.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
    /**
     * Find user by NetID.
     */
    Optional<Notification> findByActivityID(ActivityId activityId);
    /**
     * Check if an existing user already uses a NetID.
     */
    boolean existsByActivityID(ActivityId activityId);
}
