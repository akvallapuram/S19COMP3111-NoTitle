/**
 *
 * You might want to uncomment the following code to learn testFX. Sorry, no tutorial session on this.
 *
 */
package comp3111.coursescraper;

import static org.junit.Assert.*;

import javafx.scene.control.CheckBox;
import org.junit.Test;
//import org.testfx.api.FxRobotInterface;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.TableView ;
import javafx.scene.control.CheckBox ;


public class FxTest extends ApplicationTest {

	private Scene s;
	private Button button;
	private Controller ctr;
	private CheckBox cb;

	@Override
	public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/ui.fxml"));
   		VBox root = (VBox) loader.load();
   		Scene scene =  new Scene(root);
   		stage.setScene(scene);
   		stage.setTitle("Course Scraper");
		stage.show();
		ctr = loader.getController(); 
   		s = scene;
	}


	//@Test
//	public void testTabSFQButton() {
//		clickOn("#tabSfq");
//		//FxRobotInterface click = clickOn("#buttonInstructorSfq");
//		clickOn("#buttonInstructorSfq");
//		Button b = (Button)s.lookup("#buttonInstructorSfq");
//		sleep(1000);
//		assertFalse(b.isDisabled());
//	}

@Test
public void testList()
{
	//clickOn("#buttonSearch");
	clickOn("#tabFilter");
	sleep(1000);
	clickOn("#CC");
	sleep(1000);
	clickOn("#tabList");
	TableView<TableClass> tabl = ctr.getTable();
	TableClass obj = tabl.getItems().get(1);
	//obj.getEnroll().setSelected(true);
	cb = obj.getEnroll();
	//cb.setSelected(true);
	sleep(1000);
	assertEquals(cb.isSelected(), false);
}

//	@Test
//	public void testAM() {
//		clickOn("#tabFilter");
//		FxRobotInterface click = clickOn("#AM");
//		CheckBox cb = (CheckBox)s.lookup("#AM");
//		assertTrue(cb.isSelected());
//	}

	@Test
	public void testSelectAll() {
		clickOn("#tabFilter");
		clickOn("#SelectAll");
		Button b = (Button) s.lookup("#SelectAll");
		sleep(100);
		assertEquals(b.getText(), "Deselect All");
	}

	@Test
	public void testDeselectAll() {
		clickOn("#tabFilter");
		clickOn("#SelectAll");
		clickOn("#SelectAll");
		Button b = (Button) s.lookup("#SelectAll");
		sleep(100);
		assertEquals(b.getText(), "Select All");
	}

}
