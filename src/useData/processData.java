/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useData;

import java.io.*;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;

/**
 *
 * @author willy
 */

public class processData {

    public static JPanel plotPower()
    {
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

        // Add the series to a data set, add the dataset to a chart,
        // and then add the chart to a ChartPanel
        XYDataset xyData = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart ("XYLine Chart using JFreeChart", "X", "Y",
                xyData, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chPanel = new ChartPanel(chart);
        //chPanel.setPreferredSize(new Dimension(100,100));
        return chPanel;
    }
    
    public static JPanel plotPriceData() 
    {
        List<dataPoint> priceData = readPriceData("TodaysData.csv");
        XYSeries series = new XYSeries("24h Power Pricing");
        
        for(int i=0; i<priceData.size(); i++){
            series.add(i, priceData.get(i).getPrice());
        }
        
        // Add the series to a data set, add the dataset to a chart,
        // and then add the chart to a ChartPanel
        XYDataset xyData = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart ("NYISO LBMP", "Time", "$/MWH",
                xyData, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chPanel =new ChartPanel(chart);
        //chPanel.setPreferredSize(new Dimension(100,100));
        return chPanel;
    }
    
    // Downloads new file from the Internet to be used for data decision making
    public static void downloadData() throws IOException 
    {
        // Dynamically update the date in the URL of the newest data from the NYISO
        Date d = new Date();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd");
        String dataSource = "http://mis.nyiso.com/public/csv/realtime/" + dFormat.format(d) + "realtime_zone.csv";
        
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
    private static List<dataPoint> readPriceData(String filename) 
    {
        List<dataPoint> dataList = new ArrayList<>();
        Path pathToFile = Paths.get(filename);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
        try(BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)){
            // String used to read lines. Read first line here
            // Note: header line is skipped as it does not hold data
            br.readLine();
            String line = br.readLine();
            
            
            while(line != null){
                // Array of values held in .csv file
                String[] attributes = line.split((","));
                
                // Get rid of extra " around time and location
                // Note: this may have to change depending on the data format
                attributes[0] = attributes[0].substring(1, attributes[0].length()-1);    
                attributes[1] = attributes[1].substring(1, attributes[1].length()-1);
                
                Date d = dateFormat.parse(attributes[0]);
                String name = attributes[1];
                double data = Double.parseDouble(attributes[3]);
                
                // Only add data from the chosen distribution zone to a 
                // dataPoint class (see below). Each dataPoint instance is
                // added to a List of dataPoint's 
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

    
    // TODO: add fail-safes for instances where data is either not found
    //       of unable to be process
    public static void analyizePriceData () 
    {
        // Retrieve List of dataPoint's (see below) holding relevant data
        List<dataPoint> dataList = readPriceData("TodaysData.csv");
        double max=0, min=1000;
        boolean buy=false, sell=false;
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
        
        // Create a status JSON depending on the current state of 
        // the system and the change in the data.
        // e.g. if currently buying and price goes above 70% of maximum,
        //      then generate a 'sell' (buy=false) JSON
        for(int i=0; i<dataList.size(); i++){
            if(dataList.get(i).getPrice() > max && buy){
                buy = false;
                sell = true;
                parseJSON.createStatusJSON(buy, sell, "Algorithmic");
            }
            else if(dataList.get(i).getPrice() < min && sell){
                buy = true;
                sell = false;
                parseJSON.createStatusJSON(buy, sell, "Algorithmic");
            }
            try {
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(processData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


// Class to hold relevent information about a data point
// Holds time of price data, name of associated region,
// and the price at given the aforementioned parameters
class dataPoint {
    
    private final Date timeStamp;
    private final String name;
    private final double price;
    
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

    @Override
    public String toString(){
        return "Data Point[ timeStamp: "+timeStamp+" name: "+name+" price: "+price+"$/MWH ]";
    }
}