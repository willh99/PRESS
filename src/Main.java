
import Visual.PRESS_hud;
import useData.Scheduler;
import useData.Globals;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Set Global variables //
        Globals.setHostName("10.199.41.199");
        Globals.setHostPort(5555);
        Globals.setTimeout(4000);

        // Create the UI //
        PRESS_hud hud = new PRESS_hud();
        hud.setVisible(true);
        
        /* Next initialize the scheduler functions
         * i.e. get sensor data from server & price data from the Internet
         */
        //Scheduler.testTiming();
        Scheduler.getStatus();
    }
    
}
