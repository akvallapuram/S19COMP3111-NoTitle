package comp3111.coursescraper;


import org.junit.Test;


import comp3111.coursescraper.Course;

import static org.junit.Assert.*;

import comp3111.coursescraper.Course;
import comp3111.coursescraper.Controller;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.CheckBox;

import javax.accessibility.AccessibleStateSet;

import comp3111.coursescraper.*;

import static org.junit.Assert.*;

import comp3111.coursescraper.*;
import comp3111.coursescraper.TableClass;
import java.util.Random;

import javafx.stage.Stage;
import java.lang.*;



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
		i.setExclusion("This is the exclusion");
		assertEquals(i.getExclusion(), "This is the exclusion");
	}

	@Test
	public void testSlotTime() {
		Slot s = new Slot();
		s.setStart("02:00AM");
		assertEquals(s.getStartHour(), 2);
	}

	@Test
	public void testCourseAddSlot(){
		Slot s = new Slot();
		s.setStart("02:00AM");
		Course i = new Course();
		i.addSlot(s);
		assertEquals(i.getSlot(0).getStartHour(), 2);
	}

	@Test
	public void testCourseGetSlotOutOfBounds(){
		Course i = new Course();
		assertEquals(i.getSlot(21), null);
	}

	@Test
	public void testCourseNumSlots() {
		Course i = new Course();
		i.setNumSlots(10);
		assertEquals(i.getNumSlots(), 10);

	}

	@Test
	public void testSetCcode()
	{
		//Color cr = new Color(Math.random(), Math.random(), Math.random(), 0.5);
		//Color cr = Color.rgb(0, 0, 0, 0.5);
		//TableClass tab = new TableClass(" ", " ", " ", " ", cr, 1);
		TableClass tab = new TableClass();
		tab.setCcode("ABC");
		assertEquals(tab.getCcode(), "ABC");
		//assertEquals("ABC", "ABC");
	}

	@Test
	public void testSetLecturesec()
	{
		//Color cr = new Color(Math.random(), Math.random(), Math.random(), 0.5);
		//Color cr = Color.rgb(0, 0, 0, 0.5);
		//TableClass tab = new TableClass(" ", " ", " ", " ", cr, 1);
		TableClass tab = new TableClass();
		tab.setLecturesec("ABC");
		assertEquals(tab.getLecturesec(), "ABC");
	}

	@Test
	public void testSetCname()
	{
		//Color cr = new Color(Math.random(), Math.random(), Math.random(), 0.5);
		Color cr = Color.rgb(0, 0, 0, 0.5);
		//TableClass tab = new TableClass(" ", " ", " ", " ", cr, 1);
		TableClass tab = new TableClass();
		tab.setCname("ABC");
		assertEquals(tab.getCname(), "ABC");
	}

	@Test
	public void testSetInstructor()
	{
		Color cr = Color.rgb(0, 0, 0, 0.5);
		//TableClass tab = new TableClass(" ", " ", " ", " ", cr, 1);
		TableClass tab = new TableClass();
		tab.setInstructor("ABC");
		String a = tab.getInstructor();
		assertTrue(a.equals("ABC"));
		//assertEquals(tab.getInstructor(), "ABC");
	}

	/*@Test
	public void testSetEnroll()
	{
		//Color cr = new Color(Math.random(), Math.random(), Math.random(), 0.5);
		Color cr = Color.rgb(0, 0, 0, 0.5);
		//TableClass tab = new TableClass(" ", " ", " ", " ", cr, 1);
		TableClass tab = new TableClass();
		CheckBox chk = new CheckBox();
		tab.setEnroll(chk);
		assertEquals(tab.getEnroll().isSelected(), chk.isSelected());
	}*/

	@Test
	public void testSetColorr()
	{
		//Color cr = new Color(Math.random(), Math.random(), Math.random(), 0.5);
		TableClass tab = new TableClass();
		Color cl = Color.rgb(0, 0, 0, 0);
		tab.setColorr(cl);
		assertEquals(tab.getColorr(), cl); //Not sure
	}

	/*@Test
	public void testSetLab()
	{
		Color cr = new Color(Math.random(), Math.random(), Math.random(), 0.5);
		TableClass tab = new TableClass(" ", " ", " ", " ", cr, 1);
		Label lb = new Label();
		tab.setLab(lb);
		assertEquals(tab.getLab(), lb);
	}*/

	@Test
	public void testSetTday()
	{
		//Color cr = new Color(Math.random(), Math.random(), Math.random(), 0.5);
		Color cr = Color.rgb(0, 0, 0, 0.5);
		//TableClass tab = new TableClass(" ", " ", " ", " ", cr, 1);
		TableClass tab = new TableClass();
		tab.setTday(1);
		assertEquals(tab.getTday(), 1);
	}

	/*@Test
	public void testBlocks()
	{
		TableClass tb = new TableClass();
		Slot st = new Slot();
		st.setStart("9:00AM");
		st.setEnd("10:00AM");
		blocks(tb, st);
	}*/

	@Test
	public void testSectionClass(){
		Section testSection = new Section("COMP 3111 L1", 1232);

		// assertions at initialisation
		String sc = testSection.getSectionCode();
		int sid = testSection.getSectionID();
		int numSlots = testSection.getNumSlots();
		String info = testSection.toString();
		assertTrue(sc.equals("COMP 3111 L1"));
		assertEquals(sid, 1232);
		assertEquals(numSlots, 0);
		assertEquals(testSection.getSlot(0), null);
		assertTrue(info.contains("(1232)"));

		// adding a slot
		Slot s = new Slot();
		s.setStart("09:30AM");
		testSection.addSlot(s);
		assertEquals(testSection.getNumSlots(), 1);
		assertEquals(testSection.getSlot(0).getStartHour(), 9);
	}

	@Test
	public void testInstructorClass(){

		// SFQ instructor construction
		Instructor testInsSFQ = new Instructor("testIns");
		assertTrue(testInsSFQ.getName().equals("testIns"));
		assertEquals((int)testInsSFQ.getScoreSFQ(), 0);
		assertTrue(testInsSFQ.isFreeTu310());

		// adding SFQ score
		testInsSFQ.addToScoreSFQ(-78);
		assertEquals((int)testInsSFQ.getScoreSFQ(), 0);
		testInsSFQ.addToScoreSFQ(1000);
		assertEquals((int)testInsSFQ.getScoreSFQ(), 0);
		testInsSFQ.addToScoreSFQ(20);
		testInsSFQ.addToScoreSFQ(80);
		assertEquals((int)testInsSFQ.getScoreSFQ(), 50);

		// Normal Instructor Construction

		Section sec1 = new Section("COMP 3111 L1", 1232);
		Instructor normalIns = new Instructor("NormalIns", sec1);
		Section sec2 = new Section("COMP 3131 L2", 1732);
		normalIns.addSection(sec2);
		Section sec3 = new Section("COMP 3222 L1", 1242);
		normalIns.addSection(sec3);
		assertFalse(normalIns.isTeaching("COMP 2323 L7"));
	}

	@Test
	public void testSlotValid(){
		Slot s1 = null;
		assertFalse(Slot.isValidSlot(s1));
		Slot s2 = new Slot();
		s2.setStart("09:30AM");
		s2.setEnd("10:30AM");
		assertTrue(Slot.isValidSlot(s2));
		Slot s3 = new Slot();
		s3.setStart("00:00AM");
		s3.setEnd("01:30AM");
		s3.setVenue("Room");
		assertFalse(Slot.isValidSlot(s3));
		assertTrue(s3.getVenue().equals("Room"));
		assertEquals(s3.getEnd().getHour(), 1);
	}

	@Test
	public void testCourseSFQ(){
		Course c = new Course();
		c.addToScoreSFQ(-78);
		assertEquals((int)c.getScoreSFQ(), 0);
		c.addToScoreSFQ(1000);
		assertEquals((int)c.getScoreSFQ(), 0);
		c.addToScoreSFQ(20);
		c.addToScoreSFQ(80);
		assertEquals((int)c.getScoreSFQ(), 50);

		c.setTitle("COMP 3223");
		Section sec1 = new Section("COMP 3223 L1", 3443);
		Section sec2 = new Section("COMP 3333 L1", 2232);
		Section sec3 = new Section("COMP 4545 L1", 2321);
		c.addSection(sec1);
		c.addSection(sec2);
		c.addSection(sec3);
		assertTrue(c.toString().contains("COMP 3223"));
		assertEquals(c.getSections().get(0).getSectionID(), 3443);
		Slot s1 = new Slot();
		s1.setType("R1");
		c.addSlot(s1);
		assertFalse(c.isValid());
		Slot s2 = new Slot();
		s2.setType("LA3");
		c.addSlot(s2);
		assertTrue(c.isValid());
	}

	// @Test public void 




}
