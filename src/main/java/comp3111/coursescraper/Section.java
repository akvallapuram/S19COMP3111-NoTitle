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


  public Section(String _sc, int _sid){

    slots = new Slot[MAX_NUM_SLOTS];
    for(int i = 0; i < this.MAX_NUM_SLOTS; i++){
      for (int j = 0; j < MAX_NUM_SLOTS; j++) slots[j] = null;
      numSlots = 0;
    }

    this.sectionID = _sid;
    this.sectionCode = _sc;
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

  /**check if slot is lecture, lab or tutorial and in 9AM-10PM **/
  public static boolean isValidSlot(Slot s){

        if(s == null) return false;

        String type = s.getType();
        boolean b1 = type.startsWith("T");
        boolean b2 = type.startsWith("L");

        boolean b3 = (s.getStartHour() >= 9) && (s.getEndHour() <= 22);

        if((b1 || b2) && b3) return true;
        else return false;
}

  public String toString(){

    String text = sectionCode.split(" ")[1] + " " + String.format("(%d)\n", sectionID);
    for(int i = 0; i < numSlots; i++)
      if(isValidSlot(slots[i])) text += String.format("\t\t%s\n", slots[i].toString());

      return text;
    }

}
