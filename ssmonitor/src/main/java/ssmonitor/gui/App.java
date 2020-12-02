package ssmonitor.gui;
import ssmonitor.sysinfo.RTExecutors;
import ssmonitor.file.ParseConf;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;


public class App extends Application {
    private ArrayList<Node> components;

    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ssmonitor");
        
        
        ParseConf.readConf("./src/main/resources/exampleConf.conf");
        components = ParseConf.getNodes();

        VBox vbox = new VBox();
        
        for (Node component : components) {
            vbox.getChildren().add(component);
        }
        
        vbox.setPadding(new Insets(0));
        vbox.setSpacing(0);
        Scene scene = new Scene(vbox, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // fix scheduledexecutor
        
        
        

        
    }
    @Override
    public void stop() throws Exception {
        System.out.println("stopping");
        RTExecutors.shutdownAll();
        super.stop();
        
        
        
    }
}
