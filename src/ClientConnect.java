/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientConnect {

    private ObjectOutputStream outputObj;
    private ObjectInputStream inputObj;
    //private BufferedReader input;
    //private PrintWriter output;
    //private BufferedReader stdIn;
    
    private String message = "";
    private final String serverIP;
    private final int portNumber;
    private Socket socketConnect;
    private Scanner scan;
    
    public ClientConnect(String host, int port){
        serverIP = host;    portNumber = port;
    }
    
    public void startClient() throws IOException{
        scan = new Scanner(System.in);
        try{
            serverConnect();
            setUpStreams();
            whileChatting();
        }catch(EOFException eofEx){
            System.out.println("Client terminated connection");
        }finally{
            closeConnection();
        }
    }
    
    //Connect to a server
    private void serverConnect(){
        System.out.println("Attempting Connection");
        try{
            socketConnect = new Socket(InetAddress.getByName(serverIP), portNumber);
            System.out.println("Connected to: "+serverIP+" on port: "+portNumber);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    private void setUpStreams(){
        try{
            outputObj = new ObjectOutputStream(socketConnect.getOutputStream());
            inputObj = new ObjectInputStream(socketConnect.getInputStream());
            outputObj.flush();
            System.out.println("Streams are good");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    private void whileChatting() throws IOException{
        System.out.println("You can now chat!");
        String inMessage="";
        
        do{
            try{
                message = scan.nextLine();
                sendMessage(message);
                inMessage = (String) inputObj.readObject();
                System.out.println(inMessage);
            }catch(ClassNotFoundException e){
                System.err.println("Unknown object reieved");
            }
        }while(!inMessage.equals("CLIENT: QUIT"));

    }
    
    private void closeConnection() throws IOException{
        System.out.println("Closing down connection ...");
        outputObj.close();
        inputObj.close();
        socketConnect.close();
    }
    
    private void sendMessage(String s) {
        try{
        outputObj.writeObject("CLIENT: "+s);
        outputObj.flush();
        System.out.println("CLIENT: "+s);
        }catch(IOException e){
            System.err.println("Error writting message");
        }
    }
    
}
