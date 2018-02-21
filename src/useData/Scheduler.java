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
    
    private static Timer timer;
    
    public static void fetchData()
    {
        // Schedule timer to draw price data every day at midnight
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        
        Timer t1 = new Timer();
        t1.schedule(new dataFetch(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }
    
    public static void getStatus()
    {
        // Schedule timer to try and fetch sensor data from server every minute
        Timer mytimer = new Timer();
        mytimer.scheduleAtFixedRate(new monitorStatus(Globals.getHostName(), Globals.getHostPort()), 0, 30000);
    }
    
    public static void testTiming()
    {
        Date firstTime = new Date();
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), firstTime, 5000);
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
