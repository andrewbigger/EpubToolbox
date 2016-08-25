package support;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

public class ToolboxUITestHarness extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/Toolbox.fxml"));
        Parent root = loader.load();
        
        Object ctrl = loader.getController();
        
        Scene testScene = new Scene(root);
        testScene.getStylesheets().add("fxml/Toolbox.css");
        
        stage.setScene(testScene);
        stage.show();
    }
    
}
