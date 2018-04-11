![logo](https://github.com/willh99/PRESS/blob/master/src/images/LOGO.000.jpg)

# PRESS
### Price Reactive Energy Storage System User Interface

This is the user application for the Price Reactive Energy Storage System (PRESS) created using the Java Swing toolkit in the Netbeans IDE.


This application is currently capable of connecting to an embedded device (currently a RaspberryPi) which 
controls an Inverter and charger capabile of buying and selling energy to the grid intellegently based on price. See my other Repo - PRESS RPi - to get a better understanding of the other side of this project.

Additionally, the user application will be able to periodically pull princing data from the Internet when connected, show the user data relevant to the operation of the PRESS system, and manually override the operating status of the embedded device.

### Features
* Custom Client designed specifically for connection to the PRESS RPi server.  The PRESS app fetches voltage, temperature, profit, and status data periodically (currently every 30 seconds) from the server if a connection is possible
* Data Visualisation - Parses data JSONs created by the RPi controller and plots them in Jfreechart graphs and displays them in JTables. Data is fetched from the server every 30 seconds
* Manual Override of RPi - The user of the PRESS app can send a manual override JSON to override the current status of the controller (unless temperature/voltage readings dictate the it is unsafe to do so). A client connection is be formed on a seporate thread when this occurs and a new appstatus.json is sent over the network
* Automatic Data Fetch -  Electric pricing data (currently from the New York Independent Service Operator - NYISO) is fetch over Internet connection and used for data visualisation and to determine whether the system should buy/sell power at any given time of day
* Other fun stuff - Features are currently being added to increase the user experience, such as custom look & feel settings
