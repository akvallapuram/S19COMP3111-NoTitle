package comp3111.coursescraper;

import javafx.scene.control.CheckBox;

import javafx.scene.paint.Color;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.control.Label;

/**
 *This class is for the List Tab as well as TimeTable. A row in the List corresponds to an object of TableClass class.
 *Thus, the List contains multiple TableClass objects.
 * 
 * @author nikhilnanda
 *
 * Task 3/4
 */
public class TableClass {

	private String ccode;
	private String lecturesec;
	private String cname;
	private String instructor;
	private CheckBox enroll;
	private Color colorr;
	private Label lab;
	private int tday;
	
	/**
	 * This is the parameterized constructor
	 * @param c Course Code
	 * @param l Lecture Section
	 * @param cn Course Name
	 * @param i Instructor
	 * @param co Color
	 * @param d Day
	 */
	TableClass(String c, String l, String cn, String i, Color co, int d)
	{
		this.ccode = c;
		this.lecturesec = l;
		this.cname = cn;
		this.instructor = i;
		this.enroll = new CheckBox();
		this.colorr = co;
		this.lab = new Label();
		this.tday = d;
	}
	
	/**
	 * This is the Default Constructor
	 */
	TableClass()
	{
		this.ccode = " ";
		this.lecturesec = " ";
		this.cname = " ";
		this.instructor = " ";
		//this.enroll = new CheckBox();
		this.colorr = Color.rgb(0, 0, 0, 0);
		//this.lab = new Label();
		this.tday = 1;
	}
	
	/**
	 * Gets the Course Code of the TableClass object
	 * @return Get the Course Code of the TableClass object
	 */
	public String getCcode()
	{
		return this.ccode;
	}
	
	/**
	 * Gets the Lecture Section of the TableClass object
	 * @return Get the Lecture Section of the TableClass object
	 */
	public String getLecturesec()
	{
		return this.lecturesec;
	}
	
	/**
	 * Gets the Course Name of the TableClass object
	 * @return Get the Course Name of the TableClass object
	 */
	public String getCname()
	{
		return this.cname;
	}
	
	/**
	 * Gets the Instructor of the TableClass object
	 * @return Get the Instructor of the TableClass object
	 */
	public String getInstructor()
	{
		return this.instructor;
	}
	
	/**
	 * Gets the Enrollment status of the TableClass object 
	 * @return Get the enrollment status of the TableClass object
	 */
	public CheckBox getEnroll()
	{
		return this.enroll;
	}
	
	/**
	 * Gets the Color of the Label of the TableClass object for timetable
	 * @return Get the Color of the TableClass object
	 */
	public Color getColorr()
	{
		return this.colorr;
	}
	
	/**
	 * Gets the Label of the TableClass object for timetable
	 * @return Get the Label of the TableClass object
	 */
	public Label getLab()
	{
		return this.lab;
	}
	
	/**
	 * Gets the day of the slot of the TableClass object
	 * @return Get the day of the TableClass object
	 */
	public int getTday()
	{
		return this.tday;
	}
	
	/**
	 * Set the Course Code
	 * @param c Course Code
	 */
	public void setCcode(String c)
	{
		this.ccode = c;
	}
	
	/**
	 * Set the Lecture Section
	 * @param l Lecture Section
	 */
	public void setLecturesec(String l)
	{
		this.lecturesec = l;
	}
	
	/**
	 * Set the Course Name
	 * @param cn Course Name
	 */
	public void setCname(String cn)
	{
		this.cname = cn;
	}
	
	/**
	 * Set the Instructor
	 * @param i Instructor
	 */
	public void setInstructor(String i)
	{
		this.instructor = i;
	}
	
	/**
	 * Set the Enrollment status
	 * @param e Enrollment status checkbox
	 */
	public void setEnroll(CheckBox e)
	{
		this.enroll = e;
	}
	
	/**
	 * Set the Color for the Label
	 * @param co Color
	 */
	public void setColorr(Color co)
	{
		//this.colorr = new Color(co.getRed(), co.getGreen(), co.getBlue(), 0.5);
		this.colorr = co;
	}
	
	/**
	 * Set the Label
	 * @param l Label
	 */
	public void setLab(Label l)
	{
		this.lab = l;
	}
	
	/**
	 * Set the Day
	 * @param d Day
	 */
	public void setTday(int d)
	{
		this.tday = d;
	}
	
}
