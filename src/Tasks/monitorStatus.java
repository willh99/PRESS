/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tasks;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
    InetAddress host;
    
    private void setupClient(InetAddress h, int port)
    {
        
    }
    
    @Override
    public void run() {
       
    }
    
}
