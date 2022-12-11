package nl.tudelft.sem.template.example.domain.participant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CertificateTest {

    @Test
    void isValid() {
        Certificate certificate= new Certificate("C4");
        assertTrue(certificate.isValid());
    }

    @Test
    void NotValid() {
        Certificate certificate= new Certificate("C1");
        assertFalse(certificate.isValid());
    }
}