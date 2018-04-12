/*
 * Copyright (C) 2018 Will Horowitz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package useData;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import Tasks.*;

/**
 *
 * @author will
 */
public class Scheduler {
    
    private static Timer testTimer;
    private static Timer statusTimer;
    private static Timer dataTimer;
    private static Timer analysisTimer;
    
    // Schedule timer to draw price data every day at midnight
    public static void fetchData()
    {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        
        dataTimer = new Timer();
        dataTimer.schedule(new dataFetch(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }
    
    // Schedule timer to try and fetch sensor data from server every minute
    public static void getStatus()
    {
        statusTimer = new Timer();
        statusTimer.scheduleAtFixedRate(new monitorStatus(Globals.getHostName(), Globals.getHostPort()), 0, 60000);
    }
    
    // Schedule timer to do price analysis and generate status every three minutes
    public static void priceAnalysis()
    {
        analysisTimer = new Timer();
        analysisTimer.scheduleAtFixedRate(new calculateStatus(), 0, 180000);
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
            case "ANALYSIS":
                analysisTimer.cancel();
                analysisTimer.purge();
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
            case "ANALYSIS":
                analysisTimer.cancel();
                analysisTimer.purge();
                priceAnalysis();
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
