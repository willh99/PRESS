package useData;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */


public class parseJSON {

    public static void createStatusJSON(boolean Buy, boolean Sell, String type)
    {
        // Create JSON object
        JSONObject obj = new JSONObject();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        Date d = new Date();
        String filename;
        
        if(type.equals("Algorithmic"))
            filename = "algorithmstatus.json";
        else
            filename = "appstatus.json";

        // Put timestamp into status JSON
        obj.put("Timestamp", dateFormat.format(d));
        obj.put("Buy", Buy);
        obj.put("Sell", Sell);

        try (FileWriter file = new FileWriter("JSON_Objects/" + filename)) {
                file.write(obj.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.println(obj);
    }
  
    public static void createPowerJSON() 
    {  
        JSONObject obj = new JSONObject();
        Path pathToFile = Paths.get("v_log.txt");
    
        // Overwrite previous contents of the file
        try (FileWriter file = new FileWriter("JSON_Objects/test.json")) {
            file.write("");
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read text file line-by-line, split data and put into JSON
        try(BufferedReader br = Files.newBufferedReader(pathToFile,
            StandardCharsets.US_ASCII)){
            // String used to read lines. Read first line here
            String line = br.readLine();

            while(line != null){
                // Array of values held in .csv file
                String[] attributes = line.split(("       "));
                obj.put("Voltage", attributes[1]);
                obj.put("TimeStamp", attributes[0]);    
                line = br.readLine();
                //System.out.println(Arrays.toString(attributes));

                // Write JSON to a file
                try (FileWriter file = new FileWriter("JSON_Objects/test.json", true)) {
                    file.write(obj.toJSONString()+"\n");
                    file.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }  

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    // Parses a JSON Array from a JSON file. Uses include retreiving 
    // multiple JSONs stored within a JSON array for voltage and temperature
    public static Iterator readJSONArray (String filename) 
    {
        Iterator i = null;
        File file;
        file = new File("JSON_Objects/" + filename);
        if(!file.exists()){
            System.out.println("File \"" +filename+ "\" does not exist. Aborting");
            return i;
        }
        
        JSONParser parser = new JSONParser();
        Object fileOutput = null;
        try {
            fileOutput = parser.parse(new FileReader(file));
        } catch (IOException | ParseException ex) {
            Logger.getLogger(parseJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        JSONArray Jarray = (JSONArray) fileOutput;
        i = Jarray.iterator();
        
        return i;
    }
    
    // Parses a JSON Array from a JSON file and returns the array
    public static ArrayList getJSONArray (String filename) 
    {
        
        File file;
        JSONArray Jarray;
        file = new File("JSON_Objects/" + filename);
        if(!file.exists()){
            System.out.println("File \"" +filename+ "\" does not exist. Aborting");
            Jarray = null;
            return Jarray;
        }
        
        JSONParser parser = new JSONParser();
        Object fileOutput = null;
        try {
            fileOutput = parser.parse(new FileReader(file));
        } catch (IOException | ParseException ex) {
            Logger.getLogger(parseJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        Jarray = (JSONArray) fileOutput;
        return Jarray;
    }

    public static JSONObject getJSONObject(String filename)
    {
        if(!filename.contains(".json")){
            System.out.println("Cannot load non-json file to JSONObject");
            return null;
        }
        
        File file = new File("JSON_Objects/" + filename);
        JSONObject obj =  null;
        
        if(!file.exists()){
            System.out.println("File \"" +filename+ "\" does not exist. Aborting");
            return obj;
        }
        
        JSONParser parser = new JSONParser();
        try{
            obj = (JSONObject) parser.parse(new FileReader(file));
        } catch (IOException | ParseException ex){
            System.out.println(ex.getMessage());
            
        }
        
        System.out.println("HELLO!");
        return obj;
    }
}
