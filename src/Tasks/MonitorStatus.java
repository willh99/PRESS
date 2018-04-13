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
package Tasks;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import useData.ClientConnect;
import useData.Globals;

/**
 *
 * @author will
 */
public class MonitorStatus extends TimerTask {

    ClientConnect client;
    
    public MonitorStatus(InetAddress host, int port) throws IOException{
        client = new ClientConnect(host, port, Globals.getTimeout());
    }
    
    @Override
    public void run() {
        try {
            client.getFile("v_log.json");
            sleep(20);
            client.getFile("t_log.json");
            sleep(20);
            client.checkStatus();
            sleep(20);
            client.getFile("bt_log.json");
            sleep(20);
            client.getFile("profit.json");
        } catch (InterruptedException ex) {
            Logger.getLogger(MonitorStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}