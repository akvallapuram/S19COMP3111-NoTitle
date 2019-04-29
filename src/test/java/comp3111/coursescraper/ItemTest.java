package comp3111.coursescraper;


import org.junit.Test;

import comp3111.coursescraper.Course;

import javax.accessibility.AccessibleStateSet;

import comp3111.coursescraper.*;

import static org.junit.Assert.*;


public class ItemTest {

    @Test
    public void testSetCourseTitle() {
        Course i = new Course();
        i.setTitle("ABCDE");
        assertEquals(i.getTitle(), "ABCDE");
    }

    @Test
    public void testCourseSetDescription() {
        Course i = new Course();
        i.setDescription("This is the description");
        assertEquals(i.getDescription(), "This is the description");
    }

    @Test
    public void testCourseSetExclusion() {
        Course i = new Course();
        i.setExclusion("This is the exclusion course");
        assertEquals(i.getExclusion(), "This is the exclusion course");
    }

    @Test
    public void testSlotTime() {
        Slot s = new Slot();
        s.setStart("02:00AM");
        assertEquals(s.getStartHour(), 2);
    }

    @Test
    public void testCourseAddSlot() {
        Slot s = new Slot();
        s.setStart("02:00AM");
        s.setEnd("03:00AM");
        s.setVenue("CYT");
        Course i = new Course();
        i.addSlot(s);
        assertEquals(i.getSlot(0).getStartHour(), 2);
        assertEquals(i.getSlot(0).getEndHour(), 3);
        assertEquals("CYT", s.getVenue());
    }



    @Test
    public void testCourseNumSlots() {
        Course i = new Course();
        i.setNumSlots(10);
        assertEquals(i.getNumSlots(), 10);

    }



}
