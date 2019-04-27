package comp3111.coursescraper;

import javax.swing.*;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;
import java.util.regex.*;


public class Instructor{

  private List<Course> coursesTaught;
  private String name;
  private boolean freeTu310;
  private int scoreSFQ;


  public void addCourse(Course c){
    this.coursesTaught.add(c);

    if(c.getNumSlots() == 0) return;
    for(int i = 0; i < c.getNumSlots(); i++){

      int startHour = c.getSlot(i).getStartHour();
      int startMinute = c.getSlot(i).getStartMinute();
      int endHour = c.getSlot(i).getEndHour();
      int endMinute = c.getSlot(i).getEndMinute();

      if(startHour <= 15 && endHour >= 15 &&
         startMinute <= 10 && endMinute >= 10){
           this.freeTu310 = false;
         }
    }

  }

  public Instructor(String _name, Course _course){
    this.name = _name;
    this.freeTu310 = true;
    this.coursesTaught = new ArrayList<Course>();
    this.addCourse(_course);
    System.out.println("Constructed " + this.name);
  }


  public String getName(){
    return this.name;
  }


  public boolean isFreeTu310(){
    return freeTu310;
  }


  public int getScoreSFQ(){
    return this.scoreSFQ;
  }

  public int isTeaching(Course c){

    String title = c.getTitle();
    for(int i = 0; i < coursesTaught.size(); i++)
      if(coursesTaught.get(i).getTitle() == title) return i;

    return -1;
  }

}
