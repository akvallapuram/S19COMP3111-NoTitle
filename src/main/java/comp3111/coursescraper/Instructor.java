package comp3111.coursescraper;

import javax.swing.*;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;
import java.util.regex.*;

/**
* This class for storing data about an Instructor whose information has been scraped
* from HKUST Course Catalog and SFQ websites.
* @author akvallapuram
* Task 1/6
*/
public class Instructor{

  /**
  * list of sections taught by this instructor
  */
  private List<Section> sectionsTaught;
  private String name;
  private boolean freeTu310;
  private float scoreSFQ;
  private int numSectionsSFQ;

  /**
  * Adds a section that is assigned to the Instructor
  * @param s Section that must be assigned
  */
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

  /**
  * Constructor for instructor is section is assigned
  * @param _name name of the instructor
  * @param _section a section that is assigned to the instructor
  */
  public Instructor(String _name, Section _section){
    this.name = _name;
    this.freeTu310 = true;
    this.sectionsTaught = new ArrayList<Section>();
    this.addSection(_section);
  }


  /**
  * Constructor for instructor for pure SFQ rating purposes
  * @param _name name of the instructor
  */
  public Instructor(String _name){
    this.name = _name;
    this.numSectionsSFQ = 0;
    this.scoreSFQ = 0;
    this.freeTu310 = true;
    this.sectionsTaught = null;
  }


  /**
  * Returns the name of the Instructor
  * @return {@link #name}
  */
  public String getName(){
    return this.name;
  }

  /**
  * Returns if the Instructor is free on Tuesday 3:10PM
  * @return {@link #freeTu310}
  */
  public boolean isFreeTu310(){
    return freeTu310;
  }

  /**
  * Checks if the Instructor is teaching a specified section
  * @param _secCode section code of the Section
  * @return T/F if the Instructor teaches the section
  */
  public boolean isTeaching(String _secCode){
    String [] _secName = _secCode.split("\\s+");
    String _secN = String.join(" ", _secName);
    //System.out.println(_secN);
    _secN = _secN.substring(0, 4) + _secN.substring(5);
    for(Section sec : sectionsTaught)
      if(sec.getSectionCode().equals(_secN)) return true;

    return false;
  }


  /**
  * Returns the SFQ score of the Instructor scraped from the given SFQ url
  * @return {@link #scoreSFQ}
  */
  public float getScoreSFQ(){
    return this.scoreSFQ;
  }


  /**
  * Adds a found SFQ score associated with the Instructor from the given SFQ url
  * @param score the score found in the SFQ page related to the Instructor
  */
  public void addToScoreSFQ(float score){
    if(score > 100 || score < 0) return;
    float num = numSectionsSFQ;
    this.scoreSFQ = (scoreSFQ*num + score) / (float)(num+1);
    numSectionsSFQ++;
  }


}
