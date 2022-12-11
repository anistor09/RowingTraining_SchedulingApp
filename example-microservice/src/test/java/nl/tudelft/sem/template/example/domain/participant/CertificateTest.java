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

    @Test
    void isBetterCertificate() {
        Certificate certificate= new Certificate("C4");
        Certificate certificate1= new Certificate("4+");
        assertTrue(certificate1.isBetterCertificate(certificate));
    }

    @Test
    void isNotBetterCertificate() {
        Certificate certificate= new Certificate("C4");
        Certificate certificate1= new Certificate("4+");
        assertFalse(certificate.isBetterCertificate(certificate1));
    }
}