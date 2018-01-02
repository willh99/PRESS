/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useData;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;

/**
 *
 * @author willy
 */

public class processData {

    public static void plotPower(){
        int[][] XY = new int[10][2];
        for(int i=0; i<XY.length; i++){
            for(int j=0; j<2; j++){
                XY[i][j] = i;
            }
        }
        XYSeries series = new XYSeries("24h Power");

        for(int j=0; j<XY.length; j++){
                series.add(XY[j][0], XY[j][1]);
        }

        XYDataset xyData = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart ("XYLine Chart using JFreeChart", "X", "Y",
                xyData, PlotOrientation.VERTICAL, true, true, false);
        ChartFrame frame1=new ChartFrame("XYLine Chart",chart);
        frame1.setSize(300,300);
        frame1.setVisible(true);
    }
    
    public static void plotPriceData() {
        
        List<dataPoint> priceData = readPriceData("TodaysData.csv");
        XYSeries series = new XYSeries("24h Power Pricing");
        
        for(int i=0; i<priceData.size(); i++){
            series.add(i, priceData.get(i).getPrice());
        }
        
        XYDataset xyData = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart ("NYISO LBMP", "Time", "$/MWH",
                xyData, PlotOrientation.VERTICAL, true, true, false);
        ChartFrame frame1=new ChartFrame("Price Data",chart);
        frame1.setSize(500,500);
        frame1.setVisible(true);
    }
    
    // downloads new file from the Internet to be used for data decision making
    public static void downloadData() throws IOException {
        
        // TO DO: make URL dynamic to pull new data every day
        String dataSource = "http://mis.nyiso.com/public/csv/realtime/20171130realtime_zone.csv";
        
        // Downloads a new .csv file from NYISO to be used for data processing
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(dataSource).openStream());
            fout = new FileOutputStream("TodaysData.csv");

            byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
            fout.write(data, 0, count);
        }
        } catch (MalformedURLException ex) {
            Logger.getLogger(processData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(processData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (in != null)
                in.close();
            if (fout != null)
                fout.close();
        }
    }
    
    // Read data from a .csv file and return a List holding the relevent data
    private static List<dataPoint> readPriceData(String filename) {
        
        List<dataPoint> dataList = new ArrayList<>();
        Path pathToFile = Paths.get(filename);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
        try(BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)){
            // String used to read lines. Read first line here
            String line = br.readLine();
            line = br.readLine();
            
            
            while(line != null){
                // Array of values held in .csv file
                String[] attributes = line.split((","));
                for(int i=0; i<attributes.length;i++){
                    attributes[i] = attributes[i].substring(1, attributes[i].length()-1);
                }
                Date d = dateFormat.parse(attributes[0]);
                String name = attributes[1];
                double data = Double.parseDouble(attributes[3]);
                
                // Only add data from the chosen distribution zone
                if(name.equals("N.Y.C.")){
                    dataPoint dP = new dataPoint(d, name, data);
                    dataList.add(dP);
                }
                line = br.readLine();
            }  
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(processData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dataList;
    }

    public static void analyizePriceData () {
        
        List<dataPoint> dataList = readPriceData("TodaysData.csv");
        double max=0, min=1000;
        boolean buy =  false;
        //LocalDateTime localTime;
        
        // Find maximum and minimal prices
        for(int i=0; i<dataList.size(); i++){
            if(dataList.get(i).getPrice() > max)
                max = dataList.get(i).getPrice();
            if(dataList.get(i).getPrice() < min)
                min = dataList.get(i).getPrice();
        }
        // Buy/Sell Thresholds
        max = 0.7*max;
        min = 0.3*max;
        
        for(int i=0; i<dataList.size(); i++){
            if(dataList.get(i).getPrice() > max && buy){
                buy = false;
                parseJSON.createStatusJSON(buy);
            }
            else if(dataList.get(i).getPrice() < min && !buy){
                buy = true;
                parseJSON.createStatusJSON(buy);
            }
            try {
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(processData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


class dataPoint {
    
    // Class to hold relevent information about a data point
    private Date timeStamp;
    private String name;
    private double price;
    
    public dataPoint(Date tS, String n, double p){
        timeStamp=tS; name=n; price=p;
    }
    
    public Date getTimeStamp() {
        return timeStamp;
    }
    
    public String getName(){
        return name;
    }
    
    public double getPrice() {
        return price;
    }

    public String toString(){
        return "Data Point[ timeStamp: "+timeStamp+" name: "+name+" price: "+price+"$/MWH ]";
    }
}