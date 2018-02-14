/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tasks;

import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import useData.ClientConnect;

/**
 *
 * @author willy
 */
public class monitorStatus extends TimerTask {

    ClientConnect client;
    
    public monitorStatus(InetAddress host, int port){
        client = new ClientConnect(host, port);
    }
    
    @Override
    public void run() {
        try {
            client.getFile("v_log.json");
            sleep(5);
            client.checkStatus();
        } catch (InterruptedException ex) {
            Logger.getLogger(monitorStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
