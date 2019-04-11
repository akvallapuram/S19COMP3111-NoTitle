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

import javax.swing.*;
import java.util.Random;
import java.util.List;
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
    private Button AllSS;

    @FXML
    void allSubjectSearch() {
        //TODO need to add different functionality for different clicks
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
        while (i < allSubjects.length - 1) {
            try {
                textfieldSubject.setText(allSubjects[i]);
                search(); //TODO bug that doesn't set console output after each successful scrape
                System.out.println(allSubjects[i] + " is done.");
                i++;
            } catch (Exception e) {
                i++;
            }
        }
        //TODO NEED TO DEAL WITH NUMBER OF COURSES that is COMP3111 COMP 3311 two different courses
        textAreaConsole.setText(textAreaConsole.getText() + "\n" + "Total Number of Courses Fetched: ");
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
        String[] checked = new String[10];
        for (int i = 0; i < 10; i++) {
            checked[i] = "0";
        }
        textAreaConsole.setText("");
        if (AM.isSelected()) {
            checked[0] = "AM";
        }
        if (PM.isSelected()) {
            checked[1] = "PM";
        }
        if (Mon.isSelected()) {
            checked[2] = "Mon";
        }
        if (Tues.isSelected()) {
            checked[3] = "Tues";
        }
        if (Wed.isSelected()) {
            checked[4] = "Wed";
        }
        if (Thrs.isSelected()) {
            checked[5] = "Thrs";
        }
        if (Fri.isSelected()) {
            checked[6] = "Fri";
        }
        if (LT.isSelected()) {
            checked[7] = "LT";
        }
        if (CC.isSelected()) {
            checked[8] = "CC";
        }
        if (NE.isSelected()) {
            checked[9] = "NE";
        }
        minisearch(checked);
    }

    void minisearch(String[] constraints) {
        List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
        for (Course c : v) {
            Boolean containsAtLeastOne = false;
            String newline= c.getTitle() + "\n";
            //TODO make each constraint a seperate mini method for and then Loop over contraints AND logic
            if(constraints[2] == "Mon") {
                String result = checkMonday(c);
                if (!result.equals("")) {
                    containsAtLeastOne = true;
                    newline += result;
                }
            }
            if(containsAtLeastOne) {
                textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
            }
        }
    }

    String checkMonday(Course c) {
        String toReturn = "";
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.toString().contains("Mo")) {
                toReturn += "Slot " + i + ":" + t + "\n";
            }
        }
        return toReturn;
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
        SelectAll.setText("Deselect All");
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

    	//Add a random block on Saturday
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();
    	Label randomLabel = new Label("COMP1022\nL1");
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

    }

}
