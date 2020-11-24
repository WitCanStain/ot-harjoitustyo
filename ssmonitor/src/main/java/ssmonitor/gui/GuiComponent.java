
package ssmonitor.gui;

import ssmonitor.sysinfo.SysInfo;

import javafx.application.Platform;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


import javafx.scene.layout.Pane;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import java.util.concurrent.TimeUnit;



public class GuiComponent {
    private ScheduledExecutorService scheduledExecutorService;
    
    public GuiComponent() {
        
    }
    public LineChart<String, Number> lineChart() {
        
        
        
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

        executorService(series);
        
        
        
        return lineChart;
        
    }
    
    public void executorService(XYChart.Series<String, Number> series) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SS");

        
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        
        int WINDOW_SIZE = 10;
        
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                // get current time
                Date now = new Date();
                // put random number with current time
                series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), SysInfo.getCpuLoad()));
                
                if (series.getData().size() > WINDOW_SIZE)
                    series.getData().remove(0);
            });
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
    
    public void shutdown() {
        scheduledExecutorService.shutdownNow();
    }
}
