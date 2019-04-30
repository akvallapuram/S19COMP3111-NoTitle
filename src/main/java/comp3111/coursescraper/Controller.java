package comp3111.coursescraper;


import javafx.collections.FXCollections;






import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.concurrent.Task;
import javafx.geometry.Insets;


import javax.swing.*;
//import javax.swing.event.ChangeListener;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Collections;
import java.util.regex.*;

import javafx.scene.control.TableView ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.beans.value.ChangeListener ;
import javafx.beans.value.ObservableValue ;



public class Controller {

    /**
     * Main Tab b
     */
    @FXML
    private Tab tabMain;

    @FXML
    private TextField textfieldTerm;

    @FXML
    private TextField textfieldSubject;

    @FXML
    private Button buttonSearch;

    @FXML
    private TextField textfieldURL;

    @FXML
    private Tab tabStatistic;

    @FXML
    private ComboBox<?> comboboxTimeSlot;

    @FXML
    private Tab tabFilter;

    @FXML
    private Tab tabList;

    @FXML
    private Tab tabTimetable;

    @FXML
    private Tab tabAllSubject;

    @FXML
    private ProgressBar progressbar;

    @FXML
    private TextField textfieldSfqUrl;

    @FXML
    private Button buttonSfqEnrollCourse;

    @FXML
    private Button buttonInstructorSfq;

    @FXML
    private TextArea textAreaConsole;

    private Scraper scraper = new Scraper();

    @FXML
    private CheckBox AM;
    @FXML
    private CheckBox PM;
    @FXML
    private CheckBox Mon;
    @FXML
    private CheckBox Tues;
    @FXML
    private CheckBox Wed;
    @FXML
    private CheckBox Thrs;
    @FXML
    private CheckBox Fri;
    @FXML
    private CheckBox Sat;
    @FXML
    private CheckBox CC;
    @FXML
    private CheckBox NE;
    @FXML
    private CheckBox LT;
    @FXML
    public Button SelectAll;
    @FXML
    public Button AllSS;
    @FXML
    private TableView<TableClass> llist;
    @FXML
    private TableColumn<TableClass, String> fcCode;
    @FXML
    private TableColumn<TableClass, String> flSection;
    @FXML
    private TableColumn<TableClass, String> fcName;
    @FXML
    private TableColumn<TableClass, String> flInstructor;
    @FXML
    private TableColumn<TableClass, CheckBox> flEnroll;

    /**
     * Subset of all sections enrolled without any duplication
     */
    ObservableList<TableClass> datas3 = FXCollections.observableArrayList();

    /**
     * List of all sections encountered
     */
    List<TableClass> datasAll = new ArrayList<TableClass>();

    /**
     * ObservableList that stores all sections according to Filter
     */
    ObservableList<TableClass> newList = FXCollections.observableArrayList();


    /**
     * AllSubjectSearch Button First Click
     * Returns Total Number of Categories/Code Prefix:
     * Changes the next AllSS button Action to allSubjectSearch2()
     */
    @FXML
    public void allSubjectSearch() {
        //TODO consider the first line "clicking allsubjectsearch or search
        ArrayList<String> constructing = new ArrayList<String>();
        String[] allSubjects = {
                "ACCT", "BIBU", "BIEN", "BIPH", "CENG", "CHEM", "CIVL", "COMP",
                "CPEG", "ECON", "ELEC", "ENEG", "ENTR", "ENVR", "ENVS", "FINA",
                "FYTG", "GBUS", "GNED", "HART", "HLTG", "HUMA", "IDPO", "IEDA",
                "IIMP", "IROP", "ISDN", "ISOM", "LABU", "LANG", "LIFS", "MARK",
                "MATH", "MECH", "MGMT", "OCES", "PHYS", "PPOL", "RMBI", "SBMT",
                "SCIE", "SHSS", "SISP", "SOSC", "SUST", "TEMG", "UROP", "WBBA",
                "AESF", "BTEC", "CBME", "CHMS", "CIEM", "CSIC", "CSIT", "EEMT",
                "EESM", "EMBA", "ENGG", "EVNG", "EVSM", "GFIN", "HHMS", "HMMA",
                "IBTM", "IDPO", "IMBA", "JEVE", "MAED", "MAFS", "MESF", "MGCS",
                "MILE", "MIMT", "MSBD", "NANO", "PDEV", "SSMA"
        };
        textfieldSubject.setText("");
        int i = 0;
        int allsubjectcount = 0;
        while (i < allSubjects.length) {
            textfieldSubject.setText(allSubjects[i]);
            List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
            if (v != null) {
                constructing.add(allSubjects[i]);
                allsubjectcount++;
            }
            i++;
        }
        textAreaConsole.setText("Total Number of Categories/Code Prefix: " + allsubjectcount);
        int finalAllsubjectcount = allsubjectcount;
        Iterator it = constructing.iterator();
        String[] coursesFound = new String[constructing.size()];
        int index = 0;
        while(it.hasNext()) {
            coursesFound[index] = (String) it.next();
            index++;
        }
        AllSS.setText("All Subject Search");

        Task<Void> task = new Task<Void>() {
            int numcourses = 0;
            double progress = 0;
            @Override
            protected Void call() throws Exception {
                for(int j = 0; j < coursesFound.length; j++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int finalJ = j;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textfieldSubject.setText(coursesFound[finalJ]);
                            numcourses += searchCount();
                        }
                    });
                    System.out.println(coursesFound[j] + " is done.");
                    updateProgress(j+1, finalAllsubjectcount);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        textAreaConsole.setText(textAreaConsole.getText() + "\n" + "Total Number of Courses Fetched: " + numcourses);
                    }
                });
                return null;

            }
        };
        progressbar.progressProperty().bind(task.progressProperty());
        AllSS.setOnAction(event -> {
            try {
                Thread th = new Thread(task);
                th.setDaemon(true);
                th.start();
            } catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
        });
    }

    /**
     * Scrapes based on textfieldURL, textfieldTerm, textfieldSubject
     * returns the num of courses within the result
     * @return num courses within result of scraping
     */
    public int searchCount() {
        int numcourses = 0;
        List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
        for (Course c : v) {
            numcourses++;
            String newline = c.getTitle() + "\n";
            for (int i = 0; i < c.getNumSlots(); i++) {
                Slot t = c.getSlot(i);
                newline += "Slot " + i + ":" + t + "\n";
            }
            textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
        }
        return numcourses;
    }

    /**
    * Prints the SFQ score of all the instructors in the given url to the SFQ webpage
    * @see Scraper#scrapeInstructorSFQ()
    */
    @FXML
    void findInstructorSfq() {
        Scraper scraperSFQ = new Scraper();
        String url = textfieldSfqUrl.getText();
        List<Instructor> instructors = scraperSFQ.scrapeInstructorSFQ("file:///Users/anishkrishnavallapuram/Desktop/School%20Summary%20Report.htm");
        // List<Instructor> instructors = scraperSFQ.scrapeInstructorSFQ(testfieldSfqUrl.getText());

        String newline = "";
        for(Instructor ins : instructors) newline += String.format("%s %.2f %n", ins.getName(), ins.getScoreSFQ());
        textAreaConsole.setText(newline);
    }

    /**
    * Prints the SFQ score of all the courses enrolled from the given url to the SFQ webpage
    * @see Scraper#scrapeCourseSFQ()
    */
    @FXML
    void findSfqEnrollCourse() {
      Scraper scraperSFQ = new Scraper();
      String url = textfieldSfqUrl.getText();
      List<Course> courses = scraperSFQ.scrapeCourseSFQ("file:///Users/anishkrishnavallapuram/Desktop/School%20Summary%20Report.htm", datasAll);
      // List<Course> courses = scraperSFQ.scrapeCourseSFQ(testfieldSfqUrl.getText());

      String newline = "";
      for(Course c : courses) newline += String.format("%s %.2f %n", c.getTitle(), c.getScoreSFQ());
      textAreaConsole.setText(newline);
    }

    /**
     * Filter Results Task 2 and add the sections to List (Task3)
     * Based on AND logic. If there exists 1+ slot in a Course for every requirement/filter,
     * Displays all slots of course based on filter
     * List - takes all the sections displayed on the Console after filter and displays a new list in the List Tabgit
     */
    @FXML
    public void filterResults() {
        Boolean[] checked = new Boolean[11];
        List<Boolean> ticked = new ArrayList<Boolean>();
        for (int i = 0; i < 11; i++) {
            checked[i] = false;
            ticked.add(i, false);
        }

        //For List
        createList2();
        //ObservableList<TableClass> datas3 = FXCollections.observableArrayList();

        textAreaConsole.setText("");

        lostEnrollment();
        newList.clear();

        List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
        for (Course c : v) {
            Boolean containsAtLeastOne;
            String newline= c.getTitle() + "\n";
            if (AM.isSelected()) {
                checked[0] = check0(c);
                ticked.add(0, true);
            }
            if (PM.isSelected()) {
                checked[1] = check1(c);
                ticked.add(1, true);
            }
            if (Mon.isSelected()) {
                checked[2] = check2(c);
                ticked.add(2, true);
            }
            if (Tues.isSelected()) {
                checked[3] = check3(c);
                ticked.add(3, true);
            }
            if (Wed.isSelected()) {
                checked[4] = check4(c);
                ticked.add(4, true);
            }
            if (Thrs.isSelected()) {
                checked[5] = check5(c);
                ticked.add(5, true);
            }
            if (Fri.isSelected()) {
                checked[6] = check6(c);
                ticked.add(6, true);
            }
            if (Sat.isSelected()) {
                checked[7] = check7(c);
                ticked.add(7, true);
            }
            if (LT.isSelected()) {
                checked[8] = check8(c);
                ticked.add(8, true);
            }
            if (CC.isSelected()) {
                checked[9] = check9(c);
                ticked.add(9, true);
            }
            if (NE.isSelected()) {
                checked[10] = check10(c);
                ticked.add(10, true);
            }
            // array checked[] has all [T/F values] T means fulfill requirements
            // ticked adds a T value; this way we can compare all T ticked values to all T/F values in checked
            List<Integer> indeces = new ArrayList<>();
            int count = 0;
            while(count < 11) {
                if(ticked.get(count)) {
                    indeces.add(count);
                }
                count++;
            }
            Iterator i = indeces.iterator();
            containsAtLeastOne = true; //this is kinda dangerous
            //for all ticked values there needs to be a T in check[] to print the Course + Slots
            while (i.hasNext()) {
                if (!checked[(int)i.next()]) {
                    containsAtLeastOne = false;
                }
            }


            if(containsAtLeastOne) {

            	String sec = " ";
            	Color col = new Color(Math.random(), Math.random(), Math.random(), 0.5);
            	String prevSecType = " ";

                for (int j = 0; j < c.getNumSlots(); j++) {
                    Slot t = c.getSlot(j);
                    //newline += "Slot " + j + ":" + t + "\n";

                    TableClass obj = new TableClass(c.getTitle().substring(0, 10), t.getType().substring(0, 3), c.getTitle().substring(12), "1", col, t.getDay());
                    //Get instructor
                    String inst = "";
                    for(int a=0; a<INSTRUCTORS_IN_SEARCH.size(); ++a)
                    {
                        if(INSTRUCTORS_IN_SEARCH.get(a).isTeaching(obj.getCcode()+" "+obj.getLecturesec()))
                        {
                            inst += INSTRUCTORS_IN_SEARCH.get(a).getName() + "\n";
                        }
                    }
                    obj.setInstructor(inst);
                    //Have to prevent duplication
                    TableClass dupl = new TableClass("1", "1", "1", "1", Color.color(Math.random(), Math.random(), Math.random(), 0.5), 1);
                    TableClass dupl1 = new TableClass("1", "1", "1", "1", Color.color(Math.random(), Math.random(), Math.random(), 0.5), 1);
                    int flagg = 0;
                    int extra = 0;
                    Color dummy = col;
                	for(int l=0; l<datasAll.size(); ++l)
                	{
                		TableClass dup = datasAll.get(l);
                		if(dup.getCcode().equals(obj.getCcode()))
                		{
                			if(dup.getLecturesec().equals(obj.getLecturesec()))
                			{
                				if((dup.getCname().equals(obj.getCname()))&&((dup.getTday())==obj.getTday()))
                				{
                					//obj = dup;
                					flagg = 1;
                					dupl = dup;
                					break;
                                }
                            }
                		}
                    }

                	//End of prevention of duplication

                    if((t.getType()!=sec)&&(t.getType().length()<11))
                    {
                    	if(flagg!=1)
                    	{
                    		datas3.add(obj);
                    	}

                    	if(flagg==1)
                    	{
                            newList.add(dupl);
                    	}
                    	else
                    	{
                    		newList.add(obj);
                    	}

                    	sec = t.getType();
                    	col = new Color(Math.random(), Math.random(), Math.random(), 0.5);
                    	obj.setColorr(col);
                    }

                    if(t.getType().length()>11)
                    {
                        obj.setLecturesec(prevSecType.substring(0, 3));
                        obj.setColorr(col);
                    }

                    //
                    for(int l=0; l<datasAll.size(); ++l)
                	{
                		TableClass dup = datasAll.get(l);
                		if(dup.getCcode().equals(obj.getCcode()))
                		{
                			if(dup.getLecturesec().equals(obj.getLecturesec()))
                			{
                				if((dup.getCname().equals(obj.getCname()))&&((dup.getTday())==obj.getTday()))
                				{
                                    extra++;
                                }
                            }
                		}
                    }
                    //

                    if(flagg!=1)
                    //if(flagg!=1)
                    {
                        if(extra==0)
                        {
                            datasAll.add(obj);
                        }
                        //datasAll.add(obj);
                    }
                    llist.setItems(newList);

                    newline += obj.getCcode() + " " + obj.getLecturesec() + "Slot " + j + ":" + t + "\n";	//My version which adds sections


                    if(flagg!=1)
                    {
                    	obj.getEnroll().selectedProperty().addListener(new ChangeListener<Boolean>() {
                    		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    			if(newValue==true)
                    			{
                    				System.out.println("Checkbox is checked");
                        			blocks(obj, t);
                        			sameSection(obj, t);
                        			printEnrolled(obj);
                    			}
                    			else if((newValue==false)&&(oldValue==true))
                    			{
                    				//Need to remove label from TimeTable and print on console
                    				if(datas3.contains(obj))
                    				{
                    					printEnrolledRemove(obj);
                    				}
                    				removeBlocks(obj, t);
                    			}

                        	}
                    	});
                    }

                    prevSecType = sec;
                }
                textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
            }
            ticked.removeAll(ticked);
            for (int k = 0; k < 11; k++) {
                checked[k] = false;
                ticked.add(k, false);
            }
        }
    }

    /**
     * Checks a Course if there is a Slot that starts in AM
     * @param c Course to be checked
     * @return Boolean there is a Slot that starts in AM
     */
    static Boolean check0(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            LocalTime time1 = LocalTime.parse("12:00");
            if (t.getStart().compareTo(time1) < 0) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks a Course if there is a Slot that starts in PM
     * @param c Course to be checked
     * @return Boolean there is a Slot that starts in PM
     */
    public static Boolean check1(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            LocalTime time1 = LocalTime.parse("12:00");
            if (t.getStart().compareTo(time1) > 0) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks a Course if there is a slot in Monday
     * @param c Course to be checked
     * @return Boolean there is a slot in Monday
     */
    public static Boolean check2(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 0) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks a Course if there is a slot in Tues
     * @param c Course to be checked
     * @return Boolean there is a slot in Tues
     */
   public static Boolean check3(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 1) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks a Course if there is a slot in Wed
     * @param c Course to be checked
     * @return Boolean there is a slot in Wed
     */
    public static Boolean check4(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 2) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks a Course if there is a slot in Thurs
     * @param c Course to be checked
     * @return Boolean there is a slot in Thurs
     */
    public static Boolean check5(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 3) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks a Course if there is a slot in Fri
     * @param c Course to be checked
     * @return Boolean there is a slot in Fri
     */
    public static Boolean check6(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 4) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks a Course if there is a slot in Sat
     * @param c Course to be checked
     * @return Boolean there is a slot in Sat
     */
    public static Boolean check7(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 5) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks a Course if there is a slot that is of type Lab or Tutorial
     * @param c Course to be checked
     * @return Boolean there is a slot that is of type Lab or Tutorial
     */
    public static Boolean check8(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            String type = t.getType();
            Boolean b1 = Pattern.matches("T\\d", type);
            if (type.contains("LA") || b1) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks a Course if it is 4YCC
     * @param c Course to be checked
     * @return Boolean the course is 4YCC
     */
    public Boolean check9(Course c) {
        return c.getCommonCourse();

    }

    /**
     * Checks a Course for No Exclusion
     * @param c Course to be checked
     * @return Boolean the course has No Exclusion
     */
    public static Boolean check10(Course c) {
        if (c.getExclusion() == "null") {
            return true;
        }
        return false;
    }

    /**
     * Implementation of SelectAll Button in Filter Tab
     * Selects All filters and changes next click to DeselectAll
     */
    @FXML
    public void selectAll() {
        AM.setSelected(true);
        PM.setSelected(true);
        Mon.setSelected(true);
        Tues.setSelected(true);
        Wed.setSelected(true);
        Thrs.setSelected(true);
        Fri.setSelected(true);
        Sat.setSelected(true);
        LT.setSelected(true);
        CC.setSelected(true);
        NE.setSelected(true);
        filterResults();
        SelectAll.setText("Deselect All");
        SelectAll.setOnAction(e -> deselectAll());
    }

    /**
     * Implementation of DeselectAll Button in Filter Tab
     * Deselects all filters and changes next click to SelectAll
     */
    public void deselectAll() {
        AM.setSelected(false);
        PM.setSelected(false);
        Mon.setSelected(false);
        Tues.setSelected(false);
        Wed.setSelected(false);
        Thrs.setSelected(false);
        Fri.setSelected(false);
        Sat.setSelected(false);
        LT.setSelected(false);
        CC.setSelected(false);
        NE.setSelected(false);
        SelectAll.setText("Select All");
        SelectAll.setOnAction(t -> selectAll());
    }


  /**Used by scraper to check if a section has already been scraped before**/
  public static int inSectionSearch(int _sid){

    if(SECTIONS_IN_SEARCH.size() == 0) return -1;

    for(int i = 0; i < SECTIONS_IN_SEARCH.size(); i++){
      int sid = SECTIONS_IN_SEARCH.get(i).getSectionID();
      if( sid == _sid) return sid;
    }

    return -1;
  }


  /** Used by scraper to check if an instructor has already been scraped**/
  public static int inInstructorSearch(String _ins){

    if(INSTRUCTORS_IN_SEARCH.size() == 0) return -1;

    for(int i = 0; i < INSTRUCTORS_IN_SEARCH.size(); i++){
      if(INSTRUCTORS_IN_SEARCH.get(i).getName().equals(_ins)) {
        // System.out.println("Found " + _ins);
        return i;
      }
    }


    return -1;

  }

    // used for the search
    public static int NUMBER_OF_SECTIONS = 0;
    public static List<Instructor> INSTRUCTORS_IN_SEARCH  = new ArrayList<Instructor>();
    public static List<Section> SECTIONS_IN_SEARCH = new ArrayList<Section>();

    // TASK 1
    @FXML
    public void search() {

      // disable the enrolled courses for SFQ Search
      buttonSfqEnrollCourse.setDisable(false);
      Controller.NUMBER_OF_SECTIONS = 0;

    	List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());

        //need to display the number of all subjects in a given term even when search is clicked
        //(task5) - Jeff
        //allSubjectSearch();


        // other errors
        if(v == null) textAreaConsole.setText("Errors: check your terminal");


      // handling 404 error - Anish
      if(v.size() == 1) {

        if(v.get(0).getTitle().equals("404PageNotFound")){
          textAreaConsole.setText("Error 404: Page not Found\nPlease check your parameters");
          return;
        }

        if(v.get(0).getTitle().equals("UnknownHTTPSError")){
          textAreaConsole.setText("UnknownHTTPSError");
          return;
        }

      }


    // number of courses found
    textAreaConsole.setText("Total Number of different courses in this search: " + v.size());


    // number of sections Found
    textAreaConsole.setText(textAreaConsole.getText() + "\n" +
    "Total Number of difference sections in this search: " + Controller.NUMBER_OF_SECTIONS + "\n\n");

    //Free instructors
    List<String> freeInstructors = new ArrayList<String>();
    for(Instructor ins : Controller.INSTRUCTORS_IN_SEARCH) if(ins.isFreeTu310()) freeInstructors.add(ins.getName());
    Collections.sort(freeInstructors);
    String freeIns = "";
    for(String str : freeInstructors) freeIns  += str + "\n";
    textAreaConsole.setText(textAreaConsole.getText() + "\n" +
    "Instructors who has teaching assignment this term but does not need to teach at Tu 3:10pm:\n" + freeIns + "\n\n");


    //print sections
    String newline = "";
  	for (Course c : v) newline += c.toString() + "\n\n\n";
    textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);

      // for(Course c : v)
      //   for(Section s : c.getSections())
      //     System.out.println(s.getSectionCode());


      freeInstructors.clear();
      INSTRUCTORS_IN_SEARCH.clear();
      SECTIONS_IN_SEARCH.clear();
    }

    /**
     * Creates a List for the list Tab and aligns each column with a property
     * Task3
     */
    @FXML
    public void createList2()
    {
    	fcCode.setCellValueFactory(new PropertyValueFactory<>("ccode"));
    	flSection.setCellValueFactory(new PropertyValueFactory<>("lecturesec"));
    	fcName.setCellValueFactory(new PropertyValueFactory<>("cname"));
    	flInstructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
    	flEnroll.setCellValueFactory(new PropertyValueFactory<>("enroll"));

    }

    /**
     * Adds a block for the section specified by ts object to the timetable
     * @param ts Section to be added to timetable
     * @param s Slot that belongs to section ts
     * Task 4
     */
    @FXML
    public void blocks(TableClass ts, Slot s)
    {
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();

        ts.getLab().setText(ts.getCcode()+"\n"+ts.getLecturesec());

    	ts.getLab().setBackground(new Background(new BackgroundFill(ts.getColorr(), CornerRadii.EMPTY, Insets.EMPTY)));
    	int d = s.getDay();

    	ts.getLab().setLayoutX((d*100.0)+100.0);
    	int stimeh = s.getStartHour();
    	int stimem = s.getStartMinute();
    	int started = (stimeh*60)+stimem;

    	ts.getLab().setLayoutY(40.0 + (stimeh-9)*20.0 + (stimem*0.33));
    	int etimeh = s.getEndHour();
    	int etimem = s.getEndMinute();
    	int ended  = (etimeh*60)+etimem;

    	ts.getLab().setMinWidth(100.0);

    	ts.getLab().setMaxWidth(100.0);

    	int diff = etimem-stimem;
    	int offfset = diff==50 ? 30 : diff==20 ? 15 : 0;

    	int atls = ended-started;

    	ts.getLab().setMinHeight(atls*0.33);
    	ts.getLab().setMaxHeight(atls*0.33);


        ts.getLab().setFont(ts.getLab().getFont().font(6));

    	ap.getChildren().addAll(ts.getLab());
    }

    /**
     * Checks whether a slot belongs to section of ts object
     * @param ts Section to be checked with
     * @param s Slot that is part of section of ts
     * Task 3
     */
    @FXML
    public void sameSection(TableClass ts, Slot s)
    {
    	for(int i=0; i<datasAll.size(); ++i)
    	{
    		if((datasAll.get(i).getCcode().equals(ts.getCcode()))&&(datasAll.get(i).getLecturesec().equals(ts.getLecturesec())))
    		{
    			if((datasAll.get(i).getEnroll().isSelected()==false)&&((ts.getEnroll().isSelected())==true))
    			{
                    //System.out.println(":/");
                    datasAll.get(i).setColorr(ts.getColorr());
                    datasAll.get(i).getEnroll().setSelected(true);
                    break;
    			}
    		}
    	}
    }

    /**
     * As soon as a section is enrolled the Console output is updated with all the sections enrolled
     * @param ts Section that has been enrolled
     * Task 3
     */
    public void printEnrolled(TableClass ts)
    {

    	if(textAreaConsole.getText().substring(0, 36).equals("The following sections are enrolled:"))
    	{
    		String consoleCurr = textAreaConsole.getText();
    		String newstr = "";

    		for(int i=37; i<1000; ++i)		//change 1000 later
    		{
    			if(consoleCurr.charAt(i)=='\n')
    			{
    				break;
    			}
    			else
    			{
    				newstr += consoleCurr.charAt(i);
    			}
    		}

    		if((ts.getCcode() + " " + ts.getLecturesec()).equals(newstr)==false)
    		{
    			textAreaConsole.setText(textAreaConsole.getText().substring(0, 37) + ts.getCcode() + " " + ts.getLecturesec() + "\n" + textAreaConsole.getText().substring(37));
    		}
    	}
    	else
    	{
    		textAreaConsole.setText("The following sections are enrolled:" + "\n" + ts.getCcode() + " " + ts.getLecturesec() +"\n" + textAreaConsole.getText());
    	}
    }

    /**
     * Prints the enrolled courses on the Console after removing the section ts
     * whose enrolment status has been changed from checked to unchecked
     * @param ts Section whose enrolment status has been changed from checked to unchecked
     * Task 3
     */
    public void printEnrolledRemove(TableClass ts)
    {
    	String match = ts.getCcode() + " " + ts.getLecturesec();
    	String resultant = "";
    	int end = 0;

    	for(int i=37; i<10000; i=i+15)		//change 1000 later
    	{
    		if(match.equals(textAreaConsole.getText().substring(i, i+14)))
    		{
    			end = i;
    			break;
    		}
    		else
    		{
    			resultant = resultant + "\n" + textAreaConsole.getText().substring(i, i+14);
    		}
    	}

    	resultant = resultant + textAreaConsole.getText().substring(end+14);

    	if((textAreaConsole.getText().charAt(end+14)=='\n')&&(textAreaConsole.getText().charAt(end+15)=='\n')&&(end==37))
    	{
    		textAreaConsole.setText(resultant.substring(1));
    	}
    	else
    	{
    		textAreaConsole.setText("The following sections are enrolled:" + resultant);
    	}
    }

    /**
     * Prints the enrolled courses on the Console
     * Task 3
     */
    public void lostEnrollment()
    {
    	String prefix = "";
    	int flag = 0;

    	for(int i=0; i<datas3.size(); ++i)
    	{
    		if(datas3.get(i).getEnroll().isSelected())
    		{
    			prefix += datas3.get(i).getCcode() + " " + datas3.get(i).getLecturesec() + "\n";
    			flag = 1;
    		}
    	}

    	if(flag==1)
    	{
    		prefix = "The following sections are enrolled:" + "\n" + prefix;
    	}

    	textAreaConsole.setText(prefix);
    }

    /**
     * Removes all labels of a particular section from the timetable and changes the enrollment status of the sections
     * @param ts TableClass object that specifies which section to remove from timetable
     * @param s Slot of the Section
     * Task 4
     */
    public void removeBlocks(TableClass ts, Slot s)
    {
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();

    	for(int i=0; i<datasAll.size(); ++i)
    	{
    		if((datasAll.get(i).getLab().getText()).equals(ts.getLab().getText()))
    		{
    			ap.getChildren().remove(datasAll.get(i).getLab());
    			if(datasAll.get(i).getEnroll().isSelected())
    			{
    				datasAll.get(i).getEnroll().setSelected(false);
    			}
    		}
    	}
    }

    /**
     * Returns the TableView for List
     * @return TableView for List
     * Task 3
     */
    public TableView<TableClass> getTable()
    {
    	return llist;
    }

}
