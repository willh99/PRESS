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
import Visual.PRESS_hud;
import useData.Scheduler;
import useData.Globals;

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
        Globals.setHostName("10.199.41.127");
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
        Scheduler.fetchData();
        Scheduler.priceAnalysis();
    }
    
}
