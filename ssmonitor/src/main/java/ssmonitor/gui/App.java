package ssmonitor.gui;
import ssmonitor.sysinfo.RTExecutors;
import ssmonitor.file.ParseConf;
import java.util.ArrayList;
import java.util.List;
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
        
        Parameters params = getParameters();
        List<String> list = params.getRaw();
        System.out.println(list.size());
        String fileName;
        if (list.size() == 0) {
            fileName = "./src/main/resources/exampleConf.conf";
        } else {
            fileName = list.get(0);
        }
        
        
        //"./src/main/resources/exampleConf.conf"
        ParseConf.readConf(fileName);
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
        
        
        
        

        
    }
    @Override
    public void stop() throws Exception {
        System.out.println("stopping");
        RTExecutors.shutdownAll();
        super.stop();
        
        
        
    }
}

