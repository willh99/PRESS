
import java.io.IOException;
import java.net.InetAddress;
import useData.ClientConnect;

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
    public static void main(String[] args) throws IOException {
        // Start by creating the UI
        PRESS_hud hud = new PRESS_hud();
        hud.setVisible(true);
        
        //InetAddress host = InetAddress.getByName("127.0.0.1");
        //ClientConnect Client = new ClientConnect(host, 5555);
        //Client.chat();
    }
    
}
