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
import java.util.logging.Level;
import java.util.logging.Logger;
import Tasks.dataFetch;
import Tasks.monitorStatus;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
        try {
            InetAddress host = InetAddress.getByName("127.0.0.1");
            mytimer.scheduleAtFixedRate(new monitorStatus(host, 5555), 0, 5000);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
