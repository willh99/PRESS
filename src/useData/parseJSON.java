package useData;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  public static void createStatusJSON(boolean Buy){
    
    // Create JSON object
    JSONObject obj = new JSONObject();
    
    if(Buy){
        obj.put("Buy", true);
    }
    else{
        obj.put("Buy", false);
    }
    
    try (FileWriter file = new FileWriter("JSON_Objects/test.json")) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    System.out.println(obj);
  }
  
  public static void createPowerJSON() {
    
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
  
  public static void readJSON() {
      
    JSONParser parser = new JSONParser();
    JSONArray Jarray = new JSONArray();
    Path pathToFile = Paths.get("JSON_Objects/test.json");
    
    try(BufferedReader br = Files.newBufferedReader(pathToFile,
        StandardCharsets.US_ASCII)){
        
        // String used to read lines. Read first line here
        String line = br.readLine();
        Object obj;
        
        while(line != null){
            obj = parser.parse(line);
            Jarray.add((JSONObject) obj);
            line = br.readLine();
        }
        //System.out.println(Jarray);
    } catch (FileNotFoundException e){
        e.printStackTrace();
    } catch (IOException | ParseException e){
        e.printStackTrace();
    }
  }
}
