package comp3111.coursescraper;


import org.junit.Test;


import comp3111.coursescraper.Course;

import static org.junit.Assert.*;

import comp3111.coursescraper.Course;
import comp3111.coursescraper.Controller;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.CheckBox;

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

}
