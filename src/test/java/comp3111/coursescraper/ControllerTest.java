package comp3111.coursescraper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import comp3111.coursescraper.*;

public class ControllerTest {

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


    @Test
    public void testCheck0() {
        Course i = new Course();
        Slot s = new Slot();
        s.setStart("12:01PM");
        i.addSlot(s);


        Course i1 = new Course();
        Slot s1 = new Slot();
        s1.setStart("11:59AM");
        i1.addSlot(s1);

        assertFalse(Controller.check0(i));
        assertTrue(Controller.check0(i1));
        assertTrue(i.getNumSlots() > 0);

    }

    @Test
    public void testCheck1() {
        Course i = new Course();
        Slot s = new Slot();
        s.setStart("12:01PM");
        i.addSlot(s);


        Course i1 = new Course();
        Slot s1 = new Slot();
        s1.setStart("11:59AM");
        i1.addSlot(s1);

        assertTrue(Controller.check1(i));
        assertFalse(Controller.check1(i1));
        assertTrue(i.getNumSlots() > 0);

    }

    @Test
    public void testCheck2() {
        Course i = new Course();
        Slot s = new Slot();
        s.setDay(0);
        i.addSlot(s);

        Course i1 = new Course();
        Slot s1 = new Slot();
        s1.setDay(1);
        i1.addSlot(s1);

        assertTrue(Controller.check2(i));
        assertFalse(Controller.check2(i1));
        assertTrue(i.getNumSlots() > 0);
    }

    @Test
    public void testCheck3() {
        Course i = new Course();
        Slot s = new Slot();
        s.setDay(1);
        i.addSlot(s);

        Course i1 = new Course();
        Slot s1 = new Slot();
        s1.setDay(2);
        i1.addSlot(s1);

        assertTrue(Controller.check3(i));
        assertFalse(Controller.check3(i1));
        assertTrue(i.getNumSlots() > 0);
    }

    @Test
    public void testCheck4() {
        Course i = new Course();
        Slot s = new Slot();
        s.setDay(2);
        i.addSlot(s);

        Course i1 = new Course();
        Slot s1 = new Slot();
        s1.setDay(3);
        i1.addSlot(s1);

        assertTrue(Controller.check4(i));
        assertFalse(Controller.check4(i1));
        assertTrue(i.getNumSlots() > 0);
    }

    @Test
    public void testCheck5() {
        Course i = new Course();
        Slot s = new Slot();
        s.setDay(3);
        i.addSlot(s);

        Course i1 = new Course();
        Slot s1 = new Slot();
        s1.setDay(0);
        i1.addSlot(s1);

        assertTrue(Controller.check5(i));
        assertFalse(Controller.check5(i1));
        assertTrue(i.getNumSlots() > 0);
    }

    @Test
    public void testCheck6() {
        Course i = new Course();
        Slot s = new Slot();
        s.setDay(4);
        i.addSlot(s);

        Course i1 = new Course();
        Slot s1 = new Slot();
        s1.setDay(0);
        i1.addSlot(s1);

        assertTrue(Controller.check6(i));
        assertFalse(Controller.check6(i1));
        assertTrue(i.getNumSlots() > 0);
    }

    @Test
    public void testCheck7() {
        Course i = new Course();
        Slot s = new Slot();
        s.setDay(5);
        i.addSlot(s);

        Course i1 = new Course();
        Slot s1 = new Slot();
        s1.setDay(0);
        i1.addSlot(s1);

        assertTrue(Controller.check7(i));
        assertFalse(Controller.check7(i1));
        assertTrue(i.getNumSlots() > 0);
    }

    @Test
    public void testCheck8() {
        Course i = new Course();
        Slot s = new Slot();
        s.setType("LA 12");
        Slot s1 = new Slot();
        s1.setType("T5");
        i.addSlot(s);
        i.addSlot(s1);

        assertTrue(Controller.check8(i));
        assertTrue(i.getNumSlots() > 0);
    }


    @Test
    public void testCheck10() {
        Course i = new Course();
        i.setExclusion("null");
        assertTrue(Controller.check10(i));
        Course i1 = new Course();
        i1.setExclusion("COMP 3111");
        assertFalse(Controller.check10(i1));
    }


    @Test
    public void allSubjectSearch2() {

    }

    @Test
    public void searchCount() {
    }

    @Test
    public void findInstructorSfq() {
    }

    @Test
    public void findSfqEnrollCourse() {
    }

    @Test
    public void filterResults() {
    }

}



