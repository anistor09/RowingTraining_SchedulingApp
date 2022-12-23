package nl.tudelft.sem.template.example.profiles;

import nl.tudelft.sem.template.example.domain.ActivityService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * A configuration profile to allow injection of a mock ActivityService.
 * A configuration can be used to define beans to be used during injection.
 * When this profile is active spring dependency injection will use this class to look for bean methods.
 * It will then prioritise these beans due to their @Primary tag.
 * In this case we return a mock to allow for more testability.
 * .
 * The profile tag will give a name to this configuration.
 * With the tag applied the profile will be inactive by default unless activated.
 * When the profile is active its bean will be used when looking for Beans to auto-inject.
 */
@Profile("mockActivityService")
@Configuration
public class MockActivityService {

    /**
     * Mocks the ActivityService.
     *
     * @return A mocked ActivityService.
     */
    @Bean
    @Primary
    public ActivityService getMockActivityService() {
        return Mockito.mock(ActivityService.class);
    }
}
