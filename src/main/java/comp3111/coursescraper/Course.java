package comp3111.coursescraper;


import java.util.List;
import java.util.ArrayList;

/**
 * OOP representation of a Course in a term. E.G. COMP 3111
 */
public class Course {
	private static final int DEFAULT_MAX_SLOT = 20;

	private String title ;
	private String description ;
	private String exclusion;
	private Slot [] slots;
	private int numSlots;
	private boolean commonCourse;

	/**
	* stores the SFQ score associated with this score
	*/
	private float scoreSFQ;
	/**
	* the number of sections associated for the above SFQ score
	*/
	private int numSectionsSFQ;
	/**
	* list of all the sections associated with the course
	* @see Class Section
	*/
	private List<Section> sections;


	/**
	 * Creation of Course
	 * Default 20 slots set to null (numSlots = 0)
	 */
	public Course() {
		slots = new Slot[DEFAULT_MAX_SLOT];
		for (int i = 0; i < DEFAULT_MAX_SLOT; i++) slots[i] = null;
		numSlots = 0;

		this.scoreSFQ = 0;
		this.numSectionsSFQ = 0;
		sections = new ArrayList<Section>();
	}

	/**
	 * Adds a Slot to a Course. E.G. Course COMP 3111 has a Slot L1
	 * @param s Slot
	 */

	public void addSlot(Slot s) {
		if (numSlots >= DEFAULT_MAX_SLOT)
			return;
		slots[numSlots++] = s.clone();
	}

	/**
	 * Returns a slot of a course at specified index
	 * @param i slot index of course
	 * @return slot of with the given index
	 */
	public Slot getSlot(int i) {
		if (i >= 0 && i < numSlots)
			return slots[i];
		return null;
	}

	/**
	 * Returns String Title of Course
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets String Title of Course
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns String Description of Course
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets String description of Course
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns String exclusion of Course
	 * No exclusion = "null"
	 * @return the exclusion
	 */
	public String getExclusion() {
		return exclusion;
	}

	/**
	 * Sets String exclusion of Course
	 * @param exclusion the exclusion to set
	 */
	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}

	/**
	 * Returns int Number of Slots in Course
	 * @return the numSlots
	 */
	public int getNumSlots() {
		return numSlots;
	}

	/**
	 * Sets int numSlots in Course
	 * @param numSlots the numSlots to set
	 */
	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
	}

	/**
	 * Returns boolean if a Course is a Common Course
	 * @return T/F if CC
	 */

	public boolean getCommonCourse() { return commonCourse; }


	/**
	 * Sets if a Course is a Common Course
	 * @param cc T/F if a course is a common course
	 */
	public void setCommonCourse(boolean cc) { this.commonCourse = cc; }

	/**
	* Returns Float SFQ score of the course
	* @return {@link #scoreSFQ}
	*/
	public float getScoreSFQ(){
    return this.scoreSFQ;
  }

	/**
	* inserts a score for a course's section found in SFQ webpage
	* @param score is the score of a section that must be added
	*/
  public void addToScoreSFQ(float score){
    if(score > 100 || score < 0) return;
    float num = numSectionsSFQ;
    this.scoreSFQ = (scoreSFQ*num + score) / (float)(num+1);
    numSectionsSFQ++;
  }

	/**
		Inserts a section found by scraper that is related to this course
		@param s is a section that is related to this course
	*/
	public void addSection(Section s){
		this.sections.add(s);
	}

	/**
	* check if course atleast has one lecture, lab or tutorial
	* @return T/F if course has atleast one lecture, lab or tutorial
	*/
	public boolean isValid(){

		boolean check = false;
    for(int i = 0; i < this.numSlots; i++){
      String type = this.slots[i].getType();
      boolean b1 = type.startsWith("T");
      boolean b2 = type.startsWith("L");

      if(b1 || b2) check = true;
    }

    return check;
  }


  /**
	* returns the list sections in this course
	* @return {@link #sections}
	*/
	public List<Section> getSections(){
		return this.sections;
	}

	/**
	* returns a string can be used for printing information about the course
	* @return String newline which contains the course title and its section info
	*/
	public String toString(){
		String newline = this.title + "\n";
		for(Section s : sections) newline += s.toString();
		return newline;
	}

}
