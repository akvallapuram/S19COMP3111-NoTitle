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

  private List<Section> sectionsTaught;
  private String name;
  private boolean freeTu310;
  private float scoreSFQ;
  private int numSectionsSFQ;


  public void addSection(Section s){
    this.sectionsTaught.add(s);

    if(s.getNumSlots() == 0) return;
    for(int i = 0; i < s.getNumSlots(); i++){

      int startHour = s.getSlot(i).getStartHour();
      int startMinute = s.getSlot(i).getStartMinute();
      int endHour = s.getSlot(i).getEndHour();
      int endMinute = s.getSlot(i).getEndMinute();

      if(startHour <= 15 && endHour >= 15 &&
         startMinute <= 10 && endMinute >= 10){
           this.freeTu310 = false;
         }
    }

  }

  public Instructor(String _name, Section _section){
    this.name = _name;
    this.freeTu310 = true;
    this.sectionsTaught = new ArrayList<Section>();
    this.addSection(_section);
  }


  public Instructor(String _name){
    this.name = _name;
    this.numSectionsSFQ = 0;
    this.scoreSFQ = 0;
    this.freeTu310 = true;
    this.sectionsTaught = null;
  }


  public String getName(){
    return this.name;
  }


  public boolean isFreeTu310(){
    return freeTu310;
  }



  public boolean isTeaching(String _secCode){

    for(Section sec : sectionsTaught)
      if(sec.getSectionCode().equals(_secCode)) return true;

    return false;
  }


  public float getScoreSFQ(){
    return this.scoreSFQ;
  }

  public void addToScoreSFQ(float score){
    if(score > 100 || score < 0) return;
    float num = numSectionsSFQ;
    this.scoreSFQ = (scoreSFQ*num + score) / (float)(num+1);
    numSectionsSFQ++;
    // System.out.println(this.name + "'s new score is " + this.scoreSFQ);

  }


}
