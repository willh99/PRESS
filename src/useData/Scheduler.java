/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useData;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import Tasks.dataFetch;
import Tasks.monitorStatus;

/**
 *
 * @author will
 */
public class Scheduler {
    
    private static Timer testTimer;
    private static Timer statusTimer;
    private static Timer dataTimer;
    
    public static void fetchData()
    {
        // Schedule timer to draw price data every day at midnight
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        
        dataTimer = new Timer();
        dataTimer.schedule(new dataFetch(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }
    
    public static void getStatus()
    {
        // Schedule timer to try and fetch sensor data from server every minute
        statusTimer = new Timer();
        statusTimer.scheduleAtFixedRate(new monitorStatus(Globals.getHostName(), Globals.getHostPort()), 0, 10000);
    }
    
    public static void testTiming()
    {
        Date firstTime = new Date();
        
        testTimer = new Timer();
        testTimer.scheduleAtFixedRate(new MyTimerTask(), firstTime, 5000);
    }
    
    
    // Ends the specified task gracefully using cancel()
    public static void endTask(String taskName)
    {
        switch (taskName) {
            case "STATUS":
                statusTimer.cancel();
                statusTimer.purge();
                break;
            case "DATA":
                dataTimer.cancel();
                dataTimer.purge();
                break;
            case "TEST":
                testTimer.cancel();
                testTimer.purge();
                break;
            default:
                break;
        }
    }
    
    // Ends the specified task gracefully and restarts
    // Usefully when Globals (such as host address) have been changed
    public static void updateTask(String taskName)
    {
        switch (taskName) {
            case "STATUS":
                statusTimer.cancel();
                statusTimer.purge();
                getStatus();
                break;
            case "DATA":
                dataTimer.cancel();
                dataTimer.purge();
                fetchData();
                break;
            case "TEST":
                testTimer.cancel();
                testTimer.purge();
                testTiming();
                break;
            default:
                break;
        }
    }

}


// A test class to check the funcationality of the timer.
// Simpily prints out the current time when called.
class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        Date d = new Date();
        System.out.println("Time is currently: " + d.toString());
    }
}
