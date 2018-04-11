package useData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */
import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public ClientConnect(InetAddress address, int p, int t) 
    {
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
            //Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage() + "\n");
            //System.exit(1);
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
                //Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
                //System.exit(1);
            }
        }
        else if((socket.isClosed() || !socket.isConnected()) && (addr!=null) && (addr.getPort()>0) ){
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
            System.out.println("Failed to connect to server");
            System.out.println(ex.getMessage());
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
            System.out.println("Failed to connect to server");
            System.out.println(ex.getMessage());
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
            e.printStackTrace();
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
            System.out.println("Failed to connect to server");
            System.out.println(ex.getMessage());
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
            e.printStackTrace();
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
            System.out.println("Failed to connect to server");
            System.out.println(ex.getMessage());
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
            e.printStackTrace();
        }
    }
    
    // Check if a connection is currently active
    public boolean clientIsConnected()
    {
        return !socket.isClosed();
    }
}