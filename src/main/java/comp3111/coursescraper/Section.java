package comp3111.coursescraper;


import javax.swing.*;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;
import java.util.regex.*;


public class Section {

  private static final int MAX_NUM_SLOTS = 3;
  private int sectionID;
  private String sectionCode;
  private Slot [] slots;
	private int numSlots;
  private List<Instructor> instructors;


  public Section(String _sc, int _sid){

    for(int i = 0; i < this.MAX_NUM_SLOTS; i++){
      slots = new Slot[MAX_NUM_SLOTS];
      for (int j = 0; j < MAX_NUM_SLOTS; j++) slots[j] = null;
      numSlots = 0;

      this.sectionID = _sid;
      this.sectionCode = _sc;
      this.instructors = new ArrayList<Instructor>();
    }
  }

  public int getNumSlots() {
    return this.numSlots;
  }

  public Slot getSlot(int i){
    return this.slots[i];
  }


  public int getSectionID(){
    return this.sectionID;
  }

  public String getSectionCode(){
    return this.sectionCode;
  }

  public boolean addSlot(Slot s){

    if (this.numSlots > this.MAX_NUM_SLOTS) return false;

    this.slots[this.numSlots] = s;
    this.numSlots++;
    return true;
  }

  public boolean isValid(){

    for(int i = 0; i < this.numSlots; i++){
      String type = this.slots[i].getType();
      boolean b1 = type.startsWith("T");
      boolean b2 = type.startsWith("L");

      if(!b1 && !b2) return false;
    }

    return true;
  }

  public void addInstructor(Instructor _ins){
    this.instructors.add(_ins);
  }

}
