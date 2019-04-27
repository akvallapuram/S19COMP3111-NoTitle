package comp3111.coursescraper;


import javafx.collections.FXCollections;





import javafx.collections.ObservableList;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.control.CheckBox;
import org.apache.bcel.generic.Select;

import javax.swing.*;
//import javax.swing.event.ChangeListener;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;
import java.util.regex.*;

import javafx.scene.control.TableView ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.beans.value.ChangeListener ;
import javafx.beans.value.ObservableValue ;



public class Controller {

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
    
    ObservableList<TableClass> datas3 = FXCollections.observableArrayList();
    List<TableClass> datasAll = new ArrayList<TableClass>();

    @FXML
    void allSubjectSearch() {
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
        AllSS.setOnAction(e -> allSubjectSearch2(finalAllsubjectcount, coursesFound));
    }

    void allSubjectSearch2(int count, String[] evaluate) {
        double progress = 0;
        double step = (double) 1/count;
        int numcourses = 0;
        for(int j = 0; j < evaluate.length; j++) {
            textfieldSubject.setText(evaluate[j]);
            numcourses += searchCount();
            System.out.println(evaluate[j] + " is done.");
            progress += step;
            progressbar.setProgress(progress);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        textAreaConsole.setText(textAreaConsole.getText() + "\n" + "Total Number of Courses Fetched: " + numcourses);
        AllSS.setOnAction(e -> allSubjectSearch());
    }

    int searchCount() {
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

    @FXML
    void findInstructorSfq() {
    	buttonInstructorSfq.setDisable(true);
        //textAreaConsole.setText(textAreaConsole.getText() + "\n" + testfieldSfqUrl.getText());
    }

    @FXML
    void findSfqEnrollCourse() {

    }

    @FXML
    void filterResults() {
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
                    newline += "Slot " + j + ":" + t + "\n";
                    //For List
                    //System.out.println(t);
                    //datas3.add(new TableClass(c.getTitle().substring(0, 9), t.getType().substring(0, 3), c.getTitle().substring(12), "1"));
                    //TableClass obj = new TableClass(c.getTitle().substring(0, 9), t.getType().substring(0, 3), c.getTitle().substring(12), "1", Color.color(Math.random(), Math.random(), Math.random(), 0.5));
                    TableClass obj = new TableClass(c.getTitle().substring(0, 10), t.getType().substring(0, 3), c.getTitle().substring(12), "1", col, t.getDay());
                    //Have to prevent duplication
                    int flagg = 0;
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
                				}
                			}
                		}
                		
                	}
                	//End of prevention of duplication
                    //if((t.getType()!=sec)&&(t.getType().substring(0,  3)!="Mo ")&&(t.getType().substring(0,  3)!="Tu ")&&(t.getType().substring(0,  3)!="We ")&&(t.getType()!="Th ")&&(t.getType().substring(0,  3)!="Fr ")&&(t.getType().substring(0,  3)!="Sa "))
                    if((t.getType()!=sec)&&(t.getType().length()<11))	
                    {
                    	//System.out.println(t.getType() + "sec: " + sec);
                    	
                    	if(flagg!=1)
                    	{
                    		datas3.add(obj);
                    	}
                    	
                    	//datas3.add(obj);
                    	sec = t.getType();
                    	col = new Color(Math.random(), Math.random(), Math.random(), 0.5);
                    	obj.setColorr(col);
                    	//System.out.println(t.getType() + "sec: " + sec);
                    }
                    
                    if(t.getType().length()>11)
                    {
                    	obj.setLecturesec(prevSecType.substring(0, 3));
                    	obj.setColorr(col);
                    	System.out.println("prev sec: " + prevSecType);
                    }
                    //datas3.add(obj);
                    if(flagg!=1)
                    {
                    	datasAll.add(obj);
                    }
                    //datasAll.add(obj);
                    llist.setItems(datas3);
                    
                    
                    obj.getEnroll().selectedProperty().addListener(new ChangeListener<Boolean>() {
                    	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            //chk2.setSelected(!newValue);
                    		if(newValue==true)
                    		{
                    			System.out.println("Checkbox is checked");
                        		blocks(obj, t);
                        		sameSection(obj, t);
                        		printEnrolled(obj);
                    		}
                    		//else if((newValue==false))
                    		else if((newValue==false)&&(oldValue==true))
                    		{
                    			//Need to remove label from TimeTable and print on console
                    			if(datas3.contains(obj))
                    			{
                    				printEnrolledRemove(obj);
                    			}
                    			//printEnrolledRemove(obj);
                    			removeBlocks(obj, t);			//printEnrolled does not work with this - I think it works now
                    		}
                    		
                        }
                    });
                    
                    prevSecType = sec;
                    //sameSection(obj, t);
                }
                textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
                //createList2();
            }
            ticked.removeAll(ticked);
            for (int k = 0; k < 11; k++) {
                checked[k] = false;
                ticked.add(k, false);
            }
        }
        
        //For timetable
        //timeTable();
    }


    Boolean check0(Course c) {
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

    Boolean check1(Course c) {
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

    Boolean check2(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 0) {
                check = true;
            }
        }
        return check;
    }

    Boolean check3(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 1) {
                check = true;
            }
        }
        return check;
    }

    Boolean check4(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 2) {
                check = true;
            }
        }
        return check;
    }


    Boolean check5(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 3) {
                check = true;
            }
        }
        return check;
    }

    Boolean check6(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 4) {
                check = true;
            }
        }
        return check;
    }


    Boolean check7(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 5) {
                check = true;
            }
        }
        return check;
    }

    //Labs and tutorials
    Boolean check8(Course c) {
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

    //Common Core
    Boolean check9(Course c) {
        return c.getCommonCourse();

    }

    //No Exclusion
    Boolean check10(Course c) {
        if (c.getExclusion() == "null") {
            return true;
        }
        return false;
    }


    @FXML
    void selectAll() {
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
        //createList2();
        //timeTable();
    }

    void deselectAll() {
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
        SelectAll.setOnAction(e -> selectAll());
    }


    @FXML
    void search() {
    	List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
    	for (Course c : v) {
    		String newline = c.getTitle() + "\n";
    		for (int i = 0; i < c.getNumSlots(); i++) {
    			Slot t = c.getSlot(i);
    			newline += "Slot " + i + ":" + t + "\n";
    		}
    		textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
    	}
    	
    	//For timetable
    	//timeTable();
    }
    
    public ObservableList<TableClass> getTableClass()
	{
		ObservableList<TableClass> datas = FXCollections.observableArrayList();
		datas.add(new TableClass("1", "1", "1", "1", Color.color(Math.random(), Math.random(), Math.random(), 0.5), 1));
		return datas;
	}
    
    @FXML
    void createList()
    {
    	System.out.println(":-/");
    	
    	fcCode.setCellValueFactory(new PropertyValueFactory<>("ccode"));
    	flSection.setCellValueFactory(new PropertyValueFactory<>("lecturesec"));
    	fcName.setCellValueFactory(new PropertyValueFactory<>("cname"));
    	flInstructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
    	flEnroll.setCellValueFactory(new PropertyValueFactory<>("enroll"));
    	
    	llist.setItems(getTableClass());
    	/*llist.getColumns().addAll(fcCode, flSection, fcName, flInstructor, flEnroll);*/
    	
    	//System.out.println(":-/");
    	
    }
    
    @FXML
    void createList2()
    {
    	//Better version
    	System.out.println(":-/");
    	
    	fcCode.setCellValueFactory(new PropertyValueFactory<>("ccode"));
    	flSection.setCellValueFactory(new PropertyValueFactory<>("lecturesec"));
    	fcName.setCellValueFactory(new PropertyValueFactory<>("cname"));
    	flInstructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
    	flEnroll.setCellValueFactory(new PropertyValueFactory<>("enroll"));
    	
    	//ObservableList<TableClass> datas2 = FXCollections.observableArrayList(new TableClass("1", "2", "3", "4"));
    	//llist.setItems(datas2);
    }
    
    
    @FXML
    void timeTable()
    {
    	//AnchorPane ap = (AnchorPane)tabList.getContent();
    	
    	for(int i=0; i<llist.getItems().size(); ++i)
    	{
    		TableClass block = llist.getItems().get(i);
    		
    		if(llist.getItems().get(i).getEnroll().isSelected())
    		{
    			System.out.println(":/");
    			
    			//Not Sure
    			
    			AnchorPane ap = (AnchorPane)tabTimetable.getContent();
    	    	//Label randomLabel = new Label("COMP1022\nL1");
    			Label randomLabel = new Label(block.getCcode()+"\n"+block.getLecturesec());
    	    	Random r = new Random();
    	    	double start = (r.nextInt(10) + 1) * 20 + 40;

    	    	randomLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    	    	randomLabel.setLayoutX(600.0);
    	    	randomLabel.setLayoutY(start);
    	    	randomLabel.setMinWidth(100.0);
    	    	randomLabel.setMaxWidth(100.0);
    	    	randomLabel.setMinHeight(60);
    	    	randomLabel.setMaxHeight(60);
    	    
    	    	ap.getChildren().addAll(randomLabel);
    	    	
    	    	//Not sure ends
    		}
    	}
    	
    }
    
    //@FXML
    void blocks(TableClass ts, Slot s)
    {
    	
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();
    	
    	//ts.setLab(new Label(ts.getCcode()+"\n"+ts.getLecturesec()));
    	
    	ts.getLab().setText(ts.getCcode()+"\n"+ts.getLecturesec());
    	
    	ts.getLab().setBackground(new Background(new BackgroundFill(ts.getColorr(), CornerRadii.EMPTY, Insets.EMPTY)));
    	int d = s.getDay();
    	
    	ts.getLab().setLayoutX((d*100.0)+100.0);
    	int stimeh = s.getStartHour();
    	int stimem = s.getStartMinute();
    	//int started = (stimeh*100)+stimem;
    	int started = (stimeh*60)+stimem;
    	
    	ts.getLab().setLayoutY(40.0 + (stimeh-9)*20.0 + (stimem*0.33));
    	int etimeh = s.getEndHour();
    	int etimem = s.getEndMinute();
    	//int ended  = (etimeh*100)+etimem;
    	int ended  = (etimeh*60)+etimem;
    	
    	ts.getLab().setMinWidth(100.0);
    	
    	ts.getLab().setMaxWidth(100.0);
    	
    	int diff = etimem-stimem;
    	int offfset = diff==50 ? 30 : diff==20 ? 15 : 0;
    	
    	
    	int atls = ended-started;
    	
    	
    	
    	//ts.getLab().setMinHeight((20*(atls/100))+((atls%100)*0.33));	
    	//ts.getLab().setMaxHeight((20*(atls/100))+((atls%100)*0.33));
    	
    	ts.getLab().setMinHeight(atls*0.33);	
    	ts.getLab().setMaxHeight(atls*0.33);
    	
    	System.out.println(atls);
    	//System.out.println((20*(atls/100))+((atls%100)*0.33));
    	System.out.println(atls*0.33);
    	
    	if(atls<60)
    	{
    		ts.getLab().setText(ts.getCcode()+" "+ts.getLecturesec());
    	}
    
    	ap.getChildren().addAll(ts.getLab());
    	
    	//sameSection(ts, s);
    }
    
    /*void blocks(TableClass ts, Slot s)
    {
    	
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();
    	//Label randomLabel = new Label("COMP1022\nL1");
		Label randomLabel = new Label(ts.getCcode()+"\n"+ts.getLecturesec());
    	//ts.setLab(new Label(ts.getCcode()+"\n"+ts.getLecturesec()));
    	//Random r = new Random();
    	//double start = (r.nextInt(10) + 1) * 20 + 40;
    	//Color randomColor = new Color(r.nextInt(1), r.nextInt(1), r.nextDouble(), 1.0);

    	//randomLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    	//randomLabel.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random(), 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
    	randomLabel.setBackground(new Background(new BackgroundFill(ts.getColorr(), CornerRadii.EMPTY, Insets.EMPTY)));
    	//ts.getLab().setBackground(new Background(new BackgroundFill(ts.getColorr(), CornerRadii.EMPTY, Insets.EMPTY)));
    	int d = s.getDay();
    	randomLabel.setLayoutX((d*100.0)+100.0);
    	//ts.getLab().setLayoutX((d*100.0)+100.0);
    	int stimeh = s.getStartHour();
    	int stimem = s.getStartMinute();
    	int started = (stimeh*100)+stimem;
    	//System.out.println(started);
    	//double m = stimem==30 ? 10.0 : 0.0;
    	randomLabel.setLayoutY(40.0 + (stimeh-9)*20.0 + (stimem*0.33));
    	//ts.getLab().setLayoutY(40.0 + (stimeh-9)*20.0 + (stimem*0.33));
    	int etimeh = s.getEndHour();
    	int etimem = s.getEndMinute();
    	int ended  = (etimeh*100)+etimem;
    	//System.out.println(ended-started);
    	randomLabel.setMinWidth(100.0);
    	//ts.getLab().setMinWidth(100.0);
    	randomLabel.setMaxWidth(100.0);
    	//ts.getLab().setMaxWidth(100.0);
    	//randomLabel.setMinHeight((30*(etimeh-stimeh))+((float)(((etimem-stimem)/60.0)*15.0)));
    	//randomLabel.setMaxHeight((30*(etimeh-stimeh))+((float)(((etimem-stimem)/60.0)*15.0)));
    	int diff = etimem-stimem;
    	int offfset = diff==50 ? 30 : diff==20 ? 15 : 0;
    	//offfset = diff==20 ? 15 : 0;
    	
    	//randomLabel.setMinHeight((30*(etimeh-stimeh))+((float)(((etimem-stimem))*0.15)));	//Better than 1st time
    	//randomLabel.setMaxHeight((30*(etimeh-stimeh))+((float)(((etimem-stimem))*0.15)));
    	
    	//randomLabel.setMinHeight((30*(etimeh-stimeh))+offfset);	//Better than 1st time
    	//randomLabel.setMaxHeight((30*(etimeh-stimeh))+offfset);
    	
    	int atls = ended-started;
    	
    	randomLabel.setMinHeight((20*(atls/100))+((atls%100)*0.33));	
    	randomLabel.setMaxHeight((20*(atls/100))+((atls%100)*0.33));
    	
    	//ts.getLab().setMinHeight((20*(atls/100))+((atls%100)*0.33));	
    	//ts.getLab().setMaxHeight((20*(atls/100))+((atls%100)*0.33));
    	
    	System.out.println(atls);
    	System.out.println((20*(atls/100))+((atls%100)*0.33));
    
    	ap.getChildren().addAll(randomLabel);
    	//ap.getChildren().addAll(ts.getLab());
    	
    	//sameSection(ts, s);
    }*/
    
    @FXML
    void sameSection(TableClass ts, Slot s)
    {
    	//System.out.println(datasAll);
    	for(int i=0; i<datasAll.size(); ++i)
    	{
    		//System.out.println(datasAll.get(i).getCcode() + " " + datasAll.get(i).getLecturesec());
    		//System.out.println("Same section fn working.2?");
    		if((datasAll.get(i).getCcode().equals(ts.getCcode()))&&(datasAll.get(i).getLecturesec().equals(ts.getLecturesec())))
    		{
    			//System.out.println("Same section fn working?");
    			if((datasAll.get(i).getEnroll().isSelected()==false)&&((ts.getEnroll().isSelected())==true))
    			{
    				datasAll.get(i).getEnroll().setSelected(true);
    				System.out.println("Same section");
    				//datasAll.get(i).setColorr(ts.getColorr());
    			}
    		}
    	}
    }
    
    //It is still taking COMP1022P L1 and COMP1022Q as the same - fixed this
    
    void printEnrolled(TableClass ts)
    {
    	/*String enrolledSec = "The following sections are enrolled:";
    	
    	for(int i=0; i<datas3.size(); ++i)
    	{
    		if(datas3.get(i).getEnroll().isSelected())
    		{
    			enrolledSec += "\n" + datas3.get(i).getCcode() + " " + datas3.get(i).getLecturesec();
    		}
    	}
    	
    	textAreaConsole.setText(enrolledSec + "\n" + textAreaConsole.getText());*/
    	
    	if(textAreaConsole.getText().substring(0, 36).equals("The following sections are enrolled:"))
    	{
    		String consoleCurr = textAreaConsole.getText();
    		String newstr = "";
    		
    		for(int i=37; i<100; ++i)		//change 100 later
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
    		
    		System.out.println("newstr" + newstr);
    		System.out.println(ts.getCcode() + " " + ts.getLecturesec());
    		
    		if((ts.getCcode() + " " + ts.getLecturesec()).equals(newstr)==false)
    		{
    			textAreaConsole.setText(textAreaConsole.getText().substring(0, 37) + ts.getCcode() + " " + ts.getLecturesec() + "\n" + textAreaConsole.getText().substring(37));
    		}
    		
    		//textAreaConsole.setText(textAreaConsole.getText().substring(0, 37) + ts.getCcode() + " " + ts.getLecturesec() + "\n" + textAreaConsole.getText().substring(37));
    	}
    	else
    	{
    		textAreaConsole.setText("The following sections are enrolled:" + "\n" + ts.getCcode() + " " + ts.getLecturesec() +"\n" + textAreaConsole.getText());
    	}
    }
    
    void printEnrolledRemove(TableClass ts)
    {
    	String match = ts.getCcode() + " " + ts.getLecturesec();
    	String resultant = "";
    	int end = 0;
    	
    	for(int i=37; i<1000; i=i+15)		//change 1000 later
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
    	//if((textAreaConsole.getText().charAt(end+14)=='\n')&&(textAreaConsole.getText().charAt(end+15)=='\n')&&(end==37))
    	//if((end==37)&&(textAreaConsole.getText().charAt(end+14)=='\n'))
    	if((textAreaConsole.getText().charAt(end+14)=='\n')&&(textAreaConsole.getText().charAt(end+15)=='\n')&&(end==37))
    	{
    		textAreaConsole.setText(resultant.substring(1));
    	}
    	else
    	{
    		textAreaConsole.setText("The following sections are enrolled:" + resultant);
    	}
    	//textAreaConsole.setText("The following sections are enrolled:" + resultant);
    }
    
    void lostEnrollment()
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
    
    void removeBlocks(TableClass ts, Slot s)
    {
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();
    	
    	//ap.getChildren().remove(ts.getLab());
    	
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
    		//something here
    	}
    }

}
