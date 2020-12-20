package ssmonitor.gui;
import ssmonitor.sysinfo.RTExecutors;
import ssmonitor.sysinfo.SysInfo;
import ssmonitor.file.ParseConf;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ssmonitor.sysinfo.SysInfo;


public class App extends Application {
    private ArrayList<Node> components;

    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ssmonitor");
        
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setOpacity(0.7);

        
        Parameters params = getParameters();
        List<String> list = params.getRaw();
        
        String fileName;
        if (list.isEmpty()) {
            fileName = "./src/main/resources/exampleConf.conf";
        } else {
            fileName = list.get(0);
        }
        
        MenuBar menuBar = new MenuBar();
        MenuItem menuItem = new MenuItem("New component");
        Menu menu = new Menu("Create");
        menu.getItems().add(menuItem);
        
        menuBar.getMenus().add(menu);
        
        ParseConf.readConf(fileName);
        components = GuiComponent.getNodes();

        VBox vbox = new VBox(menuBar);
        vbox.setStyle("-fx-box-border: transparent;");
        
        
        
        Text introText = new Text();
        if (!components.isEmpty()) {
            components.forEach((component) -> {
                vbox.getChildren().add(component);
            });
        } else {
            introText.setText("You don't have any components. Make some new ones from the menu!");
            vbox.getChildren().add(introText);
        }
        menu.setOnAction(e -> {
            createNewComponent(vbox, introText);
        });
        vbox.setPadding(new Insets(0));
        vbox.setSpacing(0);
        Scene scene = new Scene(vbox, 500, 500);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        

        
    }
    
    public void createNewComponent(VBox primaryVbox, Text introText) {
        Stage newStage = new Stage();
        newStage.setTitle("New component");
        VBox vbox = new VBox();
        
        Label sceneLabel = new Label("Please specify the component parameters.");
        
        
        ComboBox componentOptions = new ComboBox();
        componentOptions.getItems().addAll("cpu_usage", "drive_memory_percentage", "drive_memory_total", "ram_usage_percentage", "ram_free", "ram_total", "os.name", "os.version", "user.name", "os.arch");
        Label textLabel = new Label("System information: ");
        textLabel.setMinWidth(200);
        HBox labelledComponentOptions = new HBox(textLabel, componentOptions);
        
        ComboBox presentationTypeOptions = new ComboBox();
        presentationTypeOptions.getItems().addAll("linechart", "progressbar", "text");
        textLabel = new Label("Representation type: ");
        textLabel.setMinWidth(200);
        HBox labelledPresentationTypeOptions = new HBox(textLabel, presentationTypeOptions);
        
        TextField refreshRate = new TextField();
        textLabel = new Label("Refresh rate (default 500): ");
        textLabel.setMinWidth(200);
        HBox labelledRefreshRate = new HBox(textLabel, refreshRate);
        
        textLabel = new Label("Text label: ");
        textLabel.setMinWidth(200);
        TextField textField = new TextField();
        
        HBox labelledTextLabel = new HBox(textLabel, textField);
        
        CheckBox checkBox = new CheckBox("Modify configuration file to save changes?");
        
        Button okButton = new Button("OK");
        okButton.setOnAction(actionEvent -> {
            onPressingOK(componentOptions, presentationTypeOptions, refreshRate, textField, checkBox, newStage, primaryVbox, introText);
            
        });
        
        vbox.getChildren().addAll(sceneLabel, labelledComponentOptions, labelledPresentationTypeOptions, labelledRefreshRate, labelledTextLabel, checkBox, okButton);
        vbox.setPadding(new Insets(5));
        vbox.setSpacing(10);
        
        Scene scene = new Scene(vbox);
        newStage.setScene(scene);
        newStage.show();
        
    }
    
    public void onPressingOK(ComboBox componentOptions, ComboBox presentationTypeOptions, TextField refreshRate, TextField textField, CheckBox checkBox, Stage newStage, VBox primaryVbox, Text introText) {
        String chosenComponent = (String) componentOptions.getValue();
        String chosenPresentationType = (String) presentationTypeOptions.getValue();
        String chosenRefreshRate = refreshRate.getText();
        String chosenTextLabel = textField.getText();
        boolean modifyFile = checkBox.isSelected();
        if (Objects.isNull(chosenComponent) && chosenTextLabel.isEmpty()) {
            componentOptions.setStyle("-fx-border-color: red;");
            return;
        }
        if (Objects.isNull(chosenPresentationType) && chosenTextLabel.isEmpty()) {
            presentationTypeOptions.setStyle("-fx-border-color: red;");
            return;
        }
        
        if (!chosenRefreshRate.matches("^$|[0-9]+")) {
            refreshRate.setStyle("-fx-border-color: red;");
            return;
        }
        if (GuiComponent.getNodes().isEmpty()) {
            primaryVbox.getChildren().remove(introText);
        }
        
        String[] guiParameters = {chosenComponent, chosenPresentationType, chosenRefreshRate, chosenTextLabel};
        
        ParseConf.parseGuiParameters(guiParameters, modifyFile);
        
        primaryVbox.getChildren().add(GuiComponent.getLastNode());
        newStage.hide();
    }
    
    @Override
    public void stop() throws Exception {
        System.out.println("stopping");
        RTExecutors.shutdownAll();
        
        
        
        
    }
}

