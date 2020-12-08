package ssmonitor.sysinfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ProgressBar;
import java.util.ArrayList;

// this class will create ScheduledExecutorServices as requested to update node data in Real Time
// it will also shut down each thread when the user closes the program.
public class RTExecutors {
    
    final private static ArrayList<ScheduledExecutorService> scheduledExecutorServices = new ArrayList<ScheduledExecutorService>();
    
    
    
    public static void executorService(XYChart.Series<String, Number> series, int sysInfoCall, int refreshRate) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SS");

        
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        
        int windowSize = 10;
        
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                
                Date now = new Date();
                double value = (double) SysInfo.getSysInfo(sysInfoCall);
                series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), value));
                
                if (series.getData().size() > windowSize) {
                    series.getData().remove(0);
                }
            });
        }, 0, refreshRate, TimeUnit.MILLISECONDS);
        
        scheduledExecutorServices.add(scheduledExecutorService);
    }
    
    public static void executorService(ProgressBar progressBar, int sysInfoCall, int refreshRate) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                
                double value = (double) SysInfo.getSysInfo(sysInfoCall);
                progressBar.setProgress(value);
                
            });
        }, 0, refreshRate, TimeUnit.MILLISECONDS);
        
        scheduledExecutorServices.add(scheduledExecutorService);
    }
    

    public static void shutdownAll() {
        scheduledExecutorServices.forEach((component) -> {
            component.shutdown();
        });
    }
    
    public static ArrayList<ScheduledExecutorService> getExecutorServices() {
        return scheduledExecutorServices;
    }

}

