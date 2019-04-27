package comp3111.coursescraper;

import javafx.scene.control.CheckBox;

import javafx.scene.paint.Color;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.control.Label;


public class TableClass {

	private String ccode;
	private String lecturesec;
	private String cname;
	private String instructor;
	private CheckBox enroll;
	private Color colorr;
	private Label lab;
	private int tday;
	
	TableClass(String c, String l, String cn, String i, Color co, int d)
	{
		this.ccode = new String(c);
		this.lecturesec = new String(l);
		this.cname = new String(cn);
		this.instructor = new String(i);
		this.enroll = new CheckBox();
		this.colorr = co;
		this.lab = new Label();
		this.tday = d;
	}
	
	public String getCcode()
	{
		return this.ccode;
	}
	
	public String getLecturesec()
	{
		return this.lecturesec;
	}
	
	public String getCname()
	{
		return this.cname;
	}
	
	public String getInstructor()
	{
		return this.instructor;
	}
	
	public CheckBox getEnroll()
	{
		return this.enroll;
	}
	
	public Color getColorr()
	{
		return this.colorr;
	}
	
	public Label getLab()
	{
		return this.lab;
	}
	
	public int getTday()
	{
		return this.tday;
	}
	
	public void setCcode(String c)
	{
		this.ccode = c;
	}
	
	public void setLecturesec(String l)
	{
		this.lecturesec = l;
	}
	
	public void setCname(String cn)
	{
		this.cname = cn;
	}
	
	public void setInstructor(String i)
	{
		this.instructor = i;
	}
	
	public void setEnroll(CheckBox e)
	{
		this.enroll = e;
	}
	
	public void setColorr(Color co)
	{
		this.colorr = co;
	}
	
	public void setLab(Label l)
	{
		this.lab = l;
	}
	
	public void setTday(int d)
	{
		this.tday = d;
	}
	
}
