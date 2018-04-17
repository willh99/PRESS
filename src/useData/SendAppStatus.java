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

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author will
 * 
 * This class provides a multi-threaded approach to sending
 * a file over the network. This is useful when the user
 * manually overrides the status of the system. This way,
 * manual overrides to not freeze the GUI when waiting to
 * connect.
 */
public class SendAppStatus implements Runnable {

    private ClientConnect c;
    private Thread t;
    
    @Override
    public void run() {
        try {
            c = new ClientConnect(Globals.getHostName(), Globals.getHostPort(), Globals.getTimeout());
            c.sendFile("appstatus.json");
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "Sent appstatus to server on: {0}\n", new Date());
        } catch (IOException ex) {
            Logger.getLogger(SendAppStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start() {
        if(t == null){
            t = new Thread(this);
            t.start();
        }
    }
    
}
