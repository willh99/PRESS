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

import java.io.*;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
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

public class ProcessData {
    
    public static JPanel plotPower() throws FileNotFoundException
    {
        Iterator Jarray = ParseJSON.readJSONArray("v_log.json");
        TimeSeries tSeries = new TimeSeries("System Voltage");
        SimpleDateFormat sdp = new SimpleDateFormat("E MMM dd HH:mm:ss yyyy");
        JSONObject obj = null;
        String day = "";
        
        if(Jarray == null)
            return new JPanel();
        
        while(Jarray.hasNext()){
            obj = (JSONObject) Jarray.next();
            Date d;
            try {
                d = sdp.parse((String) obj.get("Timestamp"));
                tSeries.add(new Second(d), (double) obj.get("Voltage"));
            } catch (ParseException | NullPointerException ex) {
                Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static JPanel plotTemp(String filename) throws FileNotFoundException
    {
        Iterator Jarray = ParseJSON.readJSONArray(filename);
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
                    Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static JPanel plotPriceData() throws FileNotFoundException 
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
        ArrayList<JSONObject> Jarray = ParseJSON.getJSONArray("v_log.json");
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
            Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (in != null)
                in.close();
            if (fout != null)
                fout.close();
        }
    }
    
    // Read data from a .csv file and return a List holding the relevent data
    private static List<DataPoint> readPriceData(String filename) throws FileNotFoundException 
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
                    System.out.println("Array Index: " + e.getMessage() + " out of bounds in attributes");
                }
                
            }
        } catch (IOException | ParseException ex){
            Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dataList;
    }

    // Algorithm for processing pricing data and deciding whether to buy/sell
    // Identical methodoloy appied on server-side
    public static void analyizePriceData () throws FileNotFoundException 
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
        
        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date timestamp =null;
        double currentPrice = 0;
        
        // Create a status JSON depending on the current state of 
        // the system and the change in the data.
        // e.g. if currently buying and price goes above 70% of maximum,
        //      then generate a 'sell' (buy=false) JSON
        for(int i=0; i<dataList.size(); i++){
            
            // Get Integer values for the current time of day and the
            // time of day of a datapoint timestamp
            timestamp = dataList.get(i).getTimeStamp();
            LocalDateTime priceDate = LocalDateTime.parse(timestamp.toString(), formatter);
            LocalDateTime myDate = LocalDateTime.now();
            
            if(myDate.getHour() >= priceDate.getHour()){
                if(myDate.getMinute() >= priceDate.getMinute()){
                    
                    currentPrice = dataList.get(i).getPrice();
                    
                    if(currentPrice > max && !sell){
                        buy = false;
                        sell = true;
                        ParseJSON.createStatusJSON(buy, sell, "Algorithmic");
                    }
                    else if(currentPrice < min && !buy){
                        buy = true;
                        sell = false;
                        ParseJSON.createStatusJSON(buy, sell, "Algorithmic");
                    }
                    try {
                        sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {break;}
        }
        
        if(!buy && !sell)
            ParseJSON.createStatusJSON(buy, sell, "Algorithmic");
        
        System.out.println("\nBuy and Sell are: " + buy + " " + sell);
        System.out.println("Current Price for "+ timestamp + " is: $" + currentPrice + "\n");
    }
    
    // Populates a table with the contents of a .csv file
    // Assumes that the first row of the .csv are column headers
    public static void populateTable(String filename, JTable table) throws FileNotFoundException
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
        } catch (IOException ex) {
            Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
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
        
        Iterator Jarray = ParseJSON.readJSONArray(filename);
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
        else if(filename.equals("t_log.json") || filename.equals("bt_log.json"))
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
    public static double[] getProfitMargin() throws FileNotFoundException 
    {
        double[] profit = {0,0,0};
        
        JSONObject obj = ParseJSON.getJSONObject("profit.json");
        if(obj == null){
            System.out.println("Null JSONObject");
            return profit;
        }
        
        try{
            profit[0] = (double) obj.get("profit");
            profit[1] = (double) obj.get("Sell Time");
            profit[2] = (double) obj.get("Buy Time");
        } catch (Exception ex){
            Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println();
        }
        
        return profit;
    }
    
    public static void LoggerSetup() throws IOException{
        Logger LOGGER = Logger.getLogger(ProcessData.class.getName());
        
        File path = new File("logs/");
        File file = new File(path, "DataLogs.txt");
        
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
            Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
