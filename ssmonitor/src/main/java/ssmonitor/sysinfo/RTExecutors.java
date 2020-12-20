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
import javafx.scene.text.Text;


/**
 * this class will create ScheduledExecutorServices as requested to update node data in Real Time.
 * It will also shut down each thread when the user closes the program.
 * @author ruby
 */
public class RTExecutors {
    
    final private static ArrayList<ScheduledExecutorService> scheduledExecutorServices = new ArrayList<ScheduledExecutorService>();
    
    
    /**
     * Creates a new thread to update chart data.
     * @param series The data the service is working with
     * @param sysInfoCall The information the user wants to see
     * @param refreshRate How often the information should be updated
     */
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
    
    /**
     * Creates a new thread to update bar data.
     * @param progressBar The progressBar the thread updates
     * @param sysInfoCall The information the user wants to see
     * @param refreshRate How often the information should be updated
     */
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
    
    public static void executorService(Text text, int sysInfoCall, int refreshRate) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                
                String value = String.valueOf(SysInfo.getSysInfo(sysInfoCall));
                text.setText(value);
                
            });
        }, 0, refreshRate, TimeUnit.MILLISECONDS);
        
        scheduledExecutorServices.add(scheduledExecutorService);
    }
    
    /**
     * Shuts down all the threads.
     */
    public static void shutdownAll() {
        scheduledExecutorServices.forEach((component) -> {
            component.shutdown();
        });
    }
    
    
    public static ArrayList<ScheduledExecutorService> getExecutorServices() {
        return scheduledExecutorServices;
    }

}

