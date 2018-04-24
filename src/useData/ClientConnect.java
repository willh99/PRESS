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

/**
 *
 * @author will
 */
import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ClientConnect
{
    private InetSocketAddress addr = null;
    private int timeout;
    private Socket socket = null;
    private FileOutputStream fos = null;
    private DataInputStream din = null;
    private DataOutputStream dos = null;
    private PrintStream pout = null;
    private Scanner scan = null;
    private boolean DEBUG;

    public ClientConnect(InetAddress address, int p, int t) throws IOException 
    {
        DEBUG = Globals.getNetworkDebug();
        //LoggerSetup();
        addr = new InetSocketAddress(address, p);
        timeout = t;
        try {
            System.out.println("Initializing Client");
            socket = new Socket();
            socket.connect(addr, timeout);
            scan = new Scanner(System.in);
            din = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            pout = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, "ClientConnect", ex);
            
            if(DEBUG)
                System.out.println(ex.getMessage() + "\n");
        }
    }
    
    // Set up the network logger
    public static void LoggerSetup() throws IOException{
        Logger LOGGER = Logger.getLogger(ClientConnect.class.getName());
        
        File path = new File("logs/");
        File file = new File(path, "NetworkLogs.txt");
        
        path.mkdirs();
        if(!file.exists())
            file.createNewFile();
        
        FileHandler fileHandler;
        SimpleFormatter formatter;
                
        try {
            fileHandler = new FileHandler(file.getPath(), true);
            LOGGER.addHandler(fileHandler);
            formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Send a string. Used in chat and to initialized
    // file Request/Transfer
    private void send(String msg) throws IOException
    {
        pout.print(msg);
        pout.flush();
    }

    // Recieve a string. Used in chat mode
    private String recv() throws IOException
    {
        byte[] bytes = new byte[1024];
        din.read(bytes);
        String reply = new String(bytes, "UTF-8");
        //System.out.println("Inside recv(): ");
        return reply;
    }

    public void closeConnection() throws IOException
    {
        if(socket != null){
            // Clean up when a connection is ended
            if(!socket.isClosed()){
                socket.close();
                din.close();
                dos.close();
                pout.close();
            }
            else
                System.out.println("Socket cannot be closed: Already Closed");
        }
    }
  
    // Connect a closed socket to a server
    public void connectSocket() throws IOException
    {
        if(socket == null)
        {
            try {
                System.out.println("Initializing Client");
                socket = new Socket();
                socket.connect(addr, timeout);
                scan = new Scanner(System.in);
                din = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                pout = new PrintStream(socket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, "ClientConnect", ex);
                //System.out.println(ex.getMessage());
            }
        }
        else if((socket.isClosed() || !socket.isConnected()) && (addr!=null) && (addr.getPort()>0) ){
            if(DEBUG)
                System.out.println("Trying to Connect Existing Socket");
            
            socket = new Socket();
            socket.connect(addr, timeout);
            din = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            pout = new PrintStream(socket.getOutputStream());
        }
        else if(!socket.isConnected())
            System.out.println("Socket is already Connected: " + socket.isConnected());
        else if(!socket.isClosed())
            System.out.println("Socket is already Connected: " + socket.isConnected());
        else if(addr==null)
            System.out.println("No address set");
        else if(addr.getPort()<=0)
            System.out.println("No port set");
    }
    
    public void chat() throws IOException
    {    
        try {
            connectSocket();
            if(socket == null)
                return;
            else if(socket.isClosed() || !socket.isConnected())
                return;
        } catch (IOException ex) {
            if(DEBUG)
                System.out.println("Failed to connect to server: " + ex.getMessage());
            return;
        }
        String response = "s";
        
        System.out.println("Initiating Chat Sequence");
        while(!response.equals("QUIT")){
            System.out.print("Client: ");
            String message = scan.nextLine();
            send(message);
            if(message.equals("QUIT"))
                break;
            response = recv();
            System.out.println("Server: " + response);

        }
        closeConnection();
    }
    
    // Request a specific file from the server
    public void getFile(String filename)
    {
        try {
            connectSocket();
            if(socket == null)
                return;
            else if(socket.isClosed() || !socket.isConnected())
                return;
        } catch (IOException ex) {
            if(DEBUG)
                System.out.println("Failed to connect to server: " + ex.getMessage());
            return;
        }
        
        System.out.println("Requested File: "+filename);
        File path, file;
        try {
            // 'Path' determines the parent directories of the file
            // 'filename' is the acutal name of the file
            if(filename.toLowerCase().contains(".json")){
                path = new File("JSON_Objects/");
                file = new File(path, filename);
            }
            else{
                path = new File("Downloads/");
                file = new File(path, filename);
            }
            // Create new file if it does not exist
            // Then request the file from server
            path.mkdirs();
            if(!file.exists()){
                file.createNewFile();
                System.out.println("Created New File: " + file);
            }
            fos = new FileOutputStream(file);
            send("REQUEST " + filename);
        
            // Get content in bytes and write to a file
            byte[] buffer = new byte[8192];
            for(int counter=0; (counter = din.read(buffer, 0, buffer.length)) >= 0;)
            {
                    fos.write(buffer, 0, counter);
            }
            fos.flush();
            fos.close();
            closeConnection();
            
        } catch (IOException e) {
            Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, "ClientConnect", e);
        }
        
    }
    
    // Send a file from the client to the server
    public void sendFile(String filename)
    {
        try {
            connectSocket();
            if(socket == null)
                return;
            else if(socket.isClosed() || !socket.isConnected())
                return;
        } catch (IOException ex) {
            if(DEBUG)
                System.out.println("Failed to connect to server: " + ex.getMessage());
            return;
        }
        
        System.out.println("Attempting to send file: "+filename);
        File file;
        try{
            if(filename.toLowerCase().contains(".json")){
                file = new File("JSON_Objects/" + filename);
            }
            else{
                file = new File("Downloads/" + filename);
            }
            if(!file.exists()){
                System.out.println("File does not exist. Aborting");
                return;
            }
            send("TRANSFER " + filename);
            
            // Sleep to give network time to recieve TRANSFER command
            // and set up to recieve incoming file
            try {
                sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            try (FileInputStream fis = new FileInputStream(file)) {
                int count;
                byte[] buffer = new byte[1024];
                
                while( (count = fis.read(buffer)) > 0){
                    dos.write(buffer, 0 ,  count);
                }
                dos.flush();
            }
            closeConnection();
            
        } catch(IOException e){
            Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, "ClientConnect", e);
        }
    }
    
    
    public void checkStatus()
    {
        try {
            connectSocket();
            if(socket == null)
                return;
            else if(socket.isClosed() || !socket.isConnected())
                return;
        } catch (IOException ex) {
            if(DEBUG)
                System.out.println("Failed to connect to server: " + ex.getMessage());
            return;
        }
        
        System.out.println("Requesting Status Update");
        File path, file;
        try {
            // 'path' determines the parent directories of the file
            // 'file' is the acutal name of the file
            path = new File("JSON_Objects/");
            file = new File(path, "home_status.json");
            
            // Create new file if it does not exist
            // Then request the file from server
            path.mkdirs();
            if(!file.exists()){
                file.createNewFile();
                System.out.println("Created New File: " + file);
            }
            fos = new FileOutputStream(file);
            send("REQUEST status.json");
        
            // Get content in bytes and write to a file
            byte[] buffer = new byte[8192];
            for(int counter=0; (counter = din.read(buffer, 0, buffer.length)) >= 0;)
            {
                    fos.write(buffer, 0, counter);
            }
            fos.flush();
            fos.close();
            closeConnection();
            
        } catch (IOException e) {
            Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, "ClientConnect", e);
        }
    }
    
    // Check if a connection is currently active
    public boolean clientIsConnected()
    {
        return !socket.isClosed();
    }
}