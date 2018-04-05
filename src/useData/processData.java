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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.*;
import org.json.simple.JSONObject;

/**
 *
 * @author will
 */

public class processData {

    public static JPanel plotPower()
    {
        Iterator Jarray = parseJSON.readJSONArray("v_log.json");
        XYSeries series = new XYSeries("24h Power");
        int i=0;
        
        while(Jarray.hasNext()){
            JSONObject obj = (JSONObject) Jarray.next();
            series.add(i, (double) obj.get("Voltage"));
            i++;
        }
        
        // Add the series to a data set, add the dataset to a chart,
        // and then add the chart to a ChartPanel
        XYDataset xyData = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart ("24H Voltage Readings", "Time", "Volts",
                xyData, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chPanel = new ChartPanel(chart);
        //chPanel.setPreferredSize(new Dimension(100,100));
        return chPanel;
    }
    
    public static JPanel plotTemp()
    {
        Iterator Jarray = parseJSON.readJSONArray("t_log.json");
        TimeSeries tSeries = new TimeSeries("System Temperature");
        SimpleDateFormat sdp = new SimpleDateFormat("E MMM dd HH:mm:ss yyyy");
        
        // Iterate through JSON array and extract temp values
        // and timestamp.
        while(Jarray.hasNext()){
            JSONObject obj = (JSONObject) Jarray.next();
            try {
                Date d = sdp.parse((String) obj.get("Timestamp"));
                tSeries.add(new Second(d), (double) obj.get("temperature"));
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
                //Logger.getLogger(processData.class.getName()).log(Level.SEVERE, null, ex);
            }
            // REMEMBER TO DELETE THIS WHEN TEMP JSON IS CREATED WITH 1 SECOND INTERVALS
            Jarray.next();
        }

        // Add the series to a data set, add the dataset to a chart,
        // and then add the chart to a ChartPanel
        TimeSeriesCollection dataset = new TimeSeriesCollection(tSeries);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("24H Temp Readings", "Time", "Degrees (C)",
                dataset, true, true, false);
        ChartPanel chPanel = new ChartPanel(chart);
        //chPanel.setPreferredSize(new Dimension(100,100));
        return chPanel;
    }
    
    public static JPanel plotPriceData() 
    {
        List<dataPoint> priceData = readPriceData("TodaysData.csv");
        TimeSeries tSeries = new TimeSeries("Price ($/MWHr)");
        
        for(int i=0; i<priceData.size(); i++){
            tSeries.add(new Second(priceData.get(i).getTimeStamp()), priceData.get(i).getPrice());
        }
        
        // Add the series to a data set, add the dataset to a chart,
        // and then add the chart to a ChartPanel
        TimeSeriesCollection dataset = new TimeSeriesCollection(tSeries);
        JFreeChart chart = ChartFactory.createTimeSeriesChart ("NYISO LBMP", "Time of Day", "$/MWHr",
                dataset, true, true, false);
        
        // Set format of date axis (x-axis)
        XYPlot plot = chart.getXYPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH:MM"));
        
        ChartPanel chPanel =new ChartPanel(chart);
        //chPanel.setPreferredSize(new Dimension(100,100));
        return chPanel;
    }
    
    public static JPanel showChargeLevel()
    {
        ArrayList<JSONObject> Jarray = parseJSON.getJSONArray("v_log.json");
        double voltage = (double) Jarray.get(Jarray.size()-1).get("Voltage");
        voltage = voltage-11.5;
        
        DefaultPieDataset dataset=new DefaultPieDataset();
        dataset.setValue("Remaining", voltage/1.6);
        dataset.setValue("Used", (1.6-voltage)/1.6);


        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
            "Battery Charge Status",
            dataset,
            true, 
            true,
            false);

        //Format Label
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
            "{0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        return panel;
 
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
    
    public static void populateTable(String filename, JTable table)
    {
        File file =  new File(filename);
        DefaultTableModel model =  (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        if(!file.exists()){
            model.addColumn("File \'" + filename + "\' Not Found");
            return;
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            // String used to read lines. Read first line here
            // Note: header line forms the column identifiers
            String line = br.readLine();
            String[] attributes = line.split((","));
            model.setColumnIdentifiers(attributes);

            Object[] tableLines = br.lines().toArray();
            for (Object tableLine : tableLines) {
                line = tableLine.toString().trim();
                attributes = line.split((","));
                if(attributes[1].equals("\"N.Y.C.\""))
                    model.addRow(attributes);
            }  
        } catch (IOException e){
            e.printStackTrace();
        }  
    }
    
    public static void populateTableWithJSON(String filename, JTable table)
    {
        File file =  new File("JSON_Objects/" + filename);
        DefaultTableModel model =  (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        if(!file.exists()){
            model.addColumn("File \'JSON_Objects/" + filename + "\' Not Found");
            return;
        }
        
        Iterator Jarray = parseJSON.readJSONArray(filename);
        if(filename.equals("v_log.json"))
        {
            String[] tableLine = {"Timestamp", "Voltage"};
            model.setColumnIdentifiers(tableLine);
            
            while(Jarray.hasNext()){
                JSONObject obj = (JSONObject) Jarray.next();
                Object[] line = {obj.get("Timestamp"), obj.get("Voltage")};
                model.addRow(line);
            }
        }
        else if(filename.equals("t_log.json"))
        {
            String[] tableLine = {"Timestamp", "temperature"};
            model.setColumnIdentifiers(tableLine);
            
            while(Jarray.hasNext()){
                JSONObject obj = (JSONObject) Jarray.next();
                Object[] line = {obj.get("Timestamp"), obj.get("temperature")};
                model.addRow(line);
            }
        }
     
    }
    
    public static double getProfitMargin() 
    {
        File file = new File("profit.txt");
        double profit = 0;
        
        if(!file.exists())
            return 0;
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            // String used to read lines. profit.txt should only have 1 line
            String line = br.readLine();
            profit = Double.parseDouble(line);
        } catch (IOException e){
           System.out.println(e.getMessage()); 
        }
        return profit;
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