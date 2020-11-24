package ssmonitor.gui;

import ssmonitor.file.ParseConf;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;


public class App extends Application {
    

    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ssmonitor");
        
        
        ParseConf.readConf("./src/main/resources/exampleConf.conf");
        ArrayList<Node> nodes = ParseConf.getNodes();

        GridPane gridpane = new GridPane();
        
        for (int i = 0; i < nodes.size(); i++) {
            gridpane.add(nodes.get(i), 0, i, 1, 1);
        }
        
        Scene scene = new Scene(gridpane, 300, 300);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        

        
    }
    @Override
    public void stop() throws Exception {
        super.stop();
        
        
    }
}
