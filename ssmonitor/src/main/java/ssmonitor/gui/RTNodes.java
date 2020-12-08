
package ssmonitor.gui;


import java.util.Objects;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.control.ProgressBar;

import ssmonitor.sysinfo.RTExecutors;

/**
 * This class contains various methods for creating and returning javaFX nodes
 * @author ruby
 */
public class RTNodes {
    
    /**
     * Creates a lineChart according to given parameters.
     * @param sysInfoCall The information the user wants to see
     * @param refreshRate How often the information should be updated
     * @return The lineChart created according to parameters.
     */
    public static LineChart<String, Number> lineChart(int sysInfoCall, int refreshRate) {
        
        if (Objects.isNull(refreshRate)) {
            refreshRate = 500;
        }
        final CategoryAxis xAxis = new CategoryAxis(); 
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setAnimated(false); 
        yAxis.setAnimated(false);
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setAnimated(false);
        yAxis.setAutoRanging(true);
        yAxis.setForceZeroInRange(false);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        // We want the line graph and nothing else
        lineChart.setLegendVisible(false);
        lineChart.setCreateSymbols(false);
        lineChart.setHorizontalGridLinesVisible(false);
        lineChart.setVerticalGridLinesVisible(false);
        lineChart.setHorizontalZeroLineVisible(false);
        Pane parent = (Pane) xAxis.getParent();
        parent.getChildren().removeAll(xAxis);  
        parent = (Pane) yAxis.getParent();
        parent.getChildren().removeAll(yAxis);
        lineChart.getData().add(series);
        RTExecutors.executorService(series, sysInfoCall, refreshRate);
        return lineChart;
        
    }
    /**
     * Creates a progressBar according to given parameters.
     * @param sysInfoCall The information the user wants to see
     * @param refreshRate How often the information should be updated
     * @return The progressBar created according to parameters.
     * 
     */
    public static ProgressBar progressBar(int sysInfoCall, int refreshRate) {
        if (Objects.isNull(refreshRate)) {
            refreshRate = 500;
        }
        
        ProgressBar progressBar = new ProgressBar();
        
        progressBar.setPrefWidth(300);
        RTExecutors.executorService(progressBar, sysInfoCall, refreshRate);
        return progressBar;
    }
    
    
    
}
