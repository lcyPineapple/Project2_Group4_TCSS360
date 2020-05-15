package GUI.weatherStations;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import GUI.WeatherStationIntegrater;
import GUI.WirelessConsole;
import GUI.sensorSuite1.computations.DewPoint;
import GUI.sensorSuite1.computations.HeatIndex;
import GUI.sensorSuite1.computations.RainfallRate;
import GUI.sensorSuite1.computations.WindChill;
import GUI.sensorSuite1.sensors.HumiditySensor;
import GUI.sensorSuite1.sensors.RainSensor;
import GUI.sensorSuite1.sensors.TemperatureSensor;
import GUI.sensorSuite1.sensors.WindDirection;
import GUI.sensorSuite1.sensors.WindSensor;
import GUI.sensorSuite1.controller.DataPacket;
import GUI.sensorSuite1.controller.Controller;

/**
 * Weather station adapted from Group 01's 
 * code for Project 1
 * 
 * @author Benjamin Munoz
 */
public class WeatherStation1 extends WeatherStation {
    /**
     * Group 1's Temperature sensor
     */
    private TemperatureSensor temp;
    
    /**
     * Group 1's wind speed sensor
     */
    private WindSensor windSpeed;
    
    /**
     * Group 1's wind direction sensor
     */
    private WindDirection windDirection;
    
    /**
     * Group 1's rain sensor
     */
    private RainSensor rain;
    
    /**
     * Group 1's humidity sensor
     */
    private HumiditySensor humidity;
    
    /**
     * Group 1's wind chill sensor
     */
    private WindChill windChill;
    
    /**
     * Group 1's rainfall rate sensor
     */
    private RainfallRate rainfallRate;
    
    /**
     * Group 1's dew point sensor
     */
    private DewPoint dewPoint;
    
    /**
     * Group 1's heat index sensor
     */
    private HeatIndex heatIndex;
    
    /**
     * Java's default Printstream when using System.out
     */
    private static final PrintStream stdOutput = System.out;
    
    /**
     * Used to prevent Group 01's code from printing out to the console
     * whenever sensor data is gathered.
     */
    private static final PrintStream outputSuppresor = new PrintStream(new OutputStream() {
        public void write(int b) {}; // do nothing
    });
    
    /**
     * Constructs a WeatherStation1 instance. It requires 
     * a weather integrater to connect to.
     * 
     * @param integrater - the weather integrater
     */
    public WeatherStation1(WeatherStationIntegrater integrater) {
        super(integrater);
        
        /*
         * The sensors work by sending their data 
         * to the appropriate files in the 
         * serializedData folder. Please do not 
         * change anything in the folder and/or
         * where the folder is!
         */
        temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
        
        windSpeed = new WindSensor(Controller.WINDSPEED_FILE, Controller.WINDSENSOR_LENGTH);
        
        windDirection = new WindDirection(Controller.WINDDIRECTION_FILE);
        
        rain = new RainSensor(Controller.RAINFALL_FILE);
        
        humidity = new HumiditySensor(Controller.HUMIDITY_FILE);
                
        windChill = new WindChill(Controller.WINDCHILL_FILE, temp.getSet(), windSpeed.getSet());
        
        rainfallRate = new RainfallRate(Controller.RAINRATE_FILE, rain.getSet());
        
        dewPoint = new DewPoint(Controller.DEWPOINT_FILE, temp.getSet(), humidity.getSet());
            
        heatIndex = new HeatIndex(Controller.HEATINDEX_FILE, temp.getSet(), humidity.getSet());
    }

    /**
     * Receives the most recent temperature reading from the temperature 
     * sensor.
     * 
     * @return the most recent temperature reading
     */
    public double getTemp() {
        System.setOut(outputSuppresor);
        temp.run();
        System.setOut(stdOutput);
        
        return getLatestDoubleFrom(Controller.TEMPERATURE_FILE);
    }
    
    /**
     * Receives the most recent wind speed reading from the wind 
     * speed sensor.
     * 
     * @return the most recent wind speed reading.
     */
    public int getWindSpeed() {
        System.setOut(outputSuppresor);
        windSpeed.run();
        System.setOut(stdOutput);
        
        return getLatestIntFrom(Controller.WINDSPEED_FILE);
    }
    
    /**
     * Receives the most recent wind direction reading from the wind 
     * direction sensor.
     * 
     * @return the most recent wind direction reading.
     */
    public int getWindDir() {
        System.setOut(outputSuppresor);
        windDirection.run();
        System.setOut(stdOutput);
        
        return getLatestIntFrom(Controller.WINDDIRECTION_FILE);
    }
    
    /**
     * Receives the most recent rainfall reading from the
     * rainfall sensor.
     * 
     * @return the most recent rainfall reading.
     */
    public double getRain() {
        System.setOut(outputSuppresor);
        rain.run();
        System.setOut(stdOutput);
        
        return getLatestDoubleFrom(Controller.RAINFALL_FILE);
    }
    
    /**
     * Receives the most recent humidity reading from the 
     * humidity sensor.
     * 
     * @return the most recent humidity reading.
     */
    public int getHumidity() {
        System.setOut(outputSuppresor);
        humidity.run();
        System.setOut(stdOutput);
        
        return getLatestIntFrom(Controller.HUMIDITY_FILE);
    }
    
    /**
     * Receives the most recent wind chill reading from the wind 
     * chill sensor.
     * 
     * NOTE: the temperature sensor and wind speed sensor must 
     * have their data collected first before valid
     * wind chill data can be extracted. Otherwise, this method 
     * will simply return Integer.MIN_VALUE.
     * 
     * @return the most recent wind chill reading.
     */
    public int getWindChill() {
        if (temp.getSet().isEmpty() || windSpeed.getSet().isEmpty()) {
            System.out.println("WARNING: Relevant data to compute wind chill not available!");
            return Integer.MIN_VALUE;
        }
        System.setOut(outputSuppresor);
        windChill.run();
        System.setOut(stdOutput);
        return getLatestIntFrom(Controller.WINDCHILL_FILE);
    }
    
    /**
     * Receives the most recent rainfall rate reading from the 
     * rainfall rate sensor.
     * 
     * NOTE: the rainfall sensor must have its data gathered 
     * before valid data from the rainfall rate sensor
     * can be extracted. Otherwise, this method will 
     * simply return Double.NEGATIVE_INFINITY. 
     * 
     * @return the most recent rainfall rate reading.
     */
    public double getRainfallRate() {
        if (rain.getSet().isEmpty() || rain.getSet().size() < 3) {
            System.out.println("WARNING: Relevant data to compute rainfall rate not available!");
            return Double.NEGATIVE_INFINITY;
        }
        System.setOut(outputSuppresor);
        rainfallRate.run();
        System.setOut(stdOutput);
        
        return getLatestIntFrom(Controller.RAINRATE_FILE);
    }
    
    /**
     * Receives the most recent dew point reading from the
     * dew point sensor.
     * 
     * NOTE: the temperature sensor and humidity sensor must 
     * have their data collected first before valid
     * dew point data can be extracted. Otherwise, this method 
     * will simply return Integer.MIN_VALUE.
     * 
     * @return the most recent dew point reading.
     */
    public int getDewPoint() {
        if (temp.getSet().isEmpty() || humidity.getSet().isEmpty()) {
            System.out.println("WARNING: Relevant data to compute dew point not available!");
            return Integer.MIN_VALUE;
        }
        System.setOut(outputSuppresor);
        dewPoint.run();
        System.setOut(stdOutput);
        
        return getLatestIntFrom(Controller.DEWPOINT_FILE); 
    }
    
    /**
     * Receives the most recent heat index reading from the 
     * heat index sensor.
     * 
     * NOTE: the temperature sensor and humidity speed sensor must 
     * have their data collected first before valid
     * heat index data can be extracted. Otherwise, this method 
     * will simply return Integer.MIN_VALUE.
     * 
     * @return the most recent heat index reading.
     */
    public int getHeatIndex() {
        if (temp.getSet().isEmpty() || humidity.getSet().isEmpty()) {
            System.out.println("WARNING: Relevant data to compute heat index not available!");
            return Integer.MIN_VALUE;
        }
        System.setOut(outputSuppresor);
        heatIndex.run();
        System.setOut(stdOutput);
        
        return getLatestIntFrom(Controller.HEATINDEX_FILE);
    }
    
    /**
     * Returns the latest integer from the given file
     * 
     * @param f -- the file
     * @return The latest integer from the given file
     */
    private int getLatestIntFrom(File f) {
        int retr = Integer.MIN_VALUE;
        try {
            var dStrArr = getDataStringArrFrom(f);
            retr = Integer.parseInt(dStrArr[dStrArr.length - 1]);
        } catch (Exception e) {
            System.out.println("WARNING: reading from file " + f + " failed");
        }
        return retr;
    }
    
    /**
     * Returns the latest double from the given file
     * 
     * @param f -- the file
     * @return The latest double from the given file
     */
    private double getLatestDoubleFrom(File f) {
        double retr = Double.NEGATIVE_INFINITY;
        try {
            var dStrArr = getDataStringArrFrom(f);
            retr = Double.parseDouble(dStrArr[dStrArr.length - 1]);
        } catch (Exception e) {
            System.out.println("WARNING: reading from file " + f + " failed");
        }
        return retr;
    }
    
    /**
     * Adapted from Console's readSerializedData method. It
     * gathers the data of from a sensor's file
     * and outputs it as a string array.
     * 
     * @param <T> (double or int)
     * @param f -- the sensor's file
     * @return Data from the sensor's file as a String array.
     * @throws Exception
     */
    private <T> String[] getDataStringArrFrom(File f) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        
        @SuppressWarnings("unchecked")
        TreeSet<DataPacket<T>> obj1 = (TreeSet<DataPacket<T>>) ois.readObject();
        
        String datString = obj1.toString();
        
        // remove instances of '[', ']', and ','
        StringBuilder procStringBuilder = new StringBuilder("");
        for (int i = 0; i < datString.length(); i++) {
            var currChar = datString.charAt(i);
            if (currChar != '[' 
                    && currChar != ']'
                    && currChar != ',') {
                procStringBuilder.append(datString.charAt(i));
            }
        }
        
        ois.close();
        
        return procStringBuilder.toString().split(" "); 
    }

    /**
     * Collects weather data from all the sensors as a 
     * list of Doubles. The data is described below:
     * 
     * Index Variable
     * ----- --------
     * 0     Humidity 
     * 1     Temperature
     * 2     Wind Speed
     * 3     Daily accumulated rainfall
     * 
     * @return the weather data from this weather station 
     */
    private synchronized List<Double> collectWeatherData() {
        // only one thread can collect the weather data at a time.
        var retr = new ArrayList<Double>();
        
        retr.add(0.0 + getHumidity());
        retr.add(getTemp());
        retr.add(0.0 + getWindSpeed());
        retr.add(getRain());
        
        /*
        retr.add(0.0 + getWindChill());
        retr.add(getRainfallRate());
        retr.add(0.0 + getDewPoint());
        retr.add(0.0 + getHeatIndex());
        */
        
        return retr;
    }
    
    /**
     * Activates this weather station. Data 
     * will be sent roughly every 2 seconds
     */
    public void run() {
        Timer tock = new Timer();
        tock.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                update(collectWeatherData());
            }
        }, 0L, 2000L);
    }

}
