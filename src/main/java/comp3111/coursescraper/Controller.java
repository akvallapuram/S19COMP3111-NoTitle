package comp3111.coursescraper;


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
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;
import java.util.regex.*;

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

        textAreaConsole.setText("");
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
                for (int j = 0; j < c.getNumSlots(); j++) {
                    Slot t = c.getSlot(j);
                    newline += "Slot " + j + ":" + t + "\n";
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
    }

}
