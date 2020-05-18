Project Assignment #01
TCSS 360 Spring 2020
Group Members: Deline Zent, Spencer Little, Paras Sharma, Darby Wise

Our program serves as a model of the core software for the Wireless Vantage Pro2 Integrated Sensor Suite (ISS). This program
Completes three main tasks, as outlined in the assignment specifications:

-collects data measurements from the ISS sensors, including sensors for wind speed/direction, temperature, etc.
-processes sensor data to be able to send it to certain devices for monitoring
-serializes this data in order to pass it between the weather station and different devices on an IP network

We accomplish these tasks using a variety of different classes within our src folder, including:

DataRelay.java - a class that manages all data input/output.
Main.java - main driver class that uses test data from an input file to simulate data collection.

And within the src folder is our WeatherData folder with all classes pertaining to sensors/sensor data:

DataType.java - a public enum that we use to establish all of our sensor data types.
HistoricalDataPoint.java - abstract class for all data processing aspects. Has methods that takes in data and create data processing objects for each sensor.
WDataPoint.java - interface that establishes base functionality for all sensor classes.
Sensor.java - a public enum we use to establish three types of sensors: inside, outside, and extra.

Our specific sensor classes include: Humidity, RainFall, RainRate, SoilMoisture, SoilRadiation, Temperature, UltraViolet, WindDirection, and WindSpeed. These classes all follow the same basic functionality according to the interface and extend HistoricalDataPoint, with a few
different methods within some that require additional ones.

For testing purposes, we also include four classes within our test folder, including:

DataTransferTest.java - tests the DataRelay class, including serialization, accepting data points, etc.
HistoricalDataPointTest.java - tests the reading data methods of the HistoricalDataPoint class.
SolarRadiationTest.java - tests the warning function of the SolarRadiation class.
UltraVioletTest.java - tests the warning function of the UltraViolet class.

In order to create some sort of modeled data that we would be able to use as inputs/outputs for testing purposes, we use our DataRelay class to manage sample inputs/outputs, as well as our main driver class to use this sample data in a simulation of properly collecting and sending the data. 

We first use a text file as input with randomly generated data that serves as data collected from the sensors, incrementing the Gregorian calendar as we do so to simulate time passing. With this data from the sensors, we use the DataRelay class to add all data to its respective DataPoint objects, and then write this data to a text file to serve as a properly formatted model of our output. An example of this text file can be found in our project, titled WDATA_TEST.txt.
