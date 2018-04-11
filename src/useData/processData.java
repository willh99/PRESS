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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
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
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.json.simple.JSONObject;

/**
 *
 * @author will
 */

public class processData {

    public static JPanel plotPower()
    {
        Iterator Jarray = parseJSON.readJSONArray("v_log.json");
        TimeSeries tSeries = new TimeSeries("System Voltage");
        SimpleDateFormat sdp = new SimpleDateFormat("E MMM dd HH:mm:ss yyyy");
        JSONObject obj = null;
        String day = "";
        
        while(Jarray.hasNext()){
            obj = (JSONObject) Jarray.next();
            Date d;
            try {
                d = sdp.parse((String) obj.get("Timestamp"));
                tSeries.add(new Second(d), (double) obj.get("Voltage"));
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
            // ============================================================================
            // REMEMBER TO DELETE THIS WHEN Voltage JSON IS CREATED WITH 1 SECOND INTERVALS
            // ============================================================================
            if(Jarray.hasNext())
                Jarray.next();
        }
        
        // Set day string to show day which data was collected
        if(obj != null){
            try {
                Date d = sdp.parse((String) obj.get("Timestamp"));
                LocalDate ldate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                day = "for " + ldate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US) +
                      " " + ldate.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " +
                      ldate.getDayOfMonth() + ", " + ldate.getYear();
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        // Add the series to a data set, add the dataset to a chart,
        // and then add the chart to a ChartPanel
        TimeSeriesCollection dataset = new TimeSeriesCollection(tSeries);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Voltage Readings " + day, "Time", "Degrees (C)",
                dataset, true, true, false);
        ChartPanel chPanel = new ChartPanel(chart);
        //chPanel.setPreferredSize(new Dimension(100,100));
        return chPanel;
    }
    
    public static JPanel plotTemp(String filename)
    {
        Iterator Jarray = parseJSON.readJSONArray(filename);
        TimeSeries tSeries = new TimeSeries("System Temperature");
        SimpleDateFormat sdp = new SimpleDateFormat("E MMM dd HH:mm:ss yyyy");
        JSONObject obj = null;
        String day = "";
        
        // Iterate through JSON array and extract temp values
        // and timestamp.
        if(Jarray != null){
            while(Jarray.hasNext()){
                obj = (JSONObject) Jarray.next();
                try {
                    Date d = sdp.parse((String) obj.get("Timestamp"));
                    tSeries.add(new Second(d), (double) obj.get("temperature"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                    //Logger.getLogger(processData.class.getName()).log(Level.SEVERE, null, ex);
                }
                // =========================================================================
                // REMEMBER TO DELETE THIS WHEN TEMP JSON IS CREATED WITH 1 SECOND INTERVALS
                // =========================================================================
                if(Jarray.hasNext())
                    Jarray.next();
            }
        }
        
        // Set day string to show day which data was collected
        if(obj != null){
            try {
                Date d = sdp.parse((String) obj.get("Timestamp"));
                LocalDate ldate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                day = "for " + ldate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US) +
                      " " + ldate.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " +
                      ldate.getDayOfMonth() + ", " + ldate.getYear();
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }

        // Add the series to a data set, add the dataset to a chart,
        // and then add the chart to a ChartPanel
        TimeSeriesCollection dataset = new TimeSeriesCollection(tSeries);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Temperature Readings " + day, "Time", "Degrees (C)",
                dataset, true, true, false);
        ChartPanel chPanel = new ChartPanel(chart);
        //chPanel.setPreferredSize(new Dimension(100,100));
        return chPanel;
    }
    
    public static JPanel plotPriceData() 
    {
        List<DataPoint> priceData = readPriceData("TodaysData.csv");
        TimeSeries tSeries = new TimeSeries("Price ($/MWHr)");
        String day = "";
        
        for(int i=0; i<priceData.size(); i++){
            tSeries.add(new Second(priceData.get(i).getTimeStamp()), priceData.get(i).getPrice());
        }
        
        if(!priceData.isEmpty()){
            Date d = priceData.get(0).getTimeStamp();
            LocalDate ldate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            day = "for " + ldate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US) +
                    " " + ldate.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " +
                    ldate.getDayOfMonth() + ", " + ldate.getYear();
        }
        
        // Add the series to a data set, add the dataset to a chart,
        // and then add the chart to a ChartPanel
        TimeSeriesCollection dataset = new TimeSeriesCollection(tSeries);
        JFreeChart chart = ChartFactory.createTimeSeriesChart ("NYISO LBMP " + day, "Time of Day", "$/MWHr",
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
    private static List<DataPoint> readPriceData(String filename) 
    {
        List<DataPoint> dataList = new ArrayList<>();
        File file = new File(filename);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
        if(!file.exists())
            return dataList;
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            // String used to read lines. Read first line here
            // Note: header line is skipped as it does not hold data
            br.readLine();
            String line;
            
            while( (line=br.readLine()) != null){
                // Array of values held in .csv file
                String[] attributes = line.split((","));
                
                
                try{
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
                        DataPoint dP = new DataPoint(d, name, data);
                        dataList.add(dP);
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Array Index: " + e.getMessage() + "out of bounds in attributes");
                }
                    
            }  
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(processData.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return dataList;
    }

    // Algorithm for processing pricing data and deciding whether to buy/sell
    // Identical methodoloy appied on server-side
    public static void analyizePriceData () 
    {
        // Retrieve List of dataPoint's (see below) holding relevant data
        List<DataPoint> dataList = readPriceData("TodaysData.csv");
        double max=0, min=1000;
        boolean buy=false, sell=false;
        
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
        
        if(!buy && !sell)
            parseJSON.createStatusJSON(buy, sell, "Algorithmic");
    }
    
    // Populates a table with the contents of a .csv file
    // Assumes that the first row of the .csv are column headers
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
    
    // Populates a JTable with the contents of a specified json
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
    
    // Get the profit margin, buy time, and sell time from profit.json
    // and returns them in an array
    public static double[] getProfitMargin() 
    {
        double[] profit = {0,0,0};
        
        JSONObject obj = parseJSON.getJSONObject("profit.json");
        if(obj == null){
            System.out.println("Null JSONObject");
            return profit;
        }
        
        try{
            profit[0] = (double) obj.get("profit");
            profit[1] = (long) obj.get("Sell Time");
            profit[2] = (double) obj.get("Buy Time");
        } catch (Exception e){
            e.printStackTrace();
            //System.out.println();
        }
        
        return profit;
    }
}