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
* This class introduces a substructure called Sections for Courses in HKUST.
* @author akvallapuram
* Task 1/6
*/

public class Section {

  /**
  * Max number slots a Section can have
  */
  private static final int MAX_NUM_SLOTS = 3;
  /**
  * section ID number of the Section e.g. COMP 3111 L1 (1232) has section ID 1232
  */
  private int sectionID;
  /**
  * section code of the Section e.g. COMP 3111 L1 (1232) has section code COMP 3111 L1
  */
  private String sectionCode;
  /**
  * The timetable slots that the Section can have
  */
  private Slot [] slots;
  /**
  * The number of slots that the Section currently has
  */
  private int numSlots;



  /**
  * Constructor for the Section class
  * @param _sc sectionCode of the Section e.g. COMP 3111 L1 (1232) has section code COMP 3111 L1
  * @param _sid sectionID of the Section e.g. COMP 3111 L1 (1232) has section ID 1232
  */
  public Section(String _sc, int _sid){

    slots = new Slot[MAX_NUM_SLOTS];
    for(int i = 0; i < this.MAX_NUM_SLOTS; i++){
      for (int j = 0; j < MAX_NUM_SLOTS; j++) slots[j] = null;
      numSlots = 0;
    }

    this.sectionID = _sid;
    this.sectionCode = _sc;
  }


  /**
  * Returns the number of timetable slots the Section has
  * @return {@link Section#numSlots}
  */
  public int getNumSlots() {
    return this.numSlots;
  }

  /**
   * Returns the timetable slot of the Section for the given index
   * @param i index of the slot in {@link #slots}
   * @return slot with the given index
   */
  public Slot getSlot(int i){
    return this.slots[i];
  }

  /**
  * Returns the section ID of the Section e.g. COMP 3111 L1 (1232) has section ID 1232
  * @return {@link #sectionID}
  */
  public int getSectionID(){
    return this.sectionID;
  }

  /**
  * Returns the section code of the Section e.g. COMP 3111 L1 (1232) has section code COMP 3111 L1
  * @return {@link #sectionCode}
  */
  public String getSectionCode(){
    return this.sectionCode;
  }

  /**
  * Adds a slot into the Section
  * @param s slot that must be added
  * @return T/F if the slot was successfully added
  */
  public boolean addSlot(Slot s){

    if (this.numSlots > this.MAX_NUM_SLOTS) return false;

    this.slots[this.numSlots] = s;
    this.numSlots++;
    return true;
  }


  /**
  * returns a string for printing the Section
  * @return string containing the section info and its slots
  */
  public String toString(){

    String text = sectionCode.split(" ")[1] + " " + String.format("(%d)\n", sectionID);
    for(int i = 0; i < numSlots; i++)
      text += String.format("\t\t%s\n", slots[i].toString());

      return text;
    }

}
