package nl.tudelft.sem.template.example.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NetIdTest {

    @Test
    void testToString() {
        String s="netId";
        NetId netId= new NetId(s);
        assertTrue(netId.toString().equals(s));
    }
}