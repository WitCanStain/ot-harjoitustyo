
package ssmonitor.gui;


import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.control.ProgressBar;

import ssmonitor.sysinfo.RTExecutors;

// this class contains various methods for creating and returning javafx nodes
public class RTNodes {
    
    public static LineChart<String, Number> lineChart(String sysInfoCall) {
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
        RTExecutors.executorService(series, sysInfoCall);
        return lineChart;
        
    }
    
    public static ProgressBar progressBar(String sysInfoCall) {
        
        ProgressBar progressBar = new ProgressBar();
        
        progressBar.setPrefWidth(300);
        RTExecutors.executorService(progressBar, sysInfoCall);
        return progressBar;
    }
    
    
    
}
