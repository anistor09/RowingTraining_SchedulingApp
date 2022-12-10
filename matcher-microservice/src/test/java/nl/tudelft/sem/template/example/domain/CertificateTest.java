package nl.tudelft.sem.template.example.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CertificateTest {

    @Test
    void isBetterCertificate() {
        Certificate certificate= new Certificate("8+");
        Certificate certificate1= new Certificate("4+");
        assertFalse(certificate1.isBetterCertificate(certificate));
    }
}