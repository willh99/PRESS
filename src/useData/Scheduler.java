/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useData;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author willy
 */
public class Scheduler {
    
    private static Timer timer;
    
    public static void testTiming()
    {
        Date firstTime = new Date();
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), firstTime, 1000);
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
