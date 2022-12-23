package nl.tudelft.sem.template.example.domain;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {
    @Test
    public void constructorsTest(){
        TimeSlot timeSlot = new TimeSlot("21-12-2012 17:33;29-12-2022 15:22");
        assertNotNull(timeSlot);
        assertNotNull(new TimeSlot());
    }
    @Test
    public void getTimeSlot(){
        TimeSlot timeSlot = new TimeSlot("21-12-2012 17:33;29-12-2022 15:22");


       assertEquals(timeSlot,TimeSlot.getTimeSlot("21-12-2012 17:33;29-12-2022 15:22"));
    }
    @Test
    public void getTimeSlots(){
        TimeSlot timeSlot = new TimeSlot("21-12-2012 17:33;29-12-2022 15:22");
        List<String> timeStrings = List.of("21-12-2012 17:33;29-12-2022 15:22");

        assertEquals(List.of(timeSlot),TimeSlot.getTimeSlots(timeStrings));
    }
    @Test
    public void toStringTest(){
        TimeSlot timeSlot = new TimeSlot("21-12-2012 17:33;29-12-2022 15:22");


        assertEquals(timeSlot.toString(),"21-12-2012 17:33;29-12-2022 15:22");
    }
   



}