package nl.tudelft.sem.template.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
        Optional<Activity> findById(int id);
        Boolean existsById(int id);

        void deleteById(int id);
}
